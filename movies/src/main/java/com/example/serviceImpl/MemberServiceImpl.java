package com.example.serviceImpl;

import com.example.entity.MemberEntity;
import com.example.repository.MemberRepository;
import com.example.service.MemberService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MemberServiceImpl implements MemberService {

    @Autowired
    MemberRepository mRepository;

    @Override
    public int insertMember(MemberEntity member) {
        try {
            mRepository.save(member);
            return 1;
        } catch (Exception e) {
            return 0;
        }

    }

}
