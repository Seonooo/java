package com.example.restcontroller;

import java.util.HashMap;
import java.util.Map;

import com.example.entity.ProductCountEntity;
import com.example.entity.ProductViewEntity;
import com.example.repository.ProductCountRepository;
import com.example.repository.ProductViewRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/productcount")
public class ProductCountRestController {

    @Autowired
    ProductCountRepository pcRepository;

    @Autowired
    ProductViewRepository pViewRepository;

    @PostMapping(value = "/insert.json", consumes = { MediaType.ALL_VALUE }, produces = {
            MediaType.APPLICATION_JSON_VALUE })
    public Map<String, Object> insertPost(@RequestBody ProductCountEntity productCount) {
        Map<String, Object> map = new HashMap<>();
        try {
            pcRepository.save(productCount);
            map.put("status", 200);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("status", 0);
        }
        return map;
    }

    @GetMapping(value = "/selectcount.json", consumes = { MediaType.ALL_VALUE }, produces = {
            MediaType.APPLICATION_JSON_VALUE })
    public Map<String, Object> selectcountPost(@RequestParam(name = "no") long no) {
        Map<String, Object> map = new HashMap<>();
        try {
            ProductViewEntity entity = pViewRepository.findById(no).orElse(null);
            map.put("result", entity);
            map.put("status", 200);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("status", 0);
        }
        return map;
    }
}
