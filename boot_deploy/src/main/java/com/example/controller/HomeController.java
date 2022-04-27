package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

    // @ResponseBody
    // html이 표시되는 게 아니라 리턴값이 자체가 표시됨
    @RequestMapping(value = { "/", "/home", "/main" })
    public @ResponseBody String home() {
        return "home화면";
    }
}