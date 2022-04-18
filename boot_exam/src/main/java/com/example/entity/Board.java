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
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Entity
@Table(name = "BOARD")
@SequenceGenerator(name = "SEQ_BOARD", sequenceName = "SEQ_BOARD_NO", allocationSize = 1, initialValue = 1)
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_BOARD")
    private Long no;

    @Column(length = 200)
    private String title;

    @Lob
    private String content;

    @Column(length = 50)
    private String writer;

    @Lob
    private byte[] image;

    @Column(length = 200)
    private String imagename;

    @Column(length = 200)
    private String imagetype;

    private Long imagesize;

    private Long hit = 10L;

    @DateTimeFormat(pattern = "yyyy-mm-dd hh:mm:ss.sss")
    @CreationTimestamp
    private Date regdate;

    @Transient
    private String imageurl;

}
