package com.example.dto;

import java.util.Date;

import lombok.Data;

@Data
public class BuyDTO {
    private Long bno;

    private Long bcnt;

    private Date bregdate;

    private Long icode;

    private String uemail;
}
