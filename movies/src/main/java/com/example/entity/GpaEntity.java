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
@Table(name = "GPA")
@SequenceGenerator(name = "SEQ_GPA", sequenceName = "SEQ_GPA_CODE", allocationSize = 1, initialValue = 1)

public class GpaEntity {
    // 평점 코드
    @Id
    @GeneratedValue(generator = "SEQ_GPA", strategy = GenerationType.SEQUENCE)
    private Long gCode;
    // 평균평점
    private Long gGpa;
    // 짧은 리뷰
    @ManyToOne
    @JoinColumn(name = "r_code")
    private ReviewEntity reviewEntity;
    // 영화 테이블
    // @JsonBackReference
    // @OneToMany(mappedBy = "gpaEntity")
    // private List<MovieEntity> movieEntityList = new ArrayList<>();
}