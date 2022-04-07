package com.example.restcontroller;

import java.util.HashMap;
import java.util.Map;

import com.example.dto.BoardDTO;
import com.example.mapper.BoardMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/board")
public class BoardRestController {

    @Autowired
    BoardMapper bMapper;

    @RequestMapping(value = "/insert", method = { RequestMethod.POST }, consumes = { MediaType.ALL_VALUE }, produces = {
            MediaType.APPLICATION_JSON_VALUE })
    public Map<String, Object> insertBoardOne(@RequestBody BoardDTO board) {
        Map<String, Object> map = new HashMap<>();
        map.put("status", 0);

        int ret = bMapper.insertBoardOne(board);
        if (ret == 1) {
            map.put("status", 200);
        }
        return map;
    }

    @RequestMapping(value = "/delete", method = { RequestMethod.DELETE }, consumes = {
            MediaType.ALL_VALUE }, produces = {
                    MediaType.APPLICATION_JSON_VALUE })
    public Map<String, Object> deleteBoardOne(
            @RequestParam(value = "bno") long bno) {
        Map<String, Object> map = new HashMap<>();
        map.put("status", 0);

        int ret = bMapper.deleteBoardOne(bno);
        if (ret == 1) {
            map.put("status", 200);
        }
        return map;
    }

    @RequestMapping(value = "/update", method = { RequestMethod.PUT }, consumes = {
            MediaType.ALL_VALUE }, produces = {
                    MediaType.APPLICATION_JSON_VALUE })
    public Map<String, Object> updateBoardOne(
            @RequestBody BoardDTO board, Model model) {
        Map<String, Object> map = new HashMap<>();
        map.put("status", 0);

        int ret = bMapper.updateBoardOne(board);
        if (ret == 1) {
            model.addAttribute("board", board);
            map.put("status", 200);
        }
        return map;
    }

}
