package com.example.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;

@Data
@Entity
@Table(name = "MEMBERSHIP")
public class MembershipEntity {
    // 회원등급코드
    @Id
    @Column(length = 10)
    private String msCode;
    // 회원등급
    @Column(length = 20)
    private String msMembership;
    // // 회원
    // @JsonBackReference
    // @OneToMany(mappedBy = "membershipEntity")
    // private List<MemberEntity> memberEntityList = new ArrayList<>();
}
