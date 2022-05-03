package com.example.restcontroller;

import java.util.HashMap;
import java.util.Map;

import com.example.repository.NationRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

// backend만 구현함. 화면구현 X, vue.js 또는 react.js 연동

@RestController
@RequestMapping("/api/nation")
public class NationRestController {

    @Autowired
    NationRepository nRepository;

    // 장르 리스트 불러오기
    // http://127.0.0.1:9090/ROOT/api/nation/nationtotal
    @RequestMapping(value = "/nationtotal", method = { RequestMethod.GET }, consumes = {
            MediaType.ALL_VALUE }, produces = {
                    MediaType.APPLICATION_JSON_VALUE })
    public Map<String, Object> categoryGET() {
        Map<String, Object> map = new HashMap<>();

        Long total = 0L;
        total = nRepository.count();
        map.put("total", total);
        map.put("status", 200);
        return map;
    }
}