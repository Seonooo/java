package com.example.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Entity
@Table(name = "BUY")
@SequenceGenerator(name = "SEQ_BUY", sequenceName = "SEQ_BUY_NO", allocationSize = 1, initialValue = 1)

public class BuyEntity {
    // 구매번호
    @Id
    @GeneratedValue(generator = "SEQ_BUY", strategy = GenerationType.SEQUENCE)
    private Long buyno;
    // 구매일
    @DateTimeFormat(pattern = "yyyy-mm-dd hh:mm:ss.sss")
    @CreationTimestamp
    private Date buyregdate;
    // 구매단계
    @ManyToOne
    @JoinColumn(name = "bscode")
    private BuyStateEntity buyStateEntity;
    // 장바구니
    @ManyToOne
    @JoinColumn(name = "cano")
    private CartEntity cartEntity;
    // // 구매내역
    // @JsonBackReference
    // @OneToMany(mappedBy = "buyEntity")
    // private List<BuyRecordEntity> buyRecordEntityList = new ArrayList<>();
}