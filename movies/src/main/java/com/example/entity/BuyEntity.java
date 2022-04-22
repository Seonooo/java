package com.example.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

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
    private Long buyNo;
    // 구매일
    @DateTimeFormat(pattern = "yyyy-mm-dd hh:mm:ss.sss")
    @CreationTimestamp
    private Date buyRegdate;
    // 구매단계
    @ManyToOne
    @JoinColumn(name = "bs_code")
    private BuyStateEntity buyStateEntity;
    // 장바구니
    @ManyToOne
    @JoinColumn(name = "ca_no")
    private CartEntity cartEntity;
    // 구매내역
    @JsonBackReference
    @OneToMany(mappedBy = "buyEntity")
    private List<BuyRecordEntity> buyRecordEntityList = new ArrayList<>();
}