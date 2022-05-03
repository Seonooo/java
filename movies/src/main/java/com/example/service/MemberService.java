package com.example.service;

import com.example.entity.MemberEntity;

import org.springframework.stereotype.Service;

@Service
public interface MemberService {

    // 회원가입
    public int insertMember(MemberEntity member);

    // 회원가입
    public int updateprofile(MemberEntity member);

    // 회원한명 조회
    public MemberEntity getMember(String mid);

    // 회원정보수정
    public int updateMember(MemberEntity member);

    // 회원탈퇴
    public int deleteMember(String mid);

}
