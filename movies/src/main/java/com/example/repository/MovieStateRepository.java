package com.example.repository;

import com.example.entity.MovieStateEntity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieStateRepository extends JpaRepository<MovieStateEntity, Long> {

}
