package com.example.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "TEATHER")
@SequenceGenerator(name = "TH_CODE", sequenceName = "SEQ_THEATHER_CODE", allocationSize = 1, initialValue = 1)
public class TheaterEntity {

    @Id
    @GeneratedValue(generator = "TH_CODE", strategy = GenerationType.SEQUENCE) // 상영관 코드
    private Long code;

    @Column(name = "TH_MAXIMUN") // 최대 인원수
    private Long maximum;

    @Column(name = "TH_PRICE") // 가격
    private Long price;

    @Column(name = "TH_STATE") // 상영 가능 여부
    private Long state;

    @Lob
    @Column(name = "TH_CONTENT") // 상영관 설명
    private String content;

    @Column(name = "TH_COUNT") // 관람 인원 수
    private Long count;

    @Column(name = "TH_TYPE", length = 50) // 상영관 타입(nomal, couple, family)
    private String type;
}