package com.example.repository;

import com.example.entity.PosterEntity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PosterRepository extends JpaRepository<PosterEntity, Long> {
    // 이미지 리스트
    Page<PosterEntity> findByMovieEntity_Mcode(Long mcode, Pageable pageable);

    // 대표이미지
    PosterEntity findFirstByOrderByPheadDesc();

}
