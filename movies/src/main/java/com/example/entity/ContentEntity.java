package com.example.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;

@Data
@Entity
@Table(name = "CONTENT")
@SequenceGenerator(name = "SEQ_CONTENT", sequenceName = "SEQ_CONTENT_CODE", allocationSize = 1, initialValue = 1)

public class ContentEntity {

    // 줄거리코드
    @Id
    @GeneratedValue(generator = "SEQ_CONTENT", strategy = GenerationType.SEQUENCE)
    private Long ctCode;
    // 짧은줄거리
    @Lob
    private String ctShot;
    // 긴줄거리
    @Lob
    private String ctLong;
    // // 영화 테이블
    // @JsonBackReference
    // @OneToMany(mappedBy = "contentEntity")
    // private List<MovieEntity> movieEntityList = new ArrayList<>();
}
