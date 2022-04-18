package com.example.repository;

import java.util.List;

import com.example.entity.Board;
import com.example.entity.BoardListProjection;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {

    List<BoardListProjection> findByWriterContainingOrderByNoDesc(String writer, Pageable pageable);

    BoardListProjection findTop1ByNoLessThanOrderByNoDesc(long no);

    BoardListProjection findTop1ByNoGreaterThanOrderByNoAsc(long no);

}
