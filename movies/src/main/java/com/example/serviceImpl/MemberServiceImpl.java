package com.example.serviceImpl;

import java.util.Date;

import com.example.entity.MemberEntity;
import com.example.repository.MemberRepository;
import com.example.service.MemberService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;

@Controller
public class MemberServiceImpl implements MemberService {

    @Autowired
    MemberRepository mRepository;

    // 회원가입
    @Override
    public int insertMember(MemberEntity member) {
        try {
            mRepository.save(member);
            return 1;
        } catch (Exception e) {
            return 0;
        }

    }

    // 마이페이지
    @Override
    public MemberEntity getMember(String mid) {
        try {
            return mRepository.findById(mid).orElse(null);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // 회원탈퇴
    @Override
    public int deleteMember(String mid) {
        try {
            MemberEntity member = mRepository.findById(mid).orElse(null);
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
            mRepository.save(member);

            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    // 회원정보수정
    @Override
    public int updateMember(MemberEntity member) {
        try {
            System.out.println(member);
            BCryptPasswordEncoder bcpe = new BCryptPasswordEncoder();
            MemberEntity newMember = mRepository.findById(member.getMId()).orElse(null);
            newMember.setMPw(bcpe.encode(member.getMPw1()));
            newMember.setMAddr(member.getMAddr());
            newMember.setMEmail(member.getMEmail());
            newMember.setMPhone(member.getMPhone());
            mRepository.save(newMember);

            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return -0;
        }

    }

}
