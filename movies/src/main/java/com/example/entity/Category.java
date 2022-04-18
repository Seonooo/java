package com.example.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "CATEGORY")
public class Category {

    @Id
    @Column(name = "C_CODE")
    private Long code;

    @Column(name = "C_CATEGORY")
    private String category;
}
