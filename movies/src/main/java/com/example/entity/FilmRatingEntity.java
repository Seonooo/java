package com.example.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "FILMRATING")
public class FilmRatingEntity {
    // 등급코드
    @Id
    private Long fCode;
    // 등급
    @Column(length = 50)
    private String fFilmrating;
    // 영화 테이블
    // @JsonBackReference
    // @OneToMany(mappedBy = "filmratingEntity")
    // private List<MovieEntity> movieEntityList = new ArrayList<>();
}
