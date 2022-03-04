package com.example.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.example.entity.Book;
import com.example.service.BookDB;
import com.example.service.SequenceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    @Autowired
    BookDB bookdb;

    @Autowired
    SequenceService sequence;

    @GetMapping(value = "/insertbatch")
    public String insertGET() {
        return "/admin/insertbatch";
    }

    @PostMapping(value = "/insertbatch")
    public String insertPOST(
            @RequestParam(name = "title") String[] title,
            @RequestParam(name = "price") long[] price,
            @RequestParam(name = "writer") String[] writer,
            @RequestParam(name = "category") String[] category) {
        List<Book> list = new ArrayList<>();
        for (int i = 0; i < title.length; i++) { // 0 1
            Book book = new Book();
            book.setCode(sequence.generatorSequence("SEQ_BOOK4_CODE"));
            book.setTitle(title[i]);
            book.setPrice(price[i]);
            book.setWriter(writer[i]);
            book.setCategory(category[i]);
            book.setRegdate(new Date());

            list.add(book);
        }
        // 배열을 list<Book>으로 변형작업
        // 시퀀스이용해서 날짜, 코드 채우기
        bookdb.insertBatchBook(list);
        return "redirect:/admin/insertbatch";
    }

    @GetMapping(value = "/selectlist")
    public String selectlistGET(Model model,
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "text", defaultValue = "") String text) {

        // 페이지+검색
        List<Book> list = bookdb.selectListPageSearchBook(page, text);
        long pages = bookdb.countSearchBook(text);

        // jsp로 전달(변수, 값) => 변수
        model.addAttribute("list", list);
        model.addAttribute("pages", (pages - 1) / 10 + 1);

        return "admin/selectlist";
    }

    @PostMapping(value = "/action")
    public String actionPOST(
            HttpSession httpSession,
            @RequestParam(name = "btn") String btn, @RequestParam(name = "chk") long[] code) {

        if (btn.equals("일괄삭제")) {
            for (int i = 0; i < code.length; i++) {
                bookdb.deleteBook(code[i]);
            }

        } else if (btn.equals("일괄수정")) {
            // 세션에 long[]의 code를 세션에 넣음
            httpSession.setAttribute("code", code);

            // 페이지를 이동함
            long[] code1 = (long[]) httpSession.getAttribute("code");
            return "redirect:/admin/updatebatch";

        }
        return "redirect:/admin/selectlist";
    }
}
