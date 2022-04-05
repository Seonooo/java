package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

import com.example.dto.MemberDTO;
import com.example.dto.MyUserDTO;
import com.example.mapper.MemberMapper;

@Controller
public class SecurityController {

    @Autowired
    MemberMapper mMapper;

    @Autowired
    HttpSession httpSession;

    // 로그인과 상관없이 보이는 페이지
    @GetMapping(value = { "/security_home" })
    public String securityHomeGET(@AuthenticationPrincipal MyUserDTO user, Model model) {
        if (user != null) {
            System.out.println(user.getUsername());
            System.out.println(user.getName());
            System.out.println(user.getUserphone());
            System.out.println(user.getAuthorities().toArray()[0]);
            model.addAttribute("userid", user.getUsername());
            model.addAttribute("username", user.getName());
            model.addAttribute("userphone", user.getUserphone());
            model.addAttribute("userrole", user.getAuthorities().toArray()[0]);
        }

        model.addAttribute("user", user);

        return "/security/home";
    }

    // 로그인 후에 보이는 페이지
    @GetMapping(value = { "/security_admin/home" })
    public String securityAdminomeGET() {
        return "/security/admin_home";
    }

    // 로그인 후에 보이는 페이지
    @GetMapping(value = { "/security_seller/home" })
    public String securitySellerHomeGET() {
        return "/security/seller_home";
    }

    // 로그인 후에 보이는 페이지
    @GetMapping(value = { "/security_customer/home" })
    public String securityCustomerHomeGET() {
        return "/security/customer_home";
    }

    // 127.0.0.1:9090/ROOT/member/security_join
    @GetMapping(value = "/member/security_join")
    public String securityJoinGET() {
        return "/security/join";
    }

    @PostMapping(value = "/member/security_join")
    public String securityJoinPOST(
            @ModelAttribute MemberDTO member) {
        BCryptPasswordEncoder bcpe = new BCryptPasswordEncoder();
        // 암호를 가져와서 해시한후 다시 추가하기
        member.setUpw(bcpe.encode(member.getUpw()));
        member.setUrole("CUSTOMER");

        int ret = mMapper.memberJoin(member);
        if (ret == 1) { // 성공 시
            return "redirect:/security_home";
        }
        // 실패 시
        return "redirect:/member/security_join";
    }

    @GetMapping(value = "/member/security_login")
    public String securityLoginGET() {
        return "security/login";
    }

    @GetMapping(value = "/security_403")
    public String securityGET() {
        return "security/403page";
    }
}