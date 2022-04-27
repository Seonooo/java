package com.example.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;

@Data
@Entity
@Table(name = "BUY_STATE")
public class BuyStateEntity {
    // 구매단계코드
    @Id
    private Long bsCode;
    // 구매단계
    @Column(length = 30)
    private String bsState;
    // // 구매
    // @JsonBackReference
    // @OneToMany(mappedBy = "buyStateEntity")
    // private List<BuyEntity> buyEntityList = new ArrayList<>();
}