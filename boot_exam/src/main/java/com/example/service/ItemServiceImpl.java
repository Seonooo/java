package com.example.service;

import java.util.List;

import com.example.entity.Item;
import com.example.repository.ItemRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    ItemRepository iRepository;

    @Override
    public List<Item> selectItemList(long price) {
        try {
            return iRepository.findByPriceGreaterThanEqual(price);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int insertItem(Item item) {
        try {
            iRepository.save(item);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int deleteItem(long no) {
        try {
            iRepository.deleteById(no);

            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int updateItem(Item item) {
        try {
            iRepository.save(item);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public Item selectItemOne(long no) {
        try {
            return iRepository.findById(no).orElse(null);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Item selectItemName(String name) {
        return iRepository.findByName(name);
    }

}
