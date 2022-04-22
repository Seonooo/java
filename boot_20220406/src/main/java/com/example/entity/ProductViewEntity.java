package com.example.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.Immutable;

import lombok.Data;

@Immutable // view인 경우에 추가, 읽기만 가능 findBy종류만
@Entity
@Data
@Table(name = "PRODUCT_VIEW")
public class ProductViewEntity {

    @Id
    @Column(name = "no")
    Long no;

    @Column(length = 255)
    String name;

    Long price;

    Long cnt;
}
