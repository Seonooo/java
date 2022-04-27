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

@Entity
@Data
@Table(name = "VISITOR")
public class VisitorEntity {
    // 비회원 연락처
    @Id
    @Column(length = 20)
    private String vPhone;
    // 비회원 이름
    @Column(length = 30)
    private String vName;
    // 비회원 암호
    @Column(length = 100)
    private String vPw;
    // 비회원예매
    // @JsonBackReference
    // @OneToMany(mappedBy = "visitorEntity")
    // private List<VisitorTicketEntity> visitorTicketEntityList = new
    // ArrayList<>();
}
