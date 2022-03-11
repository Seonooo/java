// 서비스를 사용하지 않고, 저장소를 통해서 구현하기
package com.example.repository;

import java.util.List;

import com.example.entity.Book;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends MongoRepository<Book, Long>{
    
    // 기본적인 CRUD(읽기, 쓰기, 수정, 삭제)    
    // 1. 책등록
    // 2. 책목록(전체)
    // 3. 페이지네이션과 정렬
    // 4. 책번호를 누르면 책 상세내용 표시

    // 검색한 개수 + 페이지네이션
    @Query(value = "{title: {$regex : ?0}}")
    List<Book> getBookList(String title, Pageable pageable );

    // 검색에 해당하는 전체 개수
    @Query(value = "{title: {$regex : ?0}}", count = true)
    long getBookCount(String title);

    // 코드가 여러개 인것 조회
    @Query(value = "{ code : {$in : ?0}}")
    List<Book> getBookCode(long code);

    // 전체목록 가져오기 code기준 내림차순
    @Query(value = "{}", sort = "{code : -1}", fields = "{title : 1, price : 1}")
    List<Book> getBookListSortCodeProjection();

    // 제목과 가격이 일치하는 것 조회(OR)
    @Query(value = "{$or : [{title : ?0}, {price : ?1}]}")
    List<Book> getBookTitleOrPriceList(String title, long price);

    // 가격이 ?0 이상 인것
    @Query(value = "{price : {$gte : ?0}}")
    List<Book> getBookPriceGte(long price);

    @Query(value = "{title : :#{#book.title}, price : : #{#book.price}")
    List<Book> getTitleAndPrice(Book book);
}


