package com.example.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
@Table(name = "TICKET")
@SequenceGenerator(name = "SEQ_TICKET", sequenceName = "SEQ_TICKET_NO", allocationSize = 1, initialValue = 1)
public class TicketEntity {
    // 예매번호
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_TICKET")
    private Long tNo;
    // 예매일
    @DateTimeFormat(pattern = "yyyy-mm-dd hh:mm:ss.sss")
    @CreationTimestamp // CURRENT_DATE
    private Date tRegdate;
    // 영화 테이블
    @ManyToOne
    @JoinColumn(name = "m_code")
    private MovieEntity movieEntity;
    // 회원
    @ManyToOne
    @JoinColumn(name = "m_id")
    private MemberEntity memberEntity;
    // 상영관
    @ManyToOne
    @JoinColumn(name = "th_code")
    private TheaterEntity theaterEntity;
    // 예매상태
    @ManyToOne
    @JoinColumn(name = "ts_code")
    private TicketStateEntity ticketStateEntity;
    // 예매기록
    // @JsonBackReference
    // @OneToMany(mappedBy = "ticketEntity")
    // private List<RecordEntity> recordEntityList = new ArrayList<>();
    // // 짧은 리뷰
    // @JsonBackReference
    // @OneToMany(mappedBy = "ticketEntity")
    // private List<ReviewEntity> reviewEntityList = new ArrayList<>();
}
