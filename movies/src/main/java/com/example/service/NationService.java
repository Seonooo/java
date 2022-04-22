package com.example.service;

import java.util.List;

import com.example.entity.NationEntity;

import org.springframework.stereotype.Service;

@Service
public interface NationService {
    // 국가 2음절 코드 , 국가명 입력
    public int insertNationList(List<NationEntity> list);
}
