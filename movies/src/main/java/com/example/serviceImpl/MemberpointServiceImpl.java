package com.example.serviceImpl;

import com.example.entity.MemberEntity;
import com.example.entity.MemberpointEntity;
import com.example.repository.MemberpointRepository;
import com.example.service.MemberpointService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MemberpointServiceImpl implements MemberpointService {

    @Autowired
    MemberpointRepository mRepository;

    @Override
    public int insertMemberpoint(String mId) {
        try {
            MemberEntity member = new MemberEntity();
            MemberpointEntity membership = new MemberpointEntity();
            member.setMid(mId);
            membership.setMember(member);
            mRepository.save(membership);
            return 1;
        } catch (Exception e) {
            return 0;
        }

    }

}
