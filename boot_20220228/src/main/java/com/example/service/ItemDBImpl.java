package com.example.service;

import java.util.Date;
import java.util.List;

import com.example.entity.Item;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

@Service
public class ItemDBImpl implements ItemDB{

    @Autowired
    private MongoTemplate mongodb;

    @Autowired
    private SequenceService sequence;
    
    @Override
    public int insertItem(Item item) {
        try {
            long seq = sequence.generatorSequence("SEQ_ITEM4_NO");
            item.setCode(seq);

            item.setRegdate(new Date());
            Item item1 = mongodb.insert(item);
            if(item1.getCode() == seq){
                return 1;
            }
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public int deleteItem(Long id) {
        try {
            Query query = new Query();
            query.addCriteria(Criteria.where("_id").is(id));
            DeleteResult result = 
            mongodb.remove(query, Item.class);
            if(result.getDeletedCount() == 1L){
                return 1;
            }
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public List<Item> selectListItem(Pageable pageable) {
        try {
            Query query = new Query();
            query.with(pageable); // skip().limit()
            query.fields().exclude("filedata", "filetype", "filesize", "filename"); //projection

            Sort sort = Sort.by(Direction.DESC, "_id"); 
            query.with(sort); // sort()

            return mongodb.find(query, Item.class);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Item selectOneImage(long code) {
        try {
            Query query = new Query();
            Criteria criteria = Criteria.where("_id").is(code);
            query.addCriteria(criteria);

            query.fields().include("filedate", "filetype","filesize");

            return mongodb.findOne(query, Item.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Item selectOneItem(long code) {
        try {
            Query query = new Query();
            query.addCriteria(Criteria.where("_id").is(code));
            return mongodb.findOne(query, Item.class);
            
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public long selectItemCount() {
        try {
            return mongodb.count(new Query(), Item.class);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int updateItemOne(Item item) {
        try {
            Query query =new Query();
            query.addCriteria(Criteria.where("_id").is(item.getCode()));

            Update update = new Update();
            update.set("name", item.getName());
            update.set("price", item.getPrice());
            update.set("quantity", item.getQuantity());
            if(item.getFilesize()>0){
                update.set("filedate", item.getFiledate());
                update.set("filename", item.getFilename());
                update.set("filesize", item.getFilesize());
                update.set("filetype", item.getFiletype());
            }
            UpdateResult result=
            mongodb.updateFirst(query, update, Item.class);
            if(result.getModifiedCount() == 1L){
                return 1;
            }
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    
}
