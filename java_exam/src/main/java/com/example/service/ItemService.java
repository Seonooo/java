package com.example.service;

import java.util.List;

import com.example.entity.Item;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface ItemService {
    public long insertItem(Item item);

    public List<Item> selectlistItem(Pageable pageable);

    public long updateItem(Item item);

    public long deleteItem(long code);

    public long countItem();
}
