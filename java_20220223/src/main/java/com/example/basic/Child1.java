package com.example.basic;

public class Child1 extends Parent implements Parent1 {

    public Child1() {
    }

    public Child1(int num) {
        // 부모의 생성자 호출(super)
        super(num);
    }

    @Override // 부모의 기능을 재구현
    public void printNum() {
        super.printNum();
        // 부모의 메소드가 호출
        System.out.println("child1에서 출력");
    }

    
    public void printChild1() {
        System.out.println("printChild1 출력");
    }


    @Override
    public void printNum1() {
        System.out.println("printChild1-printNum1");
    }

    @Override
    public void printA() {
        
    }

    @Override
    public void printB() {
        
    }
    
}