package com.example.service;

import java.util.List;

import com.example.entity.Item;

import org.springframework.stereotype.Service;

@Service
public interface ItemDB {
    // 물품등록(추가할 물품정보가 처리후에 int(-1, 0, 1)으로 반환)
    public int insertItem(Item item);

    // 물품목록(페이지 정보 1, 2, 3)
    public List<Item> selectListItem(int page, String text);

    // 이미지 정보 1개 조회
    public Item selectOneImage(long code);

    // 물품1개 조회(이미지 제외)
    public Item selectOneItem(long code);

    // 물품 1개삭제
    public int deleteItem(Long id);

    // 물품전체개수 구하기(페이지네이션의 페이지 표시용)
    public long countSearchItem(String text);

    // 물품 수정
    public int updateItemOne(Item item);
}
