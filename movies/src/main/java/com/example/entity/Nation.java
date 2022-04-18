package com.example.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "NATION")
public class Nation {

    @Id
    @Column(name = "N_CODE")
    private Long code;

    @Column(name = "N_NATION")
    private String nation;
}
