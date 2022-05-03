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

@Entity
@Data
@Table(name = "BOARD")
@SequenceGenerator(name = "SEQ_BOARD", sequenceName = "SEQ_BOARD_NO", allocationSize = 1, initialValue = 1)
public class BoardEntity {
    // 게시글번호
    @Id
    @GeneratedValue(generator = "SEQ_BOARD", strategy = GenerationType.SEQUENCE)
    private Long bno;
    // 타입
    private Long btype;
    // 글제목
    @Column(length = 100)
    private String btitle;
    // 내용
    @Lob
    private String bcontent;
    // 작성일
    @DateTimeFormat(pattern = "yyyy-mm-dd hh:mm:ss.sss")
    @CreationTimestamp
    private Date bregdate;
    // 조회수
    private Long bhit = 0L;
    // 추천수
    private Long blike = 0L;
    // 회원
    @ManyToOne
    @JoinColumn(name = "mid")
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
