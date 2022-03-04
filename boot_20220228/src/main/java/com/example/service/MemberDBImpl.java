package com.example.service;

import java.util.List;

import com.example.entity.Member;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

// DB연동을 실제 수행하는 구현부
// 구현부는 프레임워크에 따라서 안만듬.
@Service
public class MemberDBImpl implements MemberDB {

    // 환경설정으로 생성된 객체를 가져옴
    @Autowired
    private MongoTemplate mongodb;

    @Override
    public Member insertMember(Member member) {
        try {
            return mongodb.insert(member);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Member> selectListMember() {
        try {
            Query query = new Query();
            return mongodb.find(query, Member.class);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public int deleteMember(String id) {
        try {
            Member member = new Member();
            member.setUserid(id);
            DeleteResult result = mongodb.remove(member);
            if (result.getDeletedCount() == 1L) {
                return 1;
            }
            return 0;
        } catch (Exception e) {
            return -1;
        }
    }

    @Override
    public Member selectOneMember(String id) {
        try {
            Query query = new Query();
            Criteria criteria = Criteria.where("_id").is(id);
            query.addCriteria(criteria);
            return mongodb.findOne(query, Member.class);
        } catch (Exception e) {
            e.printStackTrace(); // 개발자를 위한 출력(debug용)
            return null;
        }
    }

    @Override
    public int updateMember(Member member) {
        try {
            // query조건
            Query query = new Query();
            Criteria criteria = Criteria.where("_id").is(member.getUserid());
            query.addCriteria(criteria);

            // update수정할 설정
            Update update = new Update();
            update.set("username", member.getUsername());
            update.set("userage", member.getUserage());
            UpdateResult result = mongodb.updateFirst(query, update, Member.class);
            if (result.getModifiedCount() == 1L) {
                return 1;
            }
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }

    }

    @Override
    public Member selectLogin(Member member) {
        try {
            Query query = new Query();
            query.addCriteria(Criteria.where("_id").is(member.getUserid()));
            query.addCriteria(Criteria.where("userpw").is(member.getUserpw()));

            return mongodb.findOne(query, Member.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public long updateMemberPassword(Member member) {
        try {
            Query query = new Query();
            query.addCriteria(Criteria.where("_id").is(member.getUserid()));
            query.addCriteria(Criteria.where("userpw").is(member.getUserpw()));
            Update update = new Update();
            update.set("userpw", member.getNewPw());
            update.set("userpw1", member.getNewPw());
            UpdateResult result = mongodb.updateFirst(query, update, Member.class);
            return result.getModifiedCount();
        } catch (Exception e) {
            e.printStackTrace();
            return -1L;
        }
    }

    @Override
    public long withdrawMember(Member member) {
        try {
            Query query = new Query();
            query.addCriteria(Criteria.where("_id").is(member.getUserid()));
            query.addCriteria(Criteria.where("userpw").is(member.getUserpw()));
            DeleteResult result = mongodb.remove(query, Member.class);
            return result.getDeletedCount();
        } catch (Exception e) {
            e.printStackTrace();
            return -1L;
        }
    }

}
