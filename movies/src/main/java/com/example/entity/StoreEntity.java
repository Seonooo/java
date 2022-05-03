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

@Data
@Entity
@Table(name = "STORE")
@SequenceGenerator(name = "SEQ_STORE", sequenceName = "SEQ_STORE_CODE", allocationSize = 1, initialValue = 1)
public class StoreEntity {
    // 스토어코드
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_STORE")
    private Long stcode;
    // 상품명
    @Column(length = 100)
    private String stname;
    // 상품종류
    @Column(length = 20)
    private String sttype;
    // 상품가격
    private Long stprice;
    // 상품수량
    private Long stqty;
    // 상품설명
    @Lob
    private String stcontent;
    // 이미지
    @Lob
    private byte[] stimage;
    // 이미지타입
    @Column(length = 50)
    private String stimagetype;
    // 이미지크기
    private Long stimagesize;
    // 이미지이름
    @Column(length = 100)
    private String stimagename;
    // 상품등록일
    @DateTimeFormat(pattern = "yyyy-mm-dd hh:mm:ss.sss")
    @CreationTimestamp // CURRENT_DATE
    private Date stregdate;
    // 상품상태
    @ManyToOne
    @JoinColumn(name = "sstcode")
    private StoreStateEntity storeStateEntity;
    // 장바구니
    // @JsonBackReference
    // @OneToMany(mappedBy = "storeEntity")
    // private List<CartEntity> cartEntityList = new ArrayList<>();
}
