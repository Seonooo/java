package com.example.entity;

import java.util.Date;

import javax.persistence.Column;
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
@Table(name = "POSTER")
@SequenceGenerator(name = "SEQ_POSTER", sequenceName = "SEQ_POSTER_CODE", allocationSize = 1, initialValue = 1)
public class PosterEntity {
    // 코드
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_POSTER")
    private Long pCode;
    // 이미지
    @Lob
    private byte[] pImage;
    // 이미지타입
    @Column(length = 50)
    private String pImagetype;
    // 이미지크기
    private Long pImagesize;
    // 이미지이름
    @Column(length = 100)
    private String pImagename;
    // 대표이미지
    private Long pHead;
    // 이미지 url
    private String pImageUrl;
    // 등록일
    @DateTimeFormat(pattern = "yyyy-mm-dd hh:mm:ss.sss")
    @CreationTimestamp // CURRENT_DATE
    private Date pRegdate;
    // 영화 테이블
    @ManyToOne
    @JoinColumn(name = "m_code")
    private MovieEntity movieEntity;
}