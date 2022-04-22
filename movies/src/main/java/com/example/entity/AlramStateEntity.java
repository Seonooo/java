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
@Table(name = "ALRAM_STATE")
public class AlramStateEntity {
    // 알람상태코드
    @Id
    private Long asCode;
    // 알람상태
    @Column(length = 20)
    private String asState;
    // 알람
    @JsonBackReference
    @OneToMany(mappedBy = "alramStateEntity")
    private List<AlramEntity> alramEntityList = new ArrayList<>();
}
