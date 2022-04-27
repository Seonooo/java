package com.example.restcontroller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.MemberEntity;
import com.example.jwt.jwtUtil;
import com.example.repository.MemberRepository;
import com.example.service.UserDetailsServiceImpl;

// backend만 구현함. 화면구현 X, vue.js 또는 react.js 연동

@RestController
@RequestMapping("/api/member")
public class MemberRestController {

    @Autowired
    MemberRepository mRepository;

    @Autowired
    jwtUtil jwt;

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    // http://127.0.0.1:9090/ROOT/api/member/login
    @RequestMapping(value = "/login", method = { RequestMethod.POST }, consumes = { MediaType.ALL_VALUE }, produces = {
            MediaType.APPLICATION_JSON_VALUE })
    public Map<String, Object> customerLogingePost(
            @RequestBody MemberEntity member) {
        Map<String, Object> map = new HashMap<>();
        map.put("status", 0);// 정상적이지 않을 때
        System.out.println("MemberRestController ===> member.getMId() = " + member.getMId());
        UserDetails user = userDetailsService.loadUserByUsername(member.getMId());

        BCryptPasswordEncoder bcpe = new BCryptPasswordEncoder();
        // 암호화가 되지않은 암호와 암호화된 암호를 비교
        if (bcpe.matches(member.getMPw(), user.getPassword())) {
            String token = jwt.generatorToken(member.getMId());

            // MemberEntity member2 = mRepository.findBymId(member.getMId());
            // String rToken = jwt.generatorRoleToken(member2.getMRole());
            // map.put("rToken", rToken);
            map.put("status", 200);
            map.put("token", token);
        }

        return map;
    }

}