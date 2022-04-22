package com.example.repository;

import com.example.entity.ProductCountEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import io.lettuce.core.dynamic.annotation.Param;

@Repository
public interface ProductCountRepository extends JpaRepository<ProductCountEntity, Long> {

    @Query(value = "SELECT SUM(CNT) FROM PRODUCTCOUNT WHERE PRODUCT_NO=:no GROUP BY PRODUCT_NO", nativeQuery = true)
    public long selectProductCountGroup(
            @Param(value = "no") Long no);

    // nativeQuery에서 insert, update, delete => 데이터의 변화가 생김
    @Modifying(clearAutomatically = true)
    @Query(value = "DELETE FROM PRODUCTCOUNT WHERE CNT >= cnt", nativeQuery = true)
    public long deleteProductCountGroup(
            @Param(value = "cnt") Long cnt);
}
