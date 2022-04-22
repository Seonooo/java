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
@Table(name = "STORE_STATE")
public class StoreStateEntity {
    // 상품상태코드
    @Id
    private Long sstCode;
    // 상품상태
    @Column(length = 50)
    private String sstState;
    // 스토어
    @JsonBackReference
    @OneToMany(mappedBy = "storeStateEntity")
    private List<StoreEntity> storeEntityList = new ArrayList<>();
}
