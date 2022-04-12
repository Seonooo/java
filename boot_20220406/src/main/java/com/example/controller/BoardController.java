package com.example.controller;

import java.util.List;

import com.example.entity.BoardEntity;
import com.example.entity.BoardReplyEntity;
import com.example.repository.BoardReplyRepository;
import com.example.repository.BoardRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import io.lettuce.core.dynamic.annotation.Param;

@Controller
@RequestMapping(value = "/board")
public class BoardController {

    @Autowired
    BoardRepository bRepository;
    @Autowired
    BoardReplyRepository brRepository;

    @Value("${board.page.count}")
    int PAGECNT;

    @PostMapping(value = "/insertreply")
    public String insertReply(@ModelAttribute BoardReplyEntity reply) {
        System.out.println(reply.toString());
        brRepository.save(reply);
        return "redirect:/board/selectone?no=" + reply.getBoard().getNo();
    }

    @GetMapping(value = "/insert")
    public String insertGET() {
        return "board/insert";
    }

    @PostMapping(value = "/insert")
    public String insertPOST(@ModelAttribute BoardEntity board) {

        bRepository.save(board);

        return "redirect:/board/selectlist";
    }

    @GetMapping(value = "/selectlist")
    public String selectlistGET(Model model, @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "title", defaultValue = "") String title) {

        // 페이지 네이션 (시작페이지, 개수)
        PageRequest pageRequest = PageRequest.of(page - 1, 10);

        List<BoardEntity> list = bRepository.findByTitleContainingOrderByNoDesc(title, pageRequest);
        model.addAttribute("list", list);

        //
        long total = bRepository.countByTitleContaining(title);
        model.addAttribute("pages", (total - 1) / PAGECNT + 1);

        return "board/selectlist";
    }

    @GetMapping(value = "/selectone")
    public String selectOneGET(@Param(value = "no") long no, Model model) {
        BoardEntity board = bRepository.findById(no).orElse(null);
        List<BoardReplyEntity> replist = brRepository.findByBoard_noOrderByNoDesc(no);
        model.addAttribute("brd", board);
        model.addAttribute("repList", replist);
        return "/board/selectone";
    }

    @PostMapping(value = "/delete")
    public String deleteOnePOST(@Param(value = "no") long no) {
        bRepository.deleteById(no);
        return "redirect:/board/selectlist";
    }

}
