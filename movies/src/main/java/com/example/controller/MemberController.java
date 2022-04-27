package com.example.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.example.entity.CategoryEntity;
import com.example.entity.MemberEntity;
import com.example.entity.MembercategoryEntity;
import com.example.entity.MemberpointEntity;
import com.example.entity.MembershipEntity;
import com.example.repository.CategoryRepository;
import com.example.repository.MemberRepository;
import com.example.repository.MembercategoryRepository;
import com.example.repository.MemberpointRepository;
import com.example.repository.MembershipRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/member")
public class MemberController {

    @Autowired
    CategoryRepository cRepository;

    @Autowired
    MemberRepository mRepository;

    @Autowired
    MembershipRepository msRepository;

    @Autowired
    MembercategoryRepository mcRepository;

    @Autowired
    MemberpointRepository mpRepository;

    // 회원 홈
    @GetMapping(value = { "/", "/home" })
    public String homeGET(
            Model model,
            @AuthenticationPrincipal User user) {
        MemberEntity member = mRepository.findById("aaa").orElse(null);
        System.out.println(member.getMId());
        model.addAttribute("user", user);
        return "/member/home";
    }

    // 로그인 홈
    @GetMapping(value = { "/login" })
    public String loginGET() {

        return "/member/login";
    }

    // 로그인 홈
    @GetMapping(value = { "/logout" })
    public String logoutGET() {

        return "/member/logout";
    }

    // 회원가입 페이지
    @GetMapping(value = { "/insert" })
    public String insertGET(
            Model model) {
        List<CategoryEntity> list = cRepository.findAll();
        // System.out.println(list);
        model.addAttribute("category", list);
        return "/member/insert";
    }

    // 회원가입
    @PostMapping(value = { "/insert" })
    public String insertPOST(
            @ModelAttribute MemberEntity member) throws ParseException {
        // 비밀번호 HASH
        BCryptPasswordEncoder bcpe = new BCryptPasswordEncoder();
        member.setMPw(bcpe.encode(member.getMPw()));

        // 생년월일 넣기
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String birth = member.getYear() + "-" + member.getMonth() + "-" + member.getDay();
        Date date = formatter.parse(birth);
        member.setMBirth(date);

        // 멤버쉽 추가
        MembershipEntity membership = new MembershipEntity();
        membership.setMsCode("BVIP");
        member.setMembershipEntity(membership);

        mRepository.save(member);

        // 멤버 선호장르 추가

        for (Long code : member.getCategoryCode()) {
            MembercategoryEntity membercategory = new MembercategoryEntity();
            CategoryEntity category = new CategoryEntity();
            membercategory.setMember(member);
            category.setCCode(code);
            membercategory.setCategory(category);
            mcRepository.save(membercategory);
            System.out.println(code);
        }

        // 멤버 포인트 생성
        MemberpointEntity memberpoint = new MemberpointEntity();
        memberpoint.setMember(member);
        memberpoint.setMpNowpoint(0L);
        memberpoint.setMpStackpoint(0L);
        memberpoint.setMpUsepoint(0L);

        mpRepository.save(memberpoint);

        System.out.println(member.toString());
        return "redirect:/member/insert";
    }
}
