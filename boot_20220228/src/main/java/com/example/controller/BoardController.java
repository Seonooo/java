package com.example.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.example.entity.Board;
import com.example.repository.BoardRepository;
import com.example.service.SequenceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping(value = "/board")
public class BoardController {

    // 서비스 => mybatis => 설계+구현(SQL)

    // 저장소 =>jpa, hibernate => 설계+구현(SQL)
    @Autowired
    BoardRepository bRepository;

    @Autowired
    SequenceService sequenceService;

    @Autowired
    HttpSession httpSession;

    @GetMapping(value = "/selectlist")
    public String selectListGET(Model model) {
        List<Board> list = bRepository.findAll();
        model.addAttribute("list", list);

        return "/board/selectlist";
    }

    @GetMapping(value = "/insert")
    public String insertGET() {
        return "board/insert";
    }

    @PostMapping(value = "/insert")
    public String insertPOST(Model model, @ModelAttribute Board board) {
        board.setNo(sequenceService.generatorSequence("SEQ_BAORD4_NO"));
        board.setRegdate(new Date());

        Board retBoard = bRepository.save(board);
        if (retBoard != null) {
            model.addAttribute("msg", "글쓰기 완료");
            model.addAttribute("url", "/board/selectlist");
            return "alert";
        }
        return "redirect:/board/insert";
    }

    // RedirectAttributes POST에서 GET으로 데이터를 전송
    @PostMapping(value = "/action")
    public String actionPOST(Model model,
            RedirectAttributes redirectAttributes,
            @RequestParam(name = "btn") String btn,
            @RequestParam(name = "radio") long no) {

        try {
            
        
                // == 비교int long  char /String 문자열은 안됨
        if (btn.equals("1개 삭제")) {
            bRepository.deleteById(no);
            model.addAttribute("msg", "삭제를 완료했습니다.");
            model.addAttribute("url", "/board/selectlist");
            return "alert";
        } else if (btn.equals("1개 수정")) {
            redirectAttributes.addAttribute("no", no);          // url에 표시됨
            redirectAttributes.addFlashAttribute("no1", no);    // 안보이게(1회성)
            return "redirect:/board/update";
        }
        return "redirect:/board/selectlist";
    }catch (Exception e) {
            e.printStackTrace();
            return "redirect:/board/home";
        }
    }

    @GetMapping(value = "/update")
    public String updateGET(Model model, @RequestParam(name = "no") long no) {
        Board board = bRepository.findById(no).orElse(null);
        model.addAttribute("board", board);
        return "/board/update";
    }

    @PostMapping(value = "/update")
    public String updatePOST(Model model, @ModelAttribute Board board) {
        try {
            // 추기 = 기본키를 다르게 해서 저장
            // 수정 = 기본키에 해당하는 글번호를 동일하게 해서 새로저장
            Board board1 = bRepository.findById(board.getNo()).orElse(null);
            board1.setTitle(board.getTitle());
            board1.setContent(board.getContent());
            board1.setWriter(board.getWriter());

            bRepository.save(board1);

            model.addAttribute("msg", "수정되었습니다.");
            model.addAttribute("url", "/board/selectlist");
            return "alert";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/home";
        }
    }

    @GetMapping(value = "/selectfind")
    public String selectfindGET(Model model,
    @RequestParam(name = "type", defaultValue = "", required = false) String type,
    @RequestParam(name = "text", defaultValue = "",required = false) String text,
    @RequestParam(name = "type1", defaultValue = "0", required = false) String type1,
    @RequestParam(name = "hit", defaultValue = "0", required = false) long hit1,
    @RequestParam(name = "type2", defaultValue = "0", required = false) String type2,
    @RequestParam(name = "no", defaultValue = "0", required = false) List<Long> no
    ){

        List<Board> list = null;
        if(type.equals("title")){
            list = bRepository.getBoardTitle(text);
        }else if(type.equals("writer")){
            list = bRepository.getBoardWriter(text);
        }else if(type.equals("hit")){
            long hit = 0L;
            try {
                hit = Long.parseLong(text);
            } catch (Exception e) {
                hit = 0L;
            }
            list = bRepository.getBoardHit(hit);
        }
        
        if(text.length()==0){
            list = bRepository.findAll();
        }

        if(type1.equals("1")){
            list = bRepository.findByHitGreaterThanEqual(hit1);
        }
        else if (type1.equals("2")){
            list = bRepository.findByHitLessThan(hit1);
        }

        if(type2.equals("1")){
            list = bRepository.findByNoIn(no);
        }
        else if(type2.equals("2")){
            list = bRepository.findByNoNotIn(no);
        }
        model.addAttribute("list", list);
        return "/board/selectfind";
    }

}
