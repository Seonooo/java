package com.example.entity;

import java.util.Date;

import javax.persistence.Column;
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
import lombok.ToString;

@Data
@Entity
@Table(name = "BOARD10_REPLY")
@SequenceGenerator(name = "SEQ1", sequenceName = "SEQ_BOARD10_REPLY_RNO", allocationSize = 1, initialValue = 1)
public class BoardReplyEntity {

    @Id
    @Column(name = "RNO")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ1")
    private long no;

    @Column(name = "RCONTENT", length = 300)
    private String content;

    @Column(name = "RWRITER", length = 50)
    private String writer;

    @DateTimeFormat(pattern = "yyyy-mm-dd hh:mm:ss.sss")
    @CreationTimestamp
    @Column(name = "RREGDATE")
    private Date regdate;

    @ToString.Exclude
    @ManyToOne // 외래키 board10의 기본키만 가져옴
    @JoinColumn(name = "BOARD")
    private BoardEntity board;

}
