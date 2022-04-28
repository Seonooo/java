package com.example.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "NATION")
public class NationEntity {
    // 국가코드
    @Id
    private String nCode;
    // 국가
    @Column(length = 50)
    private String nNation;
    // // 영화 테이블
    // @JsonBackReference
    // @OneToMany(mappedBy = "nationEntity")
    // private List<MovieEntity> movieEntityList = new ArrayList<>();
}
