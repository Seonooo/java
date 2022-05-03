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
@Table(name = "CART")
@SequenceGenerator(name = "SEQ_CART", sequenceName = "SEQ_CART_NO", initialValue = 1, allocationSize = 1)
public class CartEntity {
    // 장바구니 번호
    @Id
    @GeneratedValue(generator = "SEQ_CART", strategy = GenerationType.SEQUENCE)
    private Long cano;
    // 구매수량
    private Long caqty;
    // 등록일
    @DateTimeFormat(pattern = "yyyy-mm-dd hh:mm:ss.sss")
    @CreationTimestamp
    private Date caregdate;
    // 스토어
    @ManyToOne
    @JoinColumn(name = "stcode")
    private StoreEntity storeEntity;
    // 회원
    @ManyToOne
    @JoinColumn(name = "mid")
    private MemberEntity memberEntity;
    // // 구매
    // @JsonBackReference
    // @OneToMany(mappedBy = "cartEntity")
    // private List<BuyEntity> buyEntityList = new ArrayList<>();
}
