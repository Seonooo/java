package com.example.basic;

// public 공용(o)
// private 개인(x)
// 클래스
public class Exam2 {
    // 객체를 직접1개 만들기
    private static Exam2 obj = new Exam2();

    // 생성자 private 외부에서 생성불가
    private Exam2(){

    }

    // 메소드 객체가 생성되고 호출
    // static 있으면 객체생성 하지않고 호출
    public static Exam2 create(){
        return obj;
    }

}
