package com.example.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "MOVIESTATE")
public class MovieStateEntity {

    // 영화상태코드
    // 0 => 개봉전, 1 => 상영중, 2 => 상영종료
    @Id
    private Long mscode;

    // 상태
    @Column(length = 20)
    private String msstate;
}
