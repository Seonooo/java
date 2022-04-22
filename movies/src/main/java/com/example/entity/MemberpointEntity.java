package com.example.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;

@Data
@Entity
@Table(name = "MEMBERPOINT")
@SequenceGenerator(name = "SEQ_MEMBERPOINT", sequenceName = "SEQ_MEMBERPOINT_CODE", allocationSize = 1, initialValue = 1001)
public class MemberpointEntity {
    // 회원포인트코드
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_MEMBERPOINT")
    private Long mpCode;
    // 누적포인트
    private Long mpStackpoint;
    // 현재포인트
    private Long mpNowpoint;
    // 사용포인트
    private Long mpUsepoint;
    // 회원
    @JsonBackReference
    @OneToMany(mappedBy = "memberpointEntity")
    private List<MemberEntity> memberEntityList = new ArrayList<>();
}