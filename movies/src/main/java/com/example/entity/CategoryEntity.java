package com.example.entity;

// import java.util.ArrayList;
// import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
// import javax.persistence.OneToMany;
import javax.persistence.Table;

// import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;

@Data
@Entity
@Table(name = "CATEGORY")
public class CategoryEntity {
    // 장르코드
    @Id
    private Long ccode;
    // 장르
    @Column(length = 50)
    private String ccategory;
    // // 영화 테이블
    // @JsonBackReference
    // @OneToMany(mappedBy = "categoryEntity")
    // private List<MovieEntity> movieEntityList = new ArrayList<>();
    // // 회원
    // @JsonBackReference
    // @OneToMany(mappedBy = "categoryEntity")
    // private List<MemberEntity> memberEntityList = new ArrayList<>();
}
