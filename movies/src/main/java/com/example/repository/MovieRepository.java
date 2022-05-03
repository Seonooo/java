package com.example.repository;

import java.util.List;

import com.example.entity.MovieEntity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<MovieEntity, Long> {
    // 상영상태별
    Page<MovieEntity> findByMovieStateEntity_Mscode(Long mscode, Pageable pageable);

    // 제목별
    // List<MovieEntity> findByMtitleContaining(String title);

    //
    Page<MovieEntity> findByMtitleContaining(String title, Pageable pageable);
    // , Pageable pageable);
}
