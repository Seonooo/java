package com.example.service;

import java.util.List;

import com.example.entity.Item;

import org.springframework.stereotype.Service;

@Service
public interface ItemService {
    // 물품 조건 조회
    public List<Item> selectItemList(long price);

    // 물품 제목조건 조회
    public Item selectItemName(String name);

    // 물품 등록
    public int insertItem(Item item);

    // 물품 삭제
    public int deleteItem(long no);

    // 물품 수정
    public int updateItem(Item item);

    // 물품 1개 조회
    public Item selectItemOne(long no);
}