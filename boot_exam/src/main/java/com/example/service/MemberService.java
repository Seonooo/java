package com.example.service;

import org.springframework.stereotype.Service;

import com.example.entity.Member;

@Service
public interface MemberService {
    public int insertMemberOne(Member member);
}
