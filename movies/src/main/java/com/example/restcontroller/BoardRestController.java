package com.example.restcontroller;

import java.util.HashMap;
import java.util.Map;

import com.example.entity.BoardEntity;
import com.example.entity.MemberEntity;
import com.example.jwt.jwtUtil;
import com.example.repository.BoardRepository;
import com.example.service.BoardService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// backend만 구현함. 화면구현 X, vue.js 또는 react.js 연동

@RestController
@RequestMapping("/api/board")
public class BoardRestController {

    @Autowired
    jwtUtil jwt;

    @Autowired
    BoardService bService;

    @Autowired
    BoardRepository bRepository;

    // 게시판 글쓰기
    // http://127.0.0.1:9090/ROOT/api/board/insert
    @RequestMapping(value = "/insert", method = { RequestMethod.POST }, consumes = {
            MediaType.ALL_VALUE }, produces = {
                    MediaType.APPLICATION_JSON_VALUE })
    public Map<String, Object> insertPOST(
            @RequestBody BoardEntity board,
            @RequestHeader(name = "TOKEN") String token) {
        Map<String, Object> map = new HashMap<>();
        map.put("status", 0);
        try {
            System.out.println(token.toString());

            if (!token.isEmpty()) {
                String user = jwt.extractUsername(token);
                MemberEntity member = new MemberEntity();
                member.setMid(user);
                board.setMemberEntity(member);
                System.out.println(board.toString());
                int ret = bService.insertBoard(board);
                if (ret == 1) {
                    map.put("status", 200);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("status", 0);
        }
        return map;
    }

    // 게시판 글 수정
    // http://127.0.0.1:9090/ROOT/api/board/update
    @RequestMapping(value = "/update", method = { RequestMethod.PUT }, consumes = {
            MediaType.ALL_VALUE }, produces = {
                    MediaType.APPLICATION_JSON_VALUE })
    public Map<String, Object> updatePOST(
            @RequestParam Long no,
            @RequestBody BoardEntity board,
            @RequestHeader(name = "TOKEN") String token) {
        Map<String, Object> map = new HashMap<>();
        map.put("status", 0);
        try {
            // System.out.println(token.toString());

            if (!token.isEmpty()) {
                String user = jwt.extractUsername(token);
                // System.out.println(user);
                BoardEntity boardold = bService.selectOneBoard(no);
                // System.out.println(boardold.getMemberEntity().getMId());
                if (user.equals(boardold.getMemberEntity().getMid())) {
                    boardold.setBcontent(board.getBcontent());
                    System.out.println("boardold.setBcontet" + board.getBcontent());
                    boardold.setBtype(board.getBtype());

                    bService.insertBoard(boardold);
                    map.put("status", 200);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("status", 0);
        }
        return map;
    }

    // 게시판 글 삭제
    // http://127.0.0.1:9090/ROOT/api/board/delete
    @RequestMapping(value = "/delete", method = { RequestMethod.DELETE }, consumes = {
            MediaType.ALL_VALUE }, produces = {
                    MediaType.APPLICATION_JSON_VALUE })
    public Map<String, Object> deletePOST(
            @RequestParam Long no,
            @RequestHeader(name = "TOKEN") String token) {
        Map<String, Object> map = new HashMap<>();
        map.put("status", 0);
        try {
            System.out.println(token.toString());
            if (!token.isEmpty()) {
                String user = jwt.extractUsername(token);
                BoardEntity boardold = bService.selectOneBoard(no);
                if (user.equals(boardold.getMemberEntity().getMid())) {
                    int ret = bService.deleteBoard(no);
                    if (ret == 1) {
                        map.put("status", 200);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("status", 0);
        }
        return map;
    }

    // 게시글 조회수 1 증가
    // http://127.0.0.1:9090/ROOT/api/board/updatehit
    @RequestMapping(value = "/updatehit", method = { RequestMethod.GET }, consumes = {
            MediaType.ALL_VALUE }, produces = {
                    MediaType.APPLICATION_JSON_VALUE })
    public Map<String, Object> updatehitPOST(
            @RequestParam Long no) {
        Map<String, Object> map = new HashMap<>();
        map.put("status", 0);
        try {

            int ret = bService.updateHit(no);
            if (ret == 1) {
                map.put("status", 200);
            }

        } catch (Exception e) {
            e.printStackTrace();
            map.put("status", 0);
        }
        return map;
    }

    // 게시글 좋아요수 1 증가
    // http://127.0.0.1:9090/ROOT/api/board/updatelike
    @RequestMapping(value = "/updatelike", method = { RequestMethod.GET }, consumes = {
            MediaType.ALL_VALUE }, produces = {
                    MediaType.APPLICATION_JSON_VALUE })
    public Map<String, Object> updatelikePOST(
            @RequestParam Long no) {
        Map<String, Object> map = new HashMap<>();
        map.put("status", 0);
        try {

            int ret = bService.updateLike(no);
            if (ret == 1) {
                map.put("status", 200);
            }

        } catch (Exception e) {
            e.printStackTrace();
            map.put("status", 0);
        }
        return map;
    }

    // // 게시글 전체 리스트 가져오기
    // // http://127.0.0.1:9090/ROOT/api/board/selectlist
    // @RequestMapping(value = "/selectlist", method = { RequestMethod.GET },
    // consumes = {
    // MediaType.ALL_VALUE }, produces = {
    // MediaType.APPLICATION_JSON_VALUE })
    // public Map<String, Object> selectListGET(
    // @RequestParam(name = "title", defaultValue = "") String title,
    // @RequestParam(name = "page", defaultValue = "1") Long page,
    // @RequestParam(name = "pagecnt", defaultValue = "5") Long pagecnt) {
    // Map<String, Object> map = new HashMap<>();
    // map.put("status", 0);
    // try {
    // long start = (page - 1) * pagecnt + 1;
    // long end = (page - 1) * pagecnt + pagecnt;
    // List<BoardEntity> boardlist = bService.selectListpagenation(title, start,
    // end);
    // map.put("boardlist", boardlist);
    // map.put("status", 200);
    // } catch (Exception e) {
    // e.printStackTrace();
    // map.put("status", 0);
    // }
    // return map;
    // }

    // // 게시글 타입별 리스트 가져오기
    // // http://127.0.0.1:9090/ROOT/api/board/selectlisttype
    // @RequestMapping(value = "/selectlisttype", method = { RequestMethod.GET },
    // consumes = {
    // MediaType.ALL_VALUE }, produces = {
    // MediaType.APPLICATION_JSON_VALUE })
    // public Map<String, Object> selectListTypeGET(
    // @RequestParam Long type,
    // @RequestParam(name = "title", defaultValue = "") String title,
    // @RequestParam(name = "page", defaultValue = "1") Long page,
    // @RequestParam(name = "pagecnt", defaultValue = "5") Long pagecnt) {
    // Map<String, Object> map = new HashMap<>();
    // map.put("status", 0);
    // try {
    // long start = (page - 1) * pagecnt + 1;
    // long end = (page - 1) * pagecnt + pagecnt;
    // List<BoardEntity> boardlist = bService.selectListTypepagenation(title, type,
    // start, end);
    // map.put("boardlisttype", boardlist);
    // map.put("status", 200);
    // } catch (Exception e) {
    // e.printStackTrace();
    // map.put("status", 0);
    // }
    // return map;
    // }

    // 게시글 1개 가져오기
    // http://127.0.0.1:9090/ROOT/api/board/selectone
    @RequestMapping(value = "/selectone", method = { RequestMethod.GET }, consumes = {
            MediaType.ALL_VALUE }, produces = {
                    MediaType.APPLICATION_JSON_VALUE })
    public Map<String, Object> selectOneGET(
            @RequestParam Long no) {
        Map<String, Object> map = new HashMap<>();
        map.put("status", 0);
        try {
            BoardEntity board = bService.selectOneBoard(no);
            map.put("board", board);
            map.put("status", 200);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("status", 0);
        }
        return map;
    }
}