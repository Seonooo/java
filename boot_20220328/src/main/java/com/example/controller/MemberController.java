package com.example.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.example.dto.MemberAdrDTO;
import com.example.dto.MemberDTO;
import com.example.mapper.MemberAdrMapper;
import com.example.mapper.MemberMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping(value = "/member")
public class MemberController {

    @Autowired
    MemberMapper mMapper;

    @Autowired
    MemberAdrMapper madrMapper;

    @Autowired
    HttpSession httpSession;

    @GetMapping(value = "/login")
    public String loginGET() {

        return "/member/login";
    }

    @PostMapping(value = "/login")
    public String loginPOST(
            @RequestParam(name = "uemail") String uemail,
            @RequestParam(name = "upw") String upw) {
        MemberDTO member = mMapper.memberLogin(uemail, upw);
        if (member != null) {
            httpSession.setAttribute("M_EMAIL", member.getUemail());
            httpSession.setAttribute("M_NAME", member.getUname());
            httpSession.setAttribute("M_ROLE", member.getUrole());
            return "redirect:/";
        }
        return "redirect:/member/login";
    }

    @PostMapping(value = "/logout")
    public String logoutPOST() {
        httpSession.invalidate();
        return "redirect:/";
    }

    @GetMapping(value = "/join")
    public String joinGET() {

        return "/member/join";
        // render => template의 파일을 표시
        // render는 get에서만 사용
    }

    // redirect는 주소를 변경후 엔터키를 누름
    @PostMapping(value = "/join")
    public String joinPOST(@ModelAttribute MemberDTO member) {
        member.setUrole("CUSTOMER");
        System.out.println(member.toString());

        int ret = mMapper.memberJoin(member);
        if (ret == 1) {
            return "redirect:/";
        }

        return "redirect:/member/join";
    }

    @GetMapping(value = "/address")
    public String addressGET(Model model) {
        String em = (String) httpSession.getAttribute("M_EMAIL");
        String role = (String) httpSession.getAttribute("M_ROLE");
        if (em != null) {
            if (role.equals("CUSTOMER")) {
                List<MemberAdrDTO> list = madrMapper.selectMemberAdrList(em);
                model.addAttribute("addr", list);

                return "member/address";
            }
            return "redirect:/403page";
        }
        return "redirect:/member/login";
    }

    @PostMapping(value = "/address")
    public String addressPost(@ModelAttribute MemberAdrDTO addr) {
        String em = (String) httpSession.getAttribute("M_EMAIL");
        String role = (String) httpSession.getAttribute("M_ROLE");
        if (em != null) {
            if (role.equals("CUSTOMER")) {
                addr.setUemail(em);
                madrMapper.insertMemberAdr(addr);
                System.out.println(addr);
                return "redirect:/member/address";
            }
        }
        return "redirect:/member/login";
    }

    @PostMapping(value = "setaddr")
    public String setaddrPOST(@RequestParam(name = "code") long code) {
        String em = (String) httpSession.getAttribute("M_EMAIL");
        String role = (String) httpSession.getAttribute("M_ROLE");
        if (em != null) {
            if (role.equals("CUSTOMER")) {
                madrMapper.updateMemberAddressSet(code, em);
                return "redirect:/member/address";

            }
        }
        return "redirect:/member/login";
    }

    @GetMapping(value = "deleteaddr")
    public String deleteaddrGET(@RequestParam(name = "code") long code) {
        String em = (String) httpSession.getAttribute("M_EMAIL");
        String role = (String) httpSession.getAttribute("M_ROLE");
        if (em != null) {
            if (role.equals("CUSTOMER")) {
                madrMapper.deleteMemeberAddress(code, em);
                return "redirect:/member/address";

            }
        }
        return "redirect:/member/login";
    }

    @GetMapping(value = "/updateaddr")
    public String updateaddrGET(@RequestParam(name = "code") long code, Model model) {
        String em = (String) httpSession.getAttribute("M_EMAIL");
        String role = (String) httpSession.getAttribute("M_ROLE");
        if (em != null) {
            if (role.equals("CUSTOMER")) {
                MemberAdrDTO addr = madrMapper.selectMemberAdressOne(code, em);
                model.addAttribute("addr", addr);
                return "/member/updateaddr";
            }
        }
        return "redirect:/member/login";
    }

    @PostMapping(value = "/updateaddr")
    public String updateaddrPOST(@ModelAttribute MemberAdrDTO addr) {
        String em = (String) httpSession.getAttribute("M_EMAIL");
        addr.setUemail(em);
        madrMapper.updateMemberAddress(addr);
        return "redirect:/member/address";
    }

}
