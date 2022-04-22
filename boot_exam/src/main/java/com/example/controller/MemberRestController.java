package com.example.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Member;
import com.example.service.MemberService;

@RestController
@RequestMapping(value = "/member")
public class MemberRestController {

    @Autowired
    MemberService mService;

    @PostMapping(value = "/insert.rest", consumes = { MediaType.ALL_VALUE }, produces = {
            MediaType.APPLICATION_JSON_VALUE })
    public Map<String, Object> insertPOST(
            @RequestBody Member member) {
        Map<String, Object> map = new HashMap<>();
        try {
            mService.insertMemberOne(member);
            map.put("status", 200);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("status", 0);
        }
        return map;
    }

}