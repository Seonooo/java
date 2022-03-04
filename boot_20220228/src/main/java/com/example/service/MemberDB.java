package com.example.service;

import java.util.List;

import com.example.entity.Member;

import org.springframework.stereotype.Service;

@Service
public interface MemberDB {
    // 추가할 내용을 member로 주면 추가한 후에 실제 추가된 내용을 반환
    public Member insertMember(Member member);

    // 회원 전체목록(page, search x)
    public List<Member> selectListMember();

    // 회원 1명삭제
    public int deleteMember(String id);

    // 회원1명 조회하기
    public Member selectOneMember(String id);

    // 회원정보 변경하기
    public int updateMember(Member member);

    // 로그인(아이디, 암호 전달되면 일치하는 회원정보를 반환)
    public Member selectLogin(Member member);

    // 암호변경
    public long updateMemberPassword(Member member);

    // 탈퇴하기
    public long withdrawMember(Member member);
}
