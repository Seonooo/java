package com.example.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
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
    private Long mcode;
    // 제목
    @Column(length = 250)
    private String mtitle;
    // 개봉일
    @DateTimeFormat(pattern = "yyyy-mm-dd hh:mm:ss.sss")
    private Date mrelease;
    // 마감일
    @DateTimeFormat(pattern = "yyyy-mm-dd hh:mm:ss.sss")
    private Date mdeadline;
    // 평점
    private Float mgpa;
    // 상영시간
    @Column(length = 10)
    private String mtime;
    // 순위
    private Long mrank;
    // 등록일
    @DateTimeFormat(pattern = "yyyy-mm-dd hh:mm:ss.sss")
    @CreationTimestamp // CURRENT_DATE
    private Date mregdate;
    // 배우
    private String mactor;
    // 감독
    private String mdirector;
    // 영화좋아요수
    private Long mlike;
    // 국가
    @ManyToOne
    @JoinColumn(name = "ncode")
    private NationEntity nationEntity;
    // 장르
    @ManyToOne
    @JoinColumn(name = "ccode")
    private CategoryEntity categoryEntity;
    // 등급
    @ManyToOne
    @JoinColumn(name = "fcode")
    private FilmRatingEntity filmratingEntity;
    // 관람객 평점
    @ManyToOne
    @JoinColumn(name = "gcode")
    private GpaEntity gpaEntity;
    // 짧은줄거리
    @Lob
    private String mshot;
    // 긴줄거리
    @Lob
    private String mlong;
    // 영화상태
    @ManyToOne
    @JoinColumn(name = "mscode")
    private MovieStateEntity movieStateEntity;
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
