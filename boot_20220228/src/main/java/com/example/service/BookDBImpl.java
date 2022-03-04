package com.example.service;

import java.util.Collection;
import java.util.List;

import com.example.entity.Book;
import com.mongodb.client.result.DeleteResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

@Service
public class BookDBImpl implements BookDB {

    @Autowired
    MongoTemplate mongodb;

    @Override
    public int insertBatchBook(List<Book> list) {
        try {

            Collection<Book> retList = mongodb.insert(list, Book.class);
            if (retList.size() == list.size()) {
                return 1;
            }
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public List<Book> selectListPageSearchBook(int page, String text) {
        try {
            Query query = new Query();

            // 검색패턴( .*a.* => a가 포함된 것 해당 ), 정규식
            Criteria criteria = Criteria.where("title").regex(".*" + text + ".*");
            query.addCriteria(criteria);

            // 페이지네이션(0 부터 시작)
            Pageable pageable = PageRequest.of(page - 1, 10);
            query.with(pageable);

            // 정렬 (_id기준 내림차순)
            Sort sort = Sort.by(Direction.DESC, "_id");
            query.with(sort);

            return mongodb.find(query, Book.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public long countSearchBook(String text) {
        try {
            Query query = new Query();

            // 검색패턴( .*a.* => a가 포함된 것 해당 ), 정규식
            Criteria criteria = Criteria.where("title").regex(".*" + text + ".*");
            query.addCriteria(criteria);

            return mongodb.count(query, Book.class);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public int deleteBook(long code) {
        try {
            Query query = new Query();
            query.addCriteria(Criteria.where("_id").is(code));
            DeleteResult result = mongodb.remove(query, Book.class);
            if (result.getDeletedCount() == 1L) {
                return 1;
            }
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

}
