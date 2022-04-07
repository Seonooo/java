package com.example.Controller;

import com.example.Service.MemberService;
import com.example.dto.MemberDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/member")
public class MemberController {

    @Autowired
    MemberService mService;

    @GetMapping(value = "/login")
    public String loginGET() {
        return "/member/login";
    }

    @GetMapping(value = "/join")
    public String joinGET() {
        return "/member/join";
    }

    @PostMapping(value = "/join")
    public String joinPOST(@ModelAttribute MemberDTO member) {
        BCryptPasswordEncoder bcpe = new BCryptPasswordEncoder();
        member.setUpw(bcpe.encode(member.getUpw()));
        member.setUrole("SELLER");
        mService.memberInsertOne(member);

        return "redirect:/";
    }

}
