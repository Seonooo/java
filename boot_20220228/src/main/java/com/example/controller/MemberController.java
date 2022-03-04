package com.example.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.example.entity.Member;
import com.example.service.MemberDB;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/member")
public class MemberController {

    // DB에 일의 수행하는 클래스
    @Autowired
    private MemberDB memberDB;

    @Autowired // 전역
    private HttpSession httpSession;

    // 로그인
    @GetMapping(value = "login")
    public String loginGET() {
        return "/member/login";
    }

    @PostMapping(value = "/mypage")
    public String mypagePOST(
            @RequestParam(name = "menu") int menu,
            @ModelAttribute Member member) {
        if (menu == 1) { // 정보수정 메뉴일 때
            int ret = memberDB.updateMember(member);
            if (ret == 1) {
                httpSession.setAttribute("USERNAME", member.getUsername());
                return "redirect:/member/mypage?menu=1";
            }
            return "redirect:/member/mypage?menu=1";
        } else if (menu == 2) { // 암호변경
            String userid = (String) httpSession.getAttribute("USERID");
            member.setUserid(userid);
            long ret = memberDB.updateMemberPassword(member);
            if (ret == 1L) {
                return "redirect:/member/mypage?menu=2";
            }
            return "redirect:/member/mypage?menu=2";

        } else if (menu == 3) { // 회원탈퇴
            String userid = (String) httpSession.getAttribute("USERID");
            member.setUserid(userid);
            long ret = memberDB.withdrawMember(member);
            if (ret == 1L) {
                httpSession.invalidate();
                return "redirect:/home";
            }
            return "redirect:/member/mypage?menu=3";
        }
        return "redirect:/home";
    }

    @GetMapping(value = "/mypage")
    public String mypageGET(Model model,
            @RequestParam(name = "menu", defaultValue = "0") int menu) {
        if (menu == 0) {
            return "redirect:/member/mypage?menu=1";
        }
        // 세션에서 정보를 읽음
        String userid = (String) httpSession.getAttribute("USERID");
        // 세션에 정보가 없다면(로그인 되지 않은 상태에서 mypage접근)
        if (userid == null) {
            return "redirect:/member/login";
        }

        if (menu == 1) { // 정보수정

            Member member = memberDB.selectOneMember(userid);
            model.addAttribute("title", "정보수정");
            model.addAttribute("mem", member);
        } else if (menu == 2) { // 암호변경
            model.addAttribute("title", "암호변경");
        } else if (menu == 3) { // 회원탈퇴
            model.addAttribute("title", "회원탈퇴");
        }

        return "member/mypage"; // 정보변경 암호변경 탈퇴하기
    }

    @PostMapping(value = "login")
    public String loginPOST(@ModelAttribute Member member) {
        Member retMember = memberDB.selectLogin(member);
        if (retMember != null) {
            // 로그인 시점
            // 세션 : 서버에 기록되는 정보
            // (어떤 주소, 어떤 컨트롤러에서 공유)
            httpSession.setAttribute("USERID", retMember.getUserid());
            httpSession.setAttribute("USERNAME", retMember.getUsername());
            return "redirect:/home";
        }
        return "redirect:/member/login";
    }

    @GetMapping(value = "logout")
    public String logoutGET() {
        httpSession.invalidate();
        return "redirect:/home";
    }

    @GetMapping(value = { "/delete" })
    public String deleteGet(@RequestParam(name = "id") String userid) {
        int ret = memberDB.deleteMember(userid);
        if (ret == 1) {
            return "redirect:/member/selectlist";
        }
        return "redirect:/member/selectlist";
    }

    @GetMapping(value = { "/selectlist" })
    public String selectlistGet(Model model) {
        // 1. DB에서 목록 받아오기
        List<Member> list = memberDB.selectListMember();

        // 2. jsp로 전달하기 addAttribute(jsp에서의 변수명, 실제값)
        model.addAttribute("list", list);

        // 3. member풀더에 있는 select.jsp
        return "member/select";
    }

    @GetMapping(value = { "/insert" })
    public String insertGet() {
        // member풀더에 있는 insert.jsp
        return "member/insert";
    }

    // post는 사용자가 입력한 내용이 전달되고
    // db작업을 위해서 필요한시점
    // jsp를 표시하는게 아니라 주소창에 입력후 엔터키를 누름

    @PostMapping(value = { "/insert" })
    public String insertPOST(
            @ModelAttribute Member mem) {

        System.out.println(mem.toString());
        memberDB.insertMember(mem);
        // 주소창에 /member/insert를 입력후
        // 엔터키를 누르는것과 같은역할
        return "redirect:/member/insert";
    }

    @GetMapping(value = "/update")
    public String updateGET(
            Model model,
            @RequestParam(name = "id") String id) {
        // db에서 내용을 가져오기
        Member member = memberDB.selectOneMember(id);
        // jsp로 전달
        model.addAttribute("member", member);
        return "/member/update";
    }

    @PostMapping(value = "/update")
    public String updatePOST(@ModelAttribute Member member) {
        System.out.println(member.toString());
        int ret = memberDB.updateMember(member);
        if (ret == 1) {
            return "redirect:/member/selectlist";
        }

        // post에서는 jsp표시x, redirect를 이용해서 주소변경
        return "redirect:/member/update?id=" + member.getUserid();
    }

}
