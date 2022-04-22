package com.example.repository;

import com.example.entity.NationEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NationRepository extends JpaRepository<NationEntity, Long> {

}
