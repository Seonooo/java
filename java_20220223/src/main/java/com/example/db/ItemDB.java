package com.example.db;

import java.util.List;
import java.util.Map;

import com.example.vo.Item;

public interface ItemDB {
    // 시퀀스를 이용해서 물품등록
    public int insertItem(Item item);

    // 물품삭제
    public int deleteItem(long code) throws Exception;

    // 변경
    public int updateItem(Item item);

    // 조회 1개
    public Map<String, Object> selectOneMapItem(long code);
    public Item selectOneItem(long code);

    // 조회 전체
    public List<Item> selectListItem();

    // 조회 페이지단위로
    public List<Item> selectListPageItem(int page);

}
