package com.example.entity;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString

@Document(collection = "item4")

public class Item {
    
    @Id
    private long code = 0L;

    @Field(name = "itemname")
    private String name = null;

    @Field(name = "itemprice")
    private long price = 0L;

    @Field(name = "itemquantity")
    private long quantity = 0L;

    @Field(name = "itemdate")
    private Date regdate = null;

    // byte배열
    private byte[] filedate = null;

    private String filetype = null;
    private String filename = null;
    private Long filesize = 0L;

}
