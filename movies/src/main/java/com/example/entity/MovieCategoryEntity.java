package com.example.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "MOVIECATEGORY")
@SequenceGenerator(name = "SEQ_MOVIECATEGORY", sequenceName = "SEQ_MOVIECATEGORY_CODE", allocationSize = 1, initialValue = 1)
public class MovieCategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_MOVIECATEGORY")
    private Long mccode;

    @ManyToOne
    @JoinColumn(name = "mcode")
    private MovieEntity movieEntity;

    @ManyToOne
    @JoinColumn(name = "ccode")
    private CategoryEntity categoryEntity;
}
