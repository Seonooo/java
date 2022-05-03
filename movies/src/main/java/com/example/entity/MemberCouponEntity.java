package com.example.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "MEMBER_COUPON")
@SequenceGenerator(name = "MCP_NO", sequenceName = "SEQ_MEMBER_COUPON_NO", allocationSize = 1, initialValue = 1)

public class MemberCouponEntity {
    // 번호
    @Id
    @GeneratedValue(generator = "MCP_NO", strategy = GenerationType.SEQUENCE)
    private Long mcpno;
    // 회원
    @ManyToOne
    @JoinColumn(name = "mid")
    private MemberEntity memberEntity;
    // 쿠폰
    @ManyToOne
    @JoinColumn(name = "cpcode")
    private CouponEntity couponEntity;
}
