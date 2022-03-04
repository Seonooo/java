package com.example.entity;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Document(collection = "book4")

public class Book {
    @Id
    private long code = 0L;

    private String title = null;

    private long price = 0L;

    private String writer = null;

    // String => 문자열
    // char => 'A' 'B' 'C' 문자 하나(작은 따옴표)
    private String category = null;

    private Date regdate = null;
}
