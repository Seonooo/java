package com.example.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

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

    @PostMapping(value = "/action")
    public String actionPOST(Model model,
            @RequestParam(name = "btn") String btn,
            @RequestParam(name = "radio") long no) {

        if (btn.equals("1개 삭제")) {
            bRepository.deleteById(no);
            model.addAttribute("msg", "삭제를 완료했습니다.");
            model.addAttribute("url", "/board/selectlist");
            return "alert";
        } else if (btn.equals("1개 수정")) {
            httpSession.setAttribute("no", no);
            return "/board/update";
        }
        return "redirect:/board/selectlist";
    }

    @GetMapping(value = "/update")
    public String updateGET(Model model, @ModelAttribute Board board) {
        Optional<Board> b1 = bRepository.findById(board.getNo());
        model.addAttribute("board", b1);
        return "/board/update";
    }

    @PostMapping(value = "/update")
    public String updatePOST(Model model, @ModelAttribute Board board) {
        Board b1 = bRepository.save(board);
        model.addAttribute("board", b1);
        return "board/update";
    }

}
