package com.example.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString

@Document(collection = "member3")

public class Member {

    @Id
    private String userid = null;

    private String userpw = null;

    private String userpw1 = null;

    private String username = null;

    private int userage = 0;

    // 일시적 (컬럼으로 만들어 지지 않음)
    // 개발에서 필요한 정보를 보관하기 위한 용도
    @Transient
    private String newPw = null;
}
