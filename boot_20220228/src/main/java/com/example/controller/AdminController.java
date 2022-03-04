package com.example.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.entity.Book;
import com.example.service.BookDB;
import com.example.service.SequenceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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

    @PostMapping(value = "/inserbatch")
    public String insertPOST(
            @RequestParam(name = "title") String[] title,
            @RequestParam(name = "title") long[] price,
            @RequestParam(name = "title") String[] writer,
            @RequestParam(name = "title") String[] category) {
        List<Book> list = new ArrayList();
        for (int i = 0; i < title.length; i++) {
            System.out.println(title[i] + "," + price[i] + "," + writer[i] + "," + category[i]);
            Book book = new Book();
            long seq = sequence.generatorSequence("SEQ_BOOK4_NO");
            book.setCode(seq);
            book.setPrice(price[i]);
            book.setRegdate(new Date());
            book.setTitle(title[i]);
            book.setWriter(writer[i]);
            book.setCategory(category[i]);
        }
        // 배열을 list<Book>으로 변형작업
        // 시퀀스이용해서 날짜, 코드 채우기
        bookdb.insertBatchBook(list);
        return "redirect:/admin/insertbatch";
    }
}
