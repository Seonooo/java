package com.example.repository;

import com.example.entity.MemberpointEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberpointRepository extends JpaRepository<MemberpointEntity, Long> {

}