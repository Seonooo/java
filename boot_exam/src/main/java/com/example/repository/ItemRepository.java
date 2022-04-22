package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.example.entity.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    List<Item> findByPriceGreaterThanEqual(Long price);

    Item findByName(String name);

}