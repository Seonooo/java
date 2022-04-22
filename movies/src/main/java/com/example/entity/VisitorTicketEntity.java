package com.example.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Entity
@Table(name = "VISITOR_TICKET")
@SequenceGenerator(name = "SEQ_VTCODE", sequenceName = "SEQ_VISITOR_CODE", allocationSize = 1, initialValue = 1)
public class VisitorTicketEntity {
    // 비회원 예매번호
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_VTCODE")
    private Long vtCode;
    // 예매일
    @DateTimeFormat(pattern = "yyyy-mm-dd hh:mm:ss.sss")
    @CreationTimestamp // CURRENT_DATE
    private Date vtRegdate;
    // 영화 테이블
    @ManyToOne
    @JoinColumn(name = "m_code")
    private MovieEntity movieEntity;
    // 상영관
    @ManyToOne
    @JoinColumn(name = "th_code")
    private TheaterEntity theaterEntity;
    // 비회원
    @ManyToOne
    @JoinColumn(name = "v_phone")
    private VisitorEntity visitorEntity;
    // 예매상태
    @ManyToOne
    @JoinColumn(name = "ts_code")
    private TicketStateEntity ticketStateEntity;
}
