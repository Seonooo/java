package com.example.restcontroller;

import java.util.HashMap;
import java.util.Map;

import com.example.dto.MemberDTO;
import com.example.jwt.JwtUtil;
import com.example.mapper.MemberMapper;
import com.example.service.UserDetailsServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/customer")
public class CustomerRestController {

    @Autowired
    MemberMapper mMapper;

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    // 암호변경 -- 토큰, 변경암호
    @RequestMapping(value = "/updatepw", method = { RequestMethod.PUT }, consumes = {
            MediaType.ALL_VALUE }, produces = {
                    MediaType.APPLICATION_JSON_VALUE })
    public Map<String, Object> customerMypagePUT(@RequestHeader(name = "TOKEN") String token,
            @RequestBody MemberDTO member) {
        Map<String, Object> map = new HashMap<>();
        map.put("status", 0);
        try {
            String username = jwtUtil.extractUsername(token);
            UserDetails user = userDetailsService.loadUserByUsername(username);
            BCryptPasswordEncoder bcpe = new BCryptPasswordEncoder();
            if (bcpe.matches(member.getUpw(), user.getPassword())) {
                mMapper.updateMemberPassword(username, bcpe.encode(member.getUpw1()));
                map.put("status", 200);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    // 회원정보수정 토큰, 이름, 전화번호
    @RequestMapping(value = "/updateinfo", method = { RequestMethod.PUT }, consumes = {
            MediaType.ALL_VALUE }, produces = {
                    MediaType.APPLICATION_JSON_VALUE })
    public Map<String, Object> customerInfoPUT(@RequestHeader(name = "TOKEN") String token,
            @RequestBody MemberDTO member) {
        Map<String, Object> map = new HashMap<>();
        map.put("status", 0);
        try {
            // 토큰으로 아이디받음
            String username = jwtUtil.extractUsername(token);
            UserDetails user = userDetailsService.loadUserByUsername(username);
            BCryptPasswordEncoder bcpe = new BCryptPasswordEncoder();
            if (bcpe.matches(member.getUpw(), user.getPassword())) {
                mMapper.updateMemberInfo(member, user.getUsername());
                map.put("status", 200);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    // 회원탈퇴 update 중요정보 내용만 지우기 토큰, 아이디를 제외한 내용 지우기

    // 마이페이지
    @RequestMapping(value = "/mypage", method = { RequestMethod.GET }, consumes = { MediaType.ALL_VALUE }, produces = {
            MediaType.APPLICATION_JSON_VALUE })
    public Map<String, Object> customerMypageGET(@RequestHeader(name = "TOKEN") String token) {
        // 토큰이 있어야 실행됨
        String username = jwtUtil.extractUsername(token);
        System.out.println(username);

        Map<String, Object> map = new HashMap<>();

        map.put("status", 200);

        return map;
    }

    // 로그인
    @RequestMapping(value = "/login", method = { RequestMethod.POST }, consumes = { MediaType.ALL_VALUE }, produces = {
            MediaType.APPLICATION_JSON_VALUE })
    public Map<String, Object> cutomerLoginPost(@RequestBody MemberDTO member) {
        Map<String, Object> map = new HashMap<>();
        map.put("status", 0);

        UserDetails user = userDetailsService.loadUserByUsername(member.getUemail());

        BCryptPasswordEncoder bcpe = new BCryptPasswordEncoder();
        if (bcpe.matches(member.getUpw(), user.getPassword())) {
            String token = jwtUtil.generatorToken(member.getUemail());
            map.put("status", 200);
            map.put("token", token);
        }

        return map;
    }

    // 회원가입(고객만)
    // 127.0.0.1:9090/ROOT/api/rest_customer/join
    @RequestMapping(value = "/join", method = { RequestMethod.POST }, consumes = { MediaType.ALL_VALUE }, produces = {
            MediaType.APPLICATION_JSON_VALUE })
    public Map<String, Object> customerJoinPost(
            @RequestBody MemberDTO member) {
        BCryptPasswordEncoder bcpe = new BCryptPasswordEncoder();
        member.setUpw(bcpe.encode(member.getUpw()));
        member.setUrole("CUSTOMER");

        int ret = mMapper.memberJoin(member);
        Map<String, Object> map = new HashMap<>();
        map.put("status", 0);
        if (ret == 1) {
            map.put("status", 200);
        }
        return map;
    }
}
