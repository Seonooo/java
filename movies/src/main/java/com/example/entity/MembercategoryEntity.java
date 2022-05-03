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
@Table(name = "MEMBERCATEGORY")
@SequenceGenerator(name = "SEQ_MEMBERCATEGORY", sequenceName = "SEQ_MEMBERCATEGORY_NO", allocationSize = 1, initialValue = 1)
public class MembercategoryEntity {
    // 회원선호장르번호
    @Id
    @GeneratedValue(generator = "SEQ_MEMBERCATEGORY", strategy = GenerationType.SEQUENCE)
    private Long mccode;
    // 장르
    @ManyToOne
    @JoinColumn(name = "ccode")
    private CategoryEntity category;
    // 회원
    @ManyToOne
    @JoinColumn(name = "mid")
    private MemberEntity member;
}
