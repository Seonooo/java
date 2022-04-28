package com.example.restcontroller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.MemberEntity;
import com.example.jwt.JwtUtil;
import com.example.repository.MemberRepository;
import com.example.service.UserDetailsServiceImpl;

// backend만 구현함. 화면구현 X, vue.js 또는 react.js 연동

@RestController
@RequestMapping("/api/member")
public class MemberRestController {

    @Autowired
    MemberRepository mRepository;

    @Autowired
    JwtUtil jwt;

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    // 로그인
    // http://127.0.0.1:9090/ROOT/api/member/login
    // requestbody가 안되던 이유 : 소문자로만 전달을 해줘야함
    @RequestMapping(value = "/login", method = { RequestMethod.POST }, consumes = { MediaType.ALL_VALUE }, produces = {
            MediaType.APPLICATION_JSON_VALUE })
    public Map<String, Object> customerLogingePost(
            @RequestBody MemberEntity member) {
        System.out.println(member.toString());
        Map<String, Object> map = new HashMap<>();
        try {
            UserDetails user = userDetailsService.loadUserByUsername(member.getMId());
            BCryptPasswordEncoder bcpe = new BCryptPasswordEncoder();
            // 암호화가 되지않은 암호와 암호화된 암호를 비교
            if (bcpe.matches(member.getMPw(), user.getPassword())) {
                String token = jwt.generatorToken(member.getMId());
                map.put("status", 200);
                map.put("token", token);
            }

        } catch (Exception e) {
            map.put("status", 0);// 정상적이지 않을 때
        }

        return map;
    }

    // 마이페이지
    // http://127.0.0.1:9090/ROOT/api/member/mypage
    @RequestMapping(value = "/mypage", method = { RequestMethod.GET }, consumes = { MediaType.ALL_VALUE }, produces = {
            MediaType.APPLICATION_JSON_VALUE })
    public Map<String, Object> customerMypageGet(@RequestHeader(name = "TOKEN") String token) {
        Map<String, Object> map = new HashMap<>();
        try {
            // 토큰으로 아이디 찾기
            String mId = jwt.extractUsername(token);
            System.out.println(mId);
            // 토큰에서 추출한 아이디로 member찾기
            MemberEntity member = mRepository.findBymId(mId);
            // member에 member전송하기
            map.put("status", 200);
            map.put("member", member);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("status", 200);
        }
        return map;
    }

    // 회원정보수정
    // 암호, 주소, 이메일, 연락처 변경
    // http://127.0.0.1:9090/ROOT/api/member/updateinfo
    @RequestMapping(value = "/updateinfo", method = { RequestMethod.PUT }, consumes = {
            MediaType.ALL_VALUE }, produces = {
                    MediaType.APPLICATION_JSON_VALUE })
    public Map<String, Object> customerInfoPut(@RequestHeader(name = "TOKEN") String token, // 토큰받기
            @RequestBody MemberEntity member) {
        System.out.println(member.toString());
        Map<String, Object> map = new HashMap<>();
        map.put("status", 0);
        try {
            // 토큰으로 아이디받음
            String mId = jwt.extractUsername(token);
            // 토큰에서 받은 아이디로 user정보 받음
            UserDetails user = userDetailsService.loadUserByUsername(mId);
            BCryptPasswordEncoder bcpe = new BCryptPasswordEncoder();

            if (bcpe.matches(member.getMPw(), user.getPassword())) {
                // newMember에 업데이트할 정보를 가져옴
                MemberEntity newMember = mRepository.findBymId(user.getUsername());
                newMember.setMPw(bcpe.encode(member.getMPw1()));
                newMember.setMAddr(member.getMAddr());
                newMember.setMEmail(member.getMEmail());
                newMember.setMPhone(member.getMPhone());
                mRepository.save(newMember);
                map.put("status", 200);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    // 회원탈퇴
    // 회원 아이디만 남기고 나머지는 null값으로 변경
    // http://127.0.0.1:9090/ROOT/api/member/delete
    @RequestMapping(value = "/delete", method = { RequestMethod.PUT }, consumes = { MediaType.ALL_VALUE }, produces = {
            MediaType.APPLICATION_JSON_VALUE })
    public Map<String, Object> customerMypagePut(@RequestHeader(name = "TOKEN") String token,
            @RequestBody MemberEntity oldmember) {
        Map<String, Object> map = new HashMap<>();
        map.put("status", 0);
        try {
            // 토큰으로 아이디받음
            String mId = jwt.extractUsername(token);
            // 토큰에서 받은 아이디로 user정보 받음
            UserDetails user = userDetailsService.loadUserByUsername(mId);
            BCryptPasswordEncoder bcpe = new BCryptPasswordEncoder();

            if (bcpe.matches(oldmember.getMPw(), user.getPassword())) {
                MemberEntity member = mRepository.findBymId(user.getUsername());
                System.out.println("oldmember =>" + member);
                member.setMPw(null);
                member.setMName(null);
                member.setMEmail(null);
                member.setMPhone(null);
                member.setMRole(null);
                member.setMAddr(null);
                member.setMBirth(new Date());
                member.setMGender(null);
                member.setMProfile(null);
                member.setMProfiletype(null);
                member.setMProfilesize(null);
                member.setMProfilename(null);
                member.setMembershipEntity(null);
                System.out.println("newmember =>" + member);
                // mRepository.save(member);
                // System.out.println(oldmember);
            }

            map.put("status", 200);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return map;
    }

}