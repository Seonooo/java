package com.example.repository;

import com.example.entity.MovieStateEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieStateRepository extends JpaRepository<MovieStateEntity, Long> {

}
