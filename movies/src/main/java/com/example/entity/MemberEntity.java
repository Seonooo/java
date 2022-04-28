package com.example.entity;

// import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
// import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

// import com.fasterxml.jackson.annotation.JsonBackReference;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Entity
@Data
@Table(name = "MEMBER")
public class MemberEntity {
    // 아이디
    @Id
    @Column(length = 50)
    private String mId;
    // 비밀번호
    @Column(length = 100)
    private String mPw;
    // 이름
    @Column(length = 50)
    private String mName;
    // 이메일
    @Column(length = 100)
    private String mEmail;
    // 연락처
    @Column(length = 20)
    private String mPhone;
    // 권한
    @Column(length = 10)
    private String mRole;
    // 주소
    @Column(length = 100)
    private String mAddr;
    // 닉네임
    @Column(length = 50)
    private String mNickname;
    // 생일
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private Date mBirth;
    // 성별
    @Column(length = 10)
    private String mGender;
    // 이미지(프로필)
    @Lob
    private String mProfile;
    // 이미지타입
    @Column(length = 50)
    private String mProfiletype;
    // 이미지크기
    private Long mProfilesize;
    // 이미지이름
    @Column(length = 100)
    private String mProfilename;
    // 가입일
    @DateTimeFormat(pattern = "yyyy-mm-dd hh:mm:ss.sss")
    @CreationTimestamp // CURRENT_DATE
    private Date mRegdate;
    // 회원등급
    @ManyToOne
    @JoinColumn(name = "ms_code")
    private MembershipEntity membershipEntity;
    // 게시판
    // @JsonBackReference
    // @OneToMany(mappedBy = "memberEntity")
    // private List<BoardEntity> boardEntityList = new ArrayList<>();
    // 회원예매
    // @JsonBackReference
    // @OneToMany(mappedBy = "memberEntity")
    // private List<TicketEntity> ticketEntityList = new ArrayList<>();
    // 알람
    // @JsonBackReference
    // @OneToMany(mappedBy = "memberEntity")
    // private List<AlramEntity> alramEntityList = new ArrayList<>();
    // 회원 보유 쿠폰
    // @JsonBackReference
    // @OneToMany(mappedBy = "memberEntity")
    // private List<MemberCouponEntity> memberCouponEntityList = new ArrayList<>();
    // 장바구니
    // @JsonBackReference
    // @OneToMany(mappedBy = "memberEntity")
    // private List<CartEntity> cartEntityList = new ArrayList<>();

    // 테이블에 생성되지 않음, 매핑도 안됨, 임시
    // 년
    @Transient
    private String year;
    // 월
    @Transient
    private String month;
    // 일
    @Transient
    private String day;
    // 변경암호
    @Transient
    private String mPw1;

    // 장르코드
    @Transient
    private List<Long> categoryCode;
}