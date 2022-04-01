package com.example.service;

import java.util.List;

import com.example.dto.MemberDTO;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService {

    // xml로 되어 있는 mapper을 호출함.
    @Autowired
    SqlSessionFactory sqlFactory;

    @Override
    public int insertMember(MemberDTO member) {
        // namespace가 Member이고 id가 insertMemberOne인 항목을 호출함.
        return sqlFactory.openSession().insert("Member.insertMemberOne", member);
    }

    @Override
    public List<MemberDTO> selectMemberList() {
        return sqlFactory.openSession().selectList("Member.selectMemberList");
    }

    @Override
    public int deleteMember(String uemail) {
        return sqlFactory.openSession().delete("Member.deleteMemberOne", uemail);
    }

    @Override
    public MemberDTO selectMemberOne(String uemail) {
        return sqlFactory.openSession().selectOne("Member.selectMemberOne", uemail);
    }

    @Override
    public int updateMember(MemberDTO member) {

        return sqlFactory.openSession().update("Member.updateMemberOne", member);
    }

    @Override
    public MemberDTO selectMemberLogin(MemberDTO member) {
        // xml mapper호출 Member id가 selectMemberLogin인것
        return sqlFactory.openSession().selectOne("Member.selectMemberLogin", member);
    }

}
