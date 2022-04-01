package com.example.service;

import java.util.List;
import java.util.Map;

import com.example.dto.ItemDTO;

import org.springframework.stereotype.Service;

@Service
public interface ItemService {
    public int insertItemOne(ItemDTO item);

    public List<ItemDTO> selectItemList(Map<String, Object> map);

    public long selectItemCount(Map<String, Object> map);

    public ItemDTO selectItemOne(Long code);

    public ItemDTO selectItemImageOne(Long code);

}
