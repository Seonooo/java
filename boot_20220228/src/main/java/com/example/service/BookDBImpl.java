package com.example.service;

import java.util.Collection;
import java.util.List;

import com.example.entity.Book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

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

}
