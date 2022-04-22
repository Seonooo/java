package com.example.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Item;
import com.example.service.ItemService;

@RestController
@RequestMapping(value = "/item")
public class RestItemController {

    @Autowired
    ItemService iService;

    @PostMapping(value = "/insert.rest", consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Object insertItemPOST(@RequestBody Item item) {
        Map<String, Object> map = new HashMap<>();
        try {
            iService.insertItem(item);
            map.put("status", 200);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("status", 0);
        }
        return map;
    }

    @PutMapping(value = "/update.rest", consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Object updateItemPUT(@RequestBody Item item) {
        Map<String, Object> map = new HashMap<>();
        try {
            Item item1 = iService.selectItemOne(item.getNo());
            item1.setContent(item.getContent());
            item1.setQuantity(item.getQuantity());
            item1.setName(item.getName());
            item1.setPrice(item.getPrice());

            iService.updateItem(item1);
            map.put("status", 200);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("status", 0);
        }
        return map;
    }

    @DeleteMapping(value = "/delete.rest", consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Object deleteItem(@RequestParam("name") String name) {
        Map<String, Object> map = new HashMap<>();
        try {
            Item item = iService.selectItemName(name);
            iService.deleteItem(item.getNo());
            map.put("status", 200);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("status", 0);
        }
        return map;
    }

    @GetMapping(value = "/select.rest", consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Object selectItemListGET(@RequestParam("price") Long price) {
        Map<String, Object> map = new HashMap<>();
        try {
            iService.selectItemList(price);
            map.put("status", 200);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("status", 0);
        }
        return map;
    }

}