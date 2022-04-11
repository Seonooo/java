package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    // @reponsebody => return에 있는 글자가 나옴
    @GetMapping(value = { "/", "/home" })
    public @ResponseBody String adminGET() {
        return "admin 글자 그대로 나옴";
    }
}
