package com.example.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Entity
@Table(name = "COMMENT")
@SequenceGenerator(name = "SEQ_COMMENT", sequenceName = "SEQ_COMMENT_NO", allocationSize = 1, initialValue = 1)

public class CommentEntity {
    // 댓글번호
    @Id
    @GeneratedValue(generator = "SEQ_COMMENT", strategy = GenerationType.SEQUENCE)
    private Long cno;
    // 댓글내용
    @Lob
    private String ccontent;
    // 등록일
    @DateTimeFormat(pattern = "yyyy-mm-dd hh:mm:ss.sss")
    @CreationTimestamp
    private Date cregdate;
    // 게시판
    @ManyToOne
    @JoinColumn(name = "bno")
    private BoardEntity boardEntity;
}