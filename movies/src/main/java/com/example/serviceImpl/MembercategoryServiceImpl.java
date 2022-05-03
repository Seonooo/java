package com.example.serviceImpl;

import com.example.entity.CategoryEntity;
import com.example.entity.MemberEntity;
import com.example.entity.MembercategoryEntity;
import com.example.repository.MembercategoryRepository;
import com.example.service.MembercategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MembercategoryServiceImpl implements MembercategoryService {
    @Autowired
    MembercategoryRepository mRepository;

    @Override
    public int insertMembercategory(long code, String mId) {
        try {
            MemberEntity member = new MemberEntity();
            CategoryEntity category = new CategoryEntity();
            MembercategoryEntity membercategory = new MembercategoryEntity();

            member.setMid(mId);
            category.setCcode(code);
            membercategory.setMember(member);
            membercategory.setCategory(category);

            mRepository.save(membercategory);

            return 1;
        } catch (Exception e) {
            return 0;
        }
    }
}
