package com.example.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "STORE_STATE")
public class StoreStateEntity {
    // 상품상태코드
    // 0 -> 판매중 / 1 -> 재고소진
    @Id
    private Long sstcode;
    // 상품상태
    @Column(length = 50)
    private String sststate;
    // 스토어
    // @JsonBackReference
    // @OneToMany(mappedBy = "storeStateEntity")
    // private List<StoreEntity> storeEntityList = new ArrayList<>();
}
