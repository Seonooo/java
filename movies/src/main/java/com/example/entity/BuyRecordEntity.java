package com.example.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Entity
@Table(name = "BUY_RECORD")
@SequenceGenerator(name = "SEQ_BUY_RECORD", sequenceName = "SEQ_BUY_RECORD_NO", allocationSize = 1, initialValue = 1)

public class BuyRecordEntity {
    // 내역번호
    @Id
    @GeneratedValue(generator = "SEQ_BUY_RECORD", strategy = GenerationType.SEQUENCE)
    private Long brno;
    // 등록일
    @DateTimeFormat(pattern = "yyyy-mm-dd hh:mm:ss.sss")
    @CreationTimestamp
    private Date brregdate;
    // 구매
    @ManyToOne
    @JoinColumn(name = "buyno")
    private BuyEntity buyEntity;
}
