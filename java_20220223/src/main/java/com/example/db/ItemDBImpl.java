package com.example.db;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.vo.Item;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.client.result.UpdateResult;

import org.bson.Document;
import org.bson.conversions.Bson;

public class ItemDBImpl implements ItemDB {

    private final String url = "mongodb://id218:pw218@1.234.5.158:37017/db218";
    private MongoCollection<Document> collection = null;    // item2
    private MongoCollection<Document> seqCollection = null; // sequence
    
    // DB접속
    public ItemDBImpl() {
        MongoClient client = MongoClients.create(this.url);
        // db연결
        MongoDatabase db = client.getDatabase("db218");

        // 컬렉션 선택(member2)
        this.seqCollection = db.getCollection("sequence");
        this.collection = db.getCollection("item2");
    }

    @Override
    public int insertItem(Item item) {
        try {
            // 시퀀스값 가져오기
            Bson filter = Filters.eq("_id", "SEQ_ITEM2_NO");
            Bson update = Updates.inc("seq", 1);
            Document doc 
                = this.seqCollection.findOneAndUpdate(filter, update);
            long seq = doc.getLong("seq");
            System.out.println(seq);
            
            Document doc1 = new Document();
            doc1.append("_id", seq); // 자동
            doc1.append("name", item.getName());
            doc1.append("price", item.getPrice());
            doc1.append("quantity", item.getQuantity());

            InsertOneResult result =  this.collection.insertOne(doc1);

            if(result.getInsertedId().asInt64().getValue() 
                    == seq) {
                return 1; //성공
            }    
            return 0; //실패
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public int deleteItem(long code) throws Exception {
        Bson bson = Filters.eq("_id", code);
        DeleteResult result = this.collection.deleteOne(bson);
        if(result.getDeletedCount() == 1L){
            return 1;
        }
        return 0;
        
        
    }

    @Override
    public int updateItem(Item item) {
        try {
            Bson filter = Filters.eq("_id", item.getNo());

            Bson bson1 = Updates.set("name", item.getName());
            Bson bson2 = Updates.set("quantity", item.getQuantity());
            Bson bson3 = Updates.set("price", item.getPrice());
            Bson update = Updates.combine(bson1, bson2, bson3);
            
            UpdateResult result = this.collection.updateOne(filter, update);

            if(result.getModifiedCount() == 1L){
                return 1;
            }
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public Map<String, Object> selectOneMapItem(long code) {
        try {
            Bson filter = Filters.eq("_id", code);
            Map<String, Object> map = new HashMap<>();

            FindIterable<Document> rows 
            = this.collection.find(filter);
            for(Document tmp : rows){
                map.put("ID", tmp.getLong("_id"));
                map.put("NAME", tmp.getString("name"));
                map.put("PRICE", tmp.getLong("price"));
                map.put("QTY", tmp.getLong("quantity"));
            }
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Item selectOneItem(long code) {
        try {
            Item item = new Item();
            Bson filter = Filters.eq("_id", code);
            FindIterable<Document> rows = this.collection.find(filter);
            for(Document tmp : rows){
                item.setNo(tmp.getLong("_id"));
                item.setName(tmp.getString("name"));
                item.setPrice(tmp.getLong("price"));
                item.setQuantity(tmp.getLong("quantity"));
            }
            return item;

        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<Item> selectListItem() {
        try {
            Bson sort = Filters.eq("_id", 1);

            // 일부데이터만 가져오기 projection
            // 제외시킴(exclude), 포함시킴(include)
            Bson projection = Projections.exclude("name");

            FindIterable<Document> rows = this.collection.find().projection(projection).sort(sort);
            List<Item> list = new ArrayList<Item>();

            for(Document doc : rows){
                Item item = new Item(
                    doc.getLong("_id"),
                    doc.getLong("price"),
                    doc.getLong("quantity"),
                    null
                );
                list.add(item);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Item> selectListPageItem(int page) {
        try {
            Bson sort = Filters.eq("_id", 1);

            // 페이지 1 => 0,10
            // 페이지 2 => 10, 10
            // 페이지 3 => 20
            int skip = (page-1)*10;
            int limit = 10;

            // 일부데이터만 가져오기 projection
            // 제외시킴(exclude), 포함시킴(include)
            FindIterable<Document> rows = this.collection.find().sort(sort).skip(skip).limit(limit);
            List<Item> list = new ArrayList<Item>();

            for(Document doc : rows){
                Item item = new Item(
                    doc.getLong("_id"),
                    doc.getLong("price"),
                    doc.getLong("quantity"),
                    doc.getString("name")
                );
                list.add(item);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    

    

    
    
}
