package com.example.controller;

import java.util.HashMap;
import java.util.Map;

import com.example.entity.Board;
import com.example.repository.BoardRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/board")
public class BoardRestController {

    @Autowired
    BoardRepository bRepository;

    @RequestMapping(value = "/updatehit", method = { RequestMethod.PUT }, consumes = {
            MediaType.ALL_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
    public Map<String, Object> updateHitPut(@RequestParam(name = "bno") Long bno) {
        Map<String, Object> map = new HashMap<>();
        try {
            Board board = bRepository.findById(bno).orElse(null);
            board.setHit(board.getHit() + 2);
            bRepository.save(board);
            map.put("status", 200);

        } catch (Exception e) {
            e.printStackTrace();
            map.put("status", 0);
        }
        return map;
    }
}
