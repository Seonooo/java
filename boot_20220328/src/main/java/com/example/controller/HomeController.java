package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    // 서버주소 : 포트번호/컨텍스트PATH/home
    // 127.0.0.1:9090/ROOT/
    // 127.0.0.1:9090/ROOT/home
    @GetMapping(value = { "/", "home" })
    public String homeGET() {
        return "home";
    }
}
