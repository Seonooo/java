package com.example.service;

import java.util.List;

import com.example.entity.BoardEntity;

import org.springframework.stereotype.Service;

@Service
public interface BoardService {
    // 게시글 등록
    int insertBoard(BoardEntity board);

    // 게시글 1개 가져오기
    BoardEntity selectOneBoard(Long no);

    // 게시글 조회수 1증가
    int updateHit(Long no);

    // 게시글 조회수 1증가
    int updateLike(Long no);

    // 게시글 삭제
    int deleteBoard(Long no);

    // 게시글 리스트 가져오기(타입별)
    List<BoardEntity> selectListType(Long no);

    // 게시글 리스트 가져오기(전체)
    List<BoardEntity> selectList();

    // // 게시글 리스트 가져오기(페이지네이션)
    // List<BoardEntity> selectListpagenation(String title, Long start, Long end);

    // // 게시글 타입별 리스트 가져오기(페이지네이션)
    // List<BoardEntity> selectListTypepagenation(String title, Long type, Long
    // start, Long end);
}
