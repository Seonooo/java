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

@Data
@Entity
@Table(name = "STORE")
@SequenceGenerator(name = "SEQ_STORE", sequenceName = "SEQ_STORE_CODE", allocationSize = 1, initialValue = 1)
public class StoreEntity {
    // 스토어코드
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_STORE")
    private Long stCode;
    // 상품명
    @Column(length = 100)
    private String stName;
    // 상품종류
    @Column(length = 20)
    private String stType;
    // 상품가격
    private Long stPrice;
    // 상품수량
    private Long stQty;
    // 상품설명
    @Lob
    private String stContent;
    // 상품등록일
    @DateTimeFormat(pattern = "yyyy-mm-dd hh:mm:ss.sss")
    @CreationTimestamp // CURRENT_DATE
    private Date stRegdate;
    // 상품상태
    @ManyToOne
    @JoinColumn(name = "sst_code")
    private StoreStateEntity storeStateEntity;
    // 장바구니
    @JsonBackReference
    @OneToMany(mappedBy = "storeEntity")
    private List<CartEntity> cartEntityList = new ArrayList<>();
}
