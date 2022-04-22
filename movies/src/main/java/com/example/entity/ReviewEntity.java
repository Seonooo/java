package com.example.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Entity
@Table(name = "REVIEW")
@SequenceGenerator(name = "SEQ_REVIEW", sequenceName = "SEQ_REVIEW_CODE", allocationSize = 1, initialValue = 1)
public class ReviewEntity {
    // 리뷰 코드
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_REVIEW")
    private Long rCode;
    // 리뷰 내용
    @Lob
    private String rContent;
    // 평점
    private Long rGpa;
    // 좋아요수
    private Long rLike;
    // 등록일
    @DateTimeFormat(pattern = "yyyy-mm-dd hh:mm:ss.sss")
    @CreationTimestamp // CURRENT_DATE
    private Date rRegdate;
    // 회원예매
    @ManyToOne
    @JoinColumn(name = "t_no")
    private TicketEntity ticketEntity;
    // 관람객 평점
    @JsonBackReference
    @OneToMany(mappedBy = "reviewEntity")
    private List<GpaEntity> gpaEntityList = new ArrayList<>();
}
