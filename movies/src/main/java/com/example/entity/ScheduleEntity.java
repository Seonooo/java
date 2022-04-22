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
@Table(name = "SCHEDULE")
@SequenceGenerator(name = "SEQ_SCHEDULE", sequenceName = "SEQ_SCHEDULE_NO", allocationSize = 1, initialValue = 1)
public class ScheduleEntity {
    // 스케줄번호
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_SCHEDULE")
    private Long sNo;
    // 날짜
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    @CreationTimestamp // CURRENT_DATE
    private Date sDate;
    // 영화시작시간
    @DateTimeFormat(pattern = "yyyy-mm-dd hh:mm:ss.sss")
    private Date sStart;
    // 영화종료시간
    @DateTimeFormat(pattern = "yyyy-mm-dd hh:mm:ss.sss")
    private Date sEnd;
    // 등록일
    @DateTimeFormat(pattern = "yyyy-mm-dd hh:mm:ss.sss")
    @CreationTimestamp // CURRENT_DATE
    private Date sRegdate;
    // 상영관
    @ManyToOne
    @JoinColumn(name = "th_code")
    private TheaterEntity theaterEntity;
    // 영화 테이블
    @ManyToOne
    @JoinColumn(name = "m_code")
    private MovieEntity movieEntity;
}
