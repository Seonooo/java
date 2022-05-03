package com.example.repository;

import java.util.List;

import com.example.entity.BoardEntity;
import com.example.entity.BoardProjection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {
        public List<BoardEntity> findByBtype(Long type);

        // @Query(value = " SELECT * FROM (SELECT B.*,ROW_NUMBER() OVER (ORDER BY B_NO
        // DESC) ROWN FROM BOARD B WHERE B_TITLE LIKE %:ti%) WHERE ROWN BETWEEN :start
        // AND :end ORDER BY ROWN ASC", nativeQuery = true)
        // List<BoardEntity> selectBoardList(
        // @Param(value = "ti") String title,
        // @Param(value = "start") Long start,
        // @Param(value = "end") Long end);

        // @Query(value = " SELECT * FROM (SELECT B.*,ROW_NUMBER() OVER (ORDER BY B_NO
        // DESC) ROWN FROM BOARD B WHERE B_TYPE=:type AND B_TITLE LIKE %:ti%) WHERE ROWN
        // BETWEEN :start AND :end ORDER BY ROWN ASC", nativeQuery = true)
        // List<BoardEntity> selectBoardListType(
        // @Param(value = "ti") String title,
        // @Param(value = "type") Long type,
        // @Param(value = "start") Long start,
        // @Param(value = "end") Long end);

        // public List<BoardProjection> findBybTitleOrbType(String btitle, Long type);

}
