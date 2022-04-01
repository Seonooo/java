package com.example.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.example.dto.MemberDTO;
import com.example.service.MemberService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/seller")
public class SellerController {
    @Autowired
    MemberService mService;

    // 127.0.0.1:9090/ROOT/seller/insert
    @GetMapping(value = "/insert")
    public String insertGET() {
        // templates폴더 seller폴더 insert.html 표시 렌더링
        return "/seller/insert";
    }

    // 127.0.0.1:9090/ROOT/seller/insert
    @PostMapping(value = "/insert")
    public String insertPOST(@ModelAttribute MemberDTO member) {
        System.out.println(member.toString());
        mService.insertMember(member);
        return "redirect:/home"; // 주소를 바꾼다음 엔터키
    }

    // 127.0.0.1:9090/ROOT/seller/selectlist
    @GetMapping(value = "/selectlist")
    public String selectlistGET(Model model) {
        List<MemberDTO> list = mService.selectMemberList();
        model.addAttribute("list", list);
        return "/seller/selectlist";
    }

    @RequestMapping(value = "/delete", method = { RequestMethod.GET, RequestMethod.POST })
    public String deleteGET(@RequestParam(name = "uemail") String em) {
        int ret = mService.deleteMember(em);
        if (ret == 1) {

        } else {

        }
        return "redirect:/seller/selectlist";
    }

    @GetMapping(value = "/update")
    public String updateGET(Model model, @RequestParam(name = "uemail") String em) {
        MemberDTO member = mService.selectMemberOne(em);
        model.addAttribute("obj", member);
        return "/seller/update";
    }

    @PostMapping(value = "/update")
    public String updatePost(@ModelAttribute MemberDTO member) {
        int ret = mService.updateMember(member);
        if (ret == 1) {
            return "redirect:/seller/selectlist";
        } else {
            return "redirect:/seller/update?email=" + member.getUemail();
        }
    }

    @GetMapping(value = "/select")
    public String selectGET() {
        return "/seller/select";
    }

    @PostMapping(value = "/select")
    public String selectPOST(HttpSession httpSession, @ModelAttribute MemberDTO member) {
        System.out.println(member);
        MemberDTO retMember = mService.selectMemberLogin(member);
        if (retMember != null) {
            // 세션에 정보를 기록함(세션 기본유지시간은 60*30 = 1800초)
            httpSession.setAttribute("SESSION_UEMAIL", retMember.getUemail());
            httpSession.setAttribute("SESSION_UNAME", retMember.getUname());
            httpSession.setAttribute("SESSION_UROLE", retMember.getUrole());
            return "redirect:/";
        }

        return "redirect:/seller/select";
    }

    @RequestMapping(value = "/logout", method = { RequestMethod.GET, RequestMethod.POST })
    public String logouteGETPOST(HttpSession httpSession) {
        // 세션 데이터 지우기
        httpSession.invalidate();
        return "redirect:/";
    }
}