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
@Table(name = "COUPON")
@SequenceGenerator(name = "SEQ_COUPON", sequenceName = "SEQ_COUPON_CODE", allocationSize = 1, initialValue = 1)

public class CouponEntity {
    // 쿠폰코드
    @Id
    @GeneratedValue(generator = "SEQ_COUPON", strategy = GenerationType.SEQUENCE)
    private Long cpcode;
    // 쿠폰타입
    @Column(length = 20)
    private String cptype;
    // 쿠폰이름
    @Column(length = 100)
    private String cptitle;
    // 쿠폰내용
    @Lob
    private String cpcontent;
    // 쿠폰 발행일(등록일)
    @DateTimeFormat(pattern = "yyyy-mm-dd hh:mm:ss.sss")
    @CreationTimestamp
    private Date cprelease;
    // 쿠폰 만료일
    @DateTimeFormat(pattern = "yyyy-mm-dd hh:mm:ss.sss")
    private Date cpdeadline;
    // 이벤트
    @ManyToOne
    @JoinColumn(name = "ecode")
    private EventEntity eventEntity;
    // 회원 보유 쿠폰
    // @JsonBackReference
    // @OneToMany(mappedBy = "couponEntity")
    // private List<MemberCouponEntity> memberCouponEntityList = new ArrayList<>();
}
