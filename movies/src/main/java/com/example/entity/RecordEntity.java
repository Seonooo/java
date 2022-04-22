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
@Table(name = "RECORD")
@SequenceGenerator(name = "SEQ_RECORD", sequenceName = "SEQ_RECORD_CODE", allocationSize = 1, initialValue = 1)

public class RecordEntity {
    // 기록코드
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_RECORD")
    private Long rCode;
    // 등록일
    @DateTimeFormat(pattern = "yyyy-mm-dd hh:mm:ss.sss")
    @CreationTimestamp // CURRENT_DATE
    private Date rRegdate;
    // 회원예매
    @ManyToOne
    @JoinColumn(name = "t_no")
    private TicketEntity ticketEntity;
}
