package com.example.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Entity
@Data
@Table(name = "BOARD")
@SequenceGenerator(name = "SEQ_BOARD", sequenceName = "SEQ_BOARD_NO", allocationSize = 1, initialValue = 1)
public class BoardEntity {
    // 게시글번호
    @Id
    @GeneratedValue(generator = "SEQ_BOARD", strategy = GenerationType.SEQUENCE)
    private Long bNo;
    // 타입
    private Long bType;
    // 글제목
    @Column(length = 100)
    private String bTitle;
    // 내용
    @Lob
    private String bContent;
    // 작성일
    @DateTimeFormat(pattern = "yyyy-mm-dd hh:mm:ss.sss")
    @CreationTimestamp
    private Date bRegdate;
    // 조회수
    private Long bHit;
    // 추천수
    private Long bLike;
    // 회원
    @ManyToOne
    @JoinColumn(name = "m_id")
    private MemberEntity memberEntity;
    // // 게시판 이미지
    // @JsonBackReference
    // @OneToMany(mappedBy = "boardEntity")
    // private List<BoardimgEntity> boardimgEntityList = new ArrayList<>();
    // // 댓글
    // @JsonBackReference
    // @OneToMany(mappedBy = "boardEntity")
    // private List<CommentEntity> commentEntityList = new ArrayList<>();
}
