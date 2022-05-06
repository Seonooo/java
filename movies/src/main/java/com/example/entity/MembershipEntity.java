package com.example.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "MEMBERSHIP")
public class MembershipEntity {
    // 회원등급코드
    // 브론즈 / 실버 / 골드 / 플레티넘 ...
    @Id
    @Column(length = 10)
    private String mscode;
    // 회원등급
    @Column(length = 20)
    private String msmembership;
    // // 회원
    // @JsonBackReference
    // @OneToMany(mappedBy = "membershipEntity")
    // private List<MemberEntity> memberEntityList = new ArrayList<>();
}
