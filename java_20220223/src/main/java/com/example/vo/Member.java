// main / java / com / example / vo
package com.example.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// 파일명과 클래스명은 일치 : Member.java
/*
// 변수
const member = {
    _id:'a@a,com', 
    name:'a', 
    role:'CUSTOMER', 
    age : 13,
    regdate:'2022-02-22' 
}
*/
@Getter
@Setter
@NoArgsConstructor

public class Member {
    // 1. 변수
    private String id = null;
    private String name = null;
    private String role = null;
    private int age = 999;
    private String regdate = null;

    // 2. getter/setter

    // 3.toString만들기
    @Override
    public String toString() {
        return "Member [age=" + age + ", id=" + id + ", name=" + name + ", regdate=" + regdate + ", role=" + role + "]";
    }

}