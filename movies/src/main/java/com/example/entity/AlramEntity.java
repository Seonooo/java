package com.example.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "ALRAM")
public class AlramEntity {
    // 알람번호
    @Id
    private Long ano;
    // 회원
    @ManyToOne
    @JoinColumn(name = "mid")
    private MemberEntity memberEntity;
    // 영화 테이블
    @ManyToOne
    @JoinColumn(name = "mcode")
    private MovieEntity movieEntity;
    // 알람상태
    @ManyToOne
    @JoinColumn(name = "ascode")
    private AlramStateEntity alramStateEntity;
}
