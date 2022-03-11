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
@Document(collection = "item_exam")
public class Item {
    @Id
    private Long code = 0L;

    private String name = null;
    private String content = null;
    private long price = 0L;
    private long quantity = 0L;
    private Date regdate = null;
}
