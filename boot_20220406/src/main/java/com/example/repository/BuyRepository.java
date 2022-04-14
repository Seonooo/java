package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.example.entity.BuyEntity;
import com.example.entity.BuyProjection;

@Repository
public interface BuyRepository
        extends JpaRepository<BuyEntity, Long> {

    BuyProjection findByBno(Long bno);

    // 고객용 주문내용
    List<BuyProjection> findByMember_uemail(String uemail);

    // 판매자용 주문내용
    List<BuyProjection> findByItem_icodeIn(List<Long> icode);

    // select * from 테이블 where 1 order by bno asc
    List<BuyProjection> findByOrderByBnoAsc();

    // select * from 테이블 where bcnt >= bcnt
    List<BuyProjection> findByBcntGreaterThanEqual(Long bcnt);

    // select * from 테이블 where bno =? and bcnt=?
    List<BuyProjection> findByBnoAndBcnt(Long bno, Long bcnt);

    // select * from 테이블 where bno in (1, 3, 5)
    List<BuyProjection> findByBnoIn(List<Long> bno);

    @Query(value = "SELECT * FROM BUY", nativeQuery = true)
    public List<BuyProjection> selectBuyList();

    @Query(value = "SELECT * FROM BUY WHERE BNO = :no", nativeQuery = true)
    public BuyProjection selectBuyOne(@Param(value = "no") long bno);

    // MYBATIS #{obj.bno} === JPA + HIBERNATE :#{#obj.bno}
    @Query(value = "SELECT * FROM BUY WHERE BNO = :#{#obj.bno} AND BCNT = :#{#obj.bcnt}", nativeQuery = true)
    public BuyProjection selectBuyOneAnd(@Param(value = "obj") BuyEntity buy);

}