package com.example.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "ALRAM_STATE")
public class AlramStateEntity {
    // 알람상태코드
    @Id
    private Long ascode;
    // 알람상태
    @Column(length = 20)
    private String asstate;
    // 알람
    // @JsonBackReference
    // @OneToMany(mappedBy = "alramStateEntity")
    // private List<AlramEntity> alramEntityList = new ArrayList<>();
}
