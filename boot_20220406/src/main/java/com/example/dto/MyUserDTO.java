package com.example.dto;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class MyUserDTO extends User {

    private static final long serialVersionUID = 1L;
    private String username = null; // 아이디
    private String password = null; // 암호
    private String userphone = null; // 연락처
    private String name = null; // 이름

    public MyUserDTO(String username, String password, Collection<? extends GrantedAuthority> authorities,
            String userphone, String name) {
        super(username, password, authorities);
        this.username = username;
        this.password = password;
        this.userphone = userphone;
        this.name = name;

    }

}
