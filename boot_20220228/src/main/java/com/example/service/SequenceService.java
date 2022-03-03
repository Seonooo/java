package com.example.service;

import com.example.entity.Sequence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

// 다른 service에서 시퀀스가 필요할때 사용할 용도
@Service
public class SequenceService {

    @Autowired
    private MongoTemplate mongoDB;

    public long generatorSequence(String seqName){
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(seqName));

        Update update = new Update();
        update.inc("seq", 1);

        FindAndModifyOptions options = new FindAndModifyOptions();
        options.returnNew(true);
        options.upsert(true);

        Sequence counter
            = mongoDB.findAndModify(query, update, options, Sequence.class);

        if(counter != null){
            return counter.getSeq();
        }
        return 0;
    }
    
}
