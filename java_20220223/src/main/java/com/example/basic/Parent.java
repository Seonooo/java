package com.example.basic;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Parent {
    // 클래스의 공통변수
    private int num = 0;

    // 클래스와 일치 : 생성자, 여러개 가능(오브로딩)
    public Parent(){

    }

    // 생성자
    public Parent(int num) {
        this.num = num;
    }

    // getter, setter

    // 메소드
    public void printNum(){
        System.out.println(this.num);
    }

    // 메소드는 기능구현 x  자식은 반드시 기능을 구현해야함
    // 설계용
    public abstract void printNum1();
    
}
