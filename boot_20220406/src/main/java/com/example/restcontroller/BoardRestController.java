package com.example.restcontroller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.dto.BoardDTO;
import com.example.entity.BoardEntity;
import com.example.mapper.BoardMapper;
import com.example.repository.BoardRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Value;

@RestController
@RequestMapping("/api/board")
public class BoardRestController {

    @Autowired
    BoardMapper bMapper;

    @Autowired
    BoardRepository bRepository;

    @Value("${board.page.count}")
    int PAGECNT;

    // 게시물 등록
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

    // 게시물 삭제
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

    // 게시물 수정
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

    // 게시물 1개 조회
    @RequestMapping(value = "/selectone", method = { RequestMethod.GET }, consumes = {
            MediaType.ALL_VALUE }, produces = {
                    MediaType.APPLICATION_JSON_VALUE })
    public Map<String, Object> boardSelectOneGET(@RequestParam(name = "bno") long bno) {
        Map<String, Object> map = new HashMap<>();
        map.put("status", 0);

        BoardDTO retBoard = bMapper.selectBoardOne(bno);
        if (retBoard != null) {
            map.put("status", 200);
            map.put("result", retBoard);
        }
        return map;
    }

    // 게시판 목록, 페이지 네이션
    @RequestMapping(value = "/selectlist", method = { RequestMethod.GET }, consumes = {
            MediaType.ALL_VALUE }, produces = {
                    MediaType.APPLICATION_JSON_VALUE })
    public Map<String, Object> selectBoardList(@RequestParam(value = "page") int page) {
        Map<String, Object> map = new HashMap<>();
        map.put("status", 0);

        List<BoardDTO> list = bMapper.selectBoardList((page * PAGECNT) - (PAGECNT - 1), page * PAGECNT);

        if (list != null) {
            map.put("status", 200);
            map.put("result", list);
        }

        return map;
    }

    // 게시물 조회수 증가
    @RequestMapping(value = "/updatehit", method = { RequestMethod.PUT }, consumes = {
            MediaType.ALL_VALUE }, produces = {
                    MediaType.APPLICATION_JSON_VALUE })
    public Map<String, Object> updateBoardHit(@RequestParam(value = "bno") long bno) {
        Map<String, Object> map = new HashMap<>();
        map.put("status", 0);

        int hit = bMapper.updateBoardHit(bno);

        if (hit == 1) {
            map.put("status", 200);
        }

        return map;
    }

    @RequestMapping(value = "/updatehit1", method = { RequestMethod.PUT }, consumes = {
            MediaType.ALL_VALUE }, produces = {
                    MediaType.APPLICATION_JSON_VALUE })
    public Map<String, Object> boardUpdateHitPUT(@RequestParam(value = "no") long no) {
        Map<String, Object> map = new HashMap<>();
        try {
            BoardEntity board = bRepository.findById(no).orElse(null);
            board.setHit(board.getHit() + 1L);
            bRepository.save(board);
            map.put("status", 200);

        } catch (Exception e) {
            map.put("status", 0);
        }
        return map;
    }

}
