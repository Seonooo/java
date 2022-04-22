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
    private Long aNo;
    // 회원
    @ManyToOne
    @JoinColumn(name = "m_id")
    private MemberEntity memberEntity;
    // 영화 테이블
    @ManyToOne
    @JoinColumn(name = "m_code")
    private MovieEntity movieEntity;
    // 알람상태
    @ManyToOne
    @JoinColumn(name = "as_code")
    private AlramStateEntity alramStateEntity;
}
