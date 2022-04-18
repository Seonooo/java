package com.example.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Entity
@Table(name = "PRODUCT")
@SequenceGenerator(name = "SEQ3", sequenceName = "SEQ_PRODUCT_NO", allocationSize = 1, initialValue = 1001)
public class ProductEntity {

    @Id
    @GeneratedValue(generator = "SEQ3", strategy = GenerationType.SEQUENCE)
    Long no;

    @Column(length = 255)
    String name;

    Long price;

    @Lob
    @Column(nullable = true)
    byte[] imagedata;

    String imagename;

    String imagetype;

    Long imagesize = 0L;

    @DateTimeFormat(pattern = "yyyy-mm-dd hh:mm:ss.sss")
    @UpdateTimestamp
    @Column(name = "UREGDATE")
    private Date uptdate;
}
