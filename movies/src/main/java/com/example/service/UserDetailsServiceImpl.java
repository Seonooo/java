package com.example.service;

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

// 로그인에서 버튼을 누르면 서비스로 이메일이 전달됨.
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    MemberRepository mRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("loadUserByUsername ->" + username);
        MemberEntity member = mRepository.findById(username).orElse(null);

        // System.out.println("loadUserByUsername -> member" + member.toString());
        // 권한정보를 문자열 배열로 만듦
        String[] strRole = { member.getMrole() };

        // String 배열 권한을 Collection<GrantedAuthority> 변환함
        Collection<GrantedAuthority> role = AuthorityUtils.createAuthorityList(strRole);

        User user = new User(member.getMid(), member.getMpw(), role);
        // System.out.println("UserDetailsService : " + username);
        return user;
    }

}
