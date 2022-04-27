package com.example.serviceImpl;

import java.util.Collection;

import com.example.entity.MemberEntity;
import com.example.repository.MemberRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

// 로그인에서 버튼을 누르면 컨트롤을 통과해서 서비스로 이메일이 전달됨
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    MemberRepository mRepository;

    @Override
    public UserDetails loadUserByUsername(String mId) throws UsernameNotFoundException {
        System.out.println("UserDetails" + mId);

        // id통해서 유저 객체 가져오기
        MemberEntity member = mRepository.findBymId(mId);

        String[] strRole = { member.getMRole() };

        Collection<GrantedAuthority> mRoles = AuthorityUtils.createAuthorityList(strRole);

        // 아이디, 비밀번호, 권한 찾기
        User user = new User(mId, member.getMPw(), mRoles);
        System.out.println(user);
        return user;
    }

}