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

    @Override
    public int updateprofile(MemberEntity member) {
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
            member.setMpw(null);
            member.setMname(null);
            member.setMemail(null);
            member.setMphone(null);
            member.setMrole(null);
            member.setMaddr(null);
            member.setMbirth(new Date());
            member.setMgender(null);
            member.setMprofile(null);
            member.setMprofiletype(null);
            member.setMprofilesize(null);
            member.setMprofilename(null);
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
            BCryptPasswordEncoder bcpe = new BCryptPasswordEncoder();
            MemberEntity newMember = mRepository.findById(member.getMid()).orElse(null);
            newMember.setMpw(bcpe.encode(member.getMPw1()));
            newMember.setMaddr(member.getMaddr());
            newMember.setMemail(member.getMemail());
            newMember.setMphone(member.getMphone());
            mRepository.save(newMember);

            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return -0;
        }

    }

}
