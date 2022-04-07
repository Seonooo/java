package com.example.dto;

import java.sql.Date;

import lombok.Data;

//물품테이블
@Data
public class ItemDTO {
    // 물품코드
    private Long icode;
    // 물품이름
    private String iname;
    // 물품내용
    private String icontent;
    // 물품가격
    private Long iprice;
    // 재고수량
    private Long iquantity;
    // 등록일
    private Date iregdate;
    // 이미지
    private byte[] iimage;
    // 이미지크기
    private Long iimagesize;
    // 이미지타입
    private String iimagetype;
    // 이미지명
    private String iimagename;
    // 이메일
    private String uemail;
}
