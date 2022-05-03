package com.example.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "MEMBERPOINT")
@SequenceGenerator(name = "SEQ_MEMBERPOINT", sequenceName = "SEQ_MEMBERPOINT_CODE", allocationSize = 1, initialValue = 1001)
public class MemberpointEntity {
    // 회원포인트코드
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_MEMBERPOINT")
    private Long mpcode;
    // 누적포인트
    private Long mpstackpoint = 0L;
    // 현재포인트
    private Long mpnowpoint = 0L;
    // 사용포인트
    private Long mpusepoint = 0L;
    // 회원
    @ManyToOne
    @JoinColumn(name = "mid")
    private MemberEntity member;
}