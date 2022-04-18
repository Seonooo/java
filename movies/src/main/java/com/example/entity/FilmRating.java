package com.example.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "FILMRATING")
public class FilmRating {

    @Id
    @Column(name = "F_CODE")
    private Long code;

    @Column(name = "F_FILMRATING")
    private String filmRating;
}
