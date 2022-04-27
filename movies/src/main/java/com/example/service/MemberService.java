package com.example.service;

import com.example.entity.MemberEntity;

import org.springframework.stereotype.Controller;

@Controller
public interface MemberService {

    public int insertMember(MemberEntity member);
}
