package com.example.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Entity
@Table(name = "EVENT")
@SequenceGenerator(name = "SEQ_EVENT", sequenceName = "SEQ_EVENT_CODE", allocationSize = 1, initialValue = 1)

public class EventEntity {
    // 이벤트코드
    @Id
    @GeneratedValue(generator = "SEQ_EVENT", strategy = GenerationType.SEQUENCE) // 이벤트 코드
    private Long ecode;
    // 이벤트내용
    @Lob
    private String econtent;
    // 이벤트제목
    @Column(length = 50)
    private String etitle;
    // 이벤트시작일
    @DateTimeFormat(pattern = "yyyy-mm-dd hh:mm:ss.sss")
    private Date erelease;
    // 이벤트종료일
    @DateTimeFormat(pattern = "yyyy-mm-dd hh:mm:ss.sss")
    private Date edeadline;
    // 이벤트등록일
    @DateTimeFormat(pattern = "yyyy-mm-dd hh:mm:ss.sss")
    @CreationTimestamp // CURRENT_DATE
    private Date eregdate;
    // 쿠폰
    // @JsonBackReference
    // @OneToMany(mappedBy = "eventEntity")
    // private List<CouponEntity> couponEntityList = new ArrayList<>();
}