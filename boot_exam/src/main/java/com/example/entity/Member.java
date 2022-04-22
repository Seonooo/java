package com.example.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Entity
@Data
@Table(name = "MEMBER")
public class Member {
    @Id
    @Column(name = "USERID")
    public String userid = null;

    @Column(name = "USERPW")
    // @JsonProperty(access = Access.WRITE_ONLY)
    private String userpw = null;

    @Transient
    private String userpw1 = null;

    @Column(name = "USERNAME")
    private String username = null;

    @Column(name = "USERTEL")
    private String usertel = null;

    @Column(name = "USERROLE", updatable = false)
    private String userrole = null;

    @CreationTimestamp
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    @Column(name = "USERDATE", updatable = false)
    private Date userdate = null;
}