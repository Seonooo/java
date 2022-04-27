package com.example.repository;

import com.example.entity.MembercategoryEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MembercategoryRepository extends JpaRepository<MembercategoryEntity, Long> {

}
