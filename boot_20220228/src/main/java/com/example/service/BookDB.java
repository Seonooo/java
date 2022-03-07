package com.example.service;

import java.util.List;

import com.example.entity.Book;

import org.springframework.stereotype.Service;

@Service
public interface BookDB {
    // 일괄 등록
    public int insertBatchBook(List<Book> list);

    // 목록(페이지+검색)
    public List<Book> selectListPageSearchBook(int page, String text);

    // 페이지네이션용(검색)
    public long countSearchBook(String text);

    // 일괄 삭제하기
    public long deleteBatchBook(List<Long> code);

    // 코드에 해당하는 목록 가져오기
    public List<Book> selectListcode(List<Long> code);

    // 일괄 수정
    public long updateBatchBook(List<Book> list);

}
