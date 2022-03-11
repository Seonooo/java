package com.example.service;

import java.util.List;

import com.example.entity.Item;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl implements ItemService{

    @Autowired
    private MongoTemplate mongodb;
    @Override
    public long insertItem(Item item) {
        try {
            Item ret = mongodb.insert(item);
            if(ret != null){
                return 1;
            }
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
    @Override
    public List<Item> selectlistItem(Pageable pageable) {
        try {
            Query query = new Query();
            query.with(pageable);

            return mongodb.find(query, Item.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    @Override
    public long updateItem(Item item) {
        try {
            Query query = new Query();
            query.addCriteria(Criteria.where("_id").is(item.getCode()));

            Update update = new Update();
            update.set("content", item.getContent());
            update.set("price", item.getPrice());
            update.set("quantity", item.getQuantity());
            if(item.getPrice()<1000L){
                update.set("price", 1000L);
            }

            UpdateResult result = mongodb.updateFirst(query, update, Item.class);
            System.out.println(result);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
    @Override
    public long deleteItem(long code) {
        try {
            Query query = new Query();
            query.addCriteria(Criteria.where("_id").is(code));
            DeleteResult result = mongodb.remove(query, Item.class);
            if(result.getDeletedCount() == 1L){
                return 1;
            }
            System.out.println(result);
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public long countItem() {
        Query query = new Query();
        return mongodb.count(query, Item.class);
    }
    
}
