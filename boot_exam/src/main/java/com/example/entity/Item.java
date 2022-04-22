package com.example.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Entity
@Data
@Table(name = "ITEM")
@SequenceGenerator(name = "SEQ1", sequenceName = "SEQ_ITEM_NO", initialValue = 1, allocationSize = 1)
public class Item {

    @Id
    @Column(name = "NO")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ1")
    private Long no;

    @Column(name = "NAME", length = 100)
    private String name;

    @Lob
    @Column(name = "CONTENT")
    private String content;

    @Column(name = "PRICE")
    private Long price;

    @Column(name = "QUANTITY")
    private Long quantity;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    @CreationTimestamp
    @Column(name = "REGDATE", updatable = false)
    private Date regdate;

    @ManyToOne
    @JoinColumn(name = "MEMBER_USERID", updatable = false, referencedColumnName = "USERID")
    private Member member;
}