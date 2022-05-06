package com.example.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "BUY_STATE")
public class BuyStateEntity {
    // 구매단계코드
    @Id
    private Long bscode;
    // 구매단계
    @Column(length = 30)
    private String bsstate;
    // // 구매
    // @JsonBackReference
    // @OneToMany(mappedBy = "buyStateEntity")
    // private List<BuyEntity> buyEntityList = new ArrayList<>();

    // 0 장바구니
    // 1 결제완료
    // 2 결제취소
    // 3 배송완료
}