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
    @Id
    private Long sstCode;
    // 상품상태
    @Column(length = 50)
    private String sstState;
    // 스토어
    // @JsonBackReference
    // @OneToMany(mappedBy = "storeStateEntity")
    // private List<StoreEntity> storeEntityList = new ArrayList<>();
}
