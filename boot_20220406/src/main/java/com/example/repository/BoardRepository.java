package com.example.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.entity.BoardEntity;

@Repository
public interface BoardRepository
                extends JpaRepository<BoardEntity, Long> {

        // 검색어가 포함된 전체 개수
        // SELECT COUNT(*) FROM BOARD10 WHERE
        // WHERE BTITLE LIKE '%' || '검색어' || '%'
        long countByTitleContaining(String title);

        // findBy컬럼명Containing
        // SELECT * FROM BOARD10
        // WHERE BTITLE LIKE '%' || '검색어' || '%'
        List<BoardEntity> findByTitleContaining(String title);

        // findBy컬럼명ContainingOrderBy컬럼명Desc
        // SELECT * FROM BOARD10
        // WHERE BTITLE LIKE '%' || '검색어' || '%' ORDER BY BNO DESC
        List<BoardEntity> findByTitleContainingOrderByNoDesc(String title);

        // SELECT B.*, ROW_NUMBER() OVER( ORDER BY BNO DESC ) FROM BOARD10 B
        // WHERE BTITLE LIKE '%' || '검색어' || '%'
        List<BoardEntity> findByTitleContainingOrderByNoDesc(String title,
                        Pageable pageable);

        @Query(value = " SELECT * FROM BOARD10 B WHERE BTITLE LIKE %:ti% ", nativeQuery = true)
        List<BoardEntity> selectBoardList(@Param(value = "ti") String title);

        // 이전글 ex) 20이면 적은것 중에서 가장큰값
        BoardEntity findTopByNoLessThanOrderByNoDesc(long no);

        // 다음글 ex) 20이면 큰것 중에서 가장 작은값
        BoardEntity findTopByNoGreaterThanOrderByNoAsc(long no);
}