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
@Table(name = "BOARDIMG")
@SequenceGenerator(name = "SEQ_BOARDIMG", sequenceName = "SEQ_BOARDIMG_NO", allocationSize = 1, initialValue = 1)
public class BoardimgEntity {
    // 이미지번호
    @Id
    @GeneratedValue(generator = "SEQ_BOARDIMG", strategy = GenerationType.SEQUENCE)
    private Long bino;
    // 이미지
    @Lob
    private byte[] biimage;
    // 이미지타입
    @Column(length = 50)
    private String biimagetype;
    // 이미지크기
    private Long biimagesize;
    // 이미지이름
    @Column(length = 100)
    private String biimagename;
    // 등록일
    @DateTimeFormat(pattern = "yyyy-mm-dd hh:mm:ss.sss")
    @CreationTimestamp
    private Date biregdate;
    // 게시판
    @ManyToOne
    @JoinColumn(name = "bno")
    private BoardEntity boardEntity;
}
