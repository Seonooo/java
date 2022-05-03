package com.example.repository;

import com.example.entity.MovieCategoryEntity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieCategoryRepository extends JpaRepository<MovieCategoryEntity, Long> {

    Page<MovieCategoryEntity> findByCategoryEntity_Ccode(Long ccode, Pageable pageable);
}