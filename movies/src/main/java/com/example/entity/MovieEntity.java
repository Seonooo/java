package com.example.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Entity
@Table(name = "MOVIE")
public class MovieEntity {
    // 영화코드
    @Id
    private Long mCode;
    // 제목
    @Column(length = 250)
    private String mTitle;
    // 개봉일
    @DateTimeFormat(pattern = "yyyy-mm-dd hh:mm:ss.sss")
    private Date mRelease;
    // 마감일
    @DateTimeFormat(pattern = "yyyy-mm-dd hh:mm:ss.sss")
    private Date mDeadline;
    // 평점
    private Long mGpa;
    // 상영시간
    @Column(length = 10)
    private String mTime;
    // 순위
    private Long mRank;
    // 등록일
    @DateTimeFormat(pattern = "yyyy-mm-dd hh:mm:ss.sss")
    @CreationTimestamp // CURRENT_DATE
    private Date mRegdate;
    // 배우
    private Long mActor;
    // 감독
    private Long mDirector;
    // 영화좋아요수
    private Long mLike;
    // 국가
    @ManyToOne
    @JoinColumn(name = "n_code")
    private NationEntity nationEntity;
    // 장르
    @ManyToOne
    @JoinColumn(name = "c_code")
    private CategoryEntity categoryEntity;
    // 등급
    @ManyToOne
    @JoinColumn(name = "f_code")
    private FilmRatingEntity filmratingEntity;
    // 관람객 평점
    @ManyToOne
    @JoinColumn(name = "g_code")
    private GpaEntity gpaEntity;
    // 줄거리
    @ManyToOne
    @JoinColumn(name = "ct_code")
    private ContentEntity contentEntity;
    // 포스터
    // @JsonBackReference
    // @OneToMany(mappedBy = "movieEntity")
    // private List<PosterEntity> posterEntityList = new ArrayList<>();
    // // 회원예매
    // @JsonBackReference
    // @OneToMany(mappedBy = "movieEntity")
    // private List<TicketEntity> ticketEntityList = new ArrayList<>();
    // // 스케줄
    // @JsonBackReference
    // @OneToMany(mappedBy = "movieEntity")
    // private List<ScheduleEntity> scheduleEntityList = new ArrayList<>();
    // // 비회원예매
    // @JsonBackReference
    // @OneToMany(mappedBy = "movieEntity")
    // private List<VisitorTicketEntity> visitorTicketEntityList = new
    // ArrayList<>();
    // // 알람
    // @JsonBackReference
    // @OneToMany(mappedBy = "movieEntity")
    // private List<AlramEntity> alramEntityList = new ArrayList<>();
}
