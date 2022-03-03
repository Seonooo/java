package com.example.db;

import java.util.ArrayList;
import java.util.List;

import com.example.vo.Board;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.client.result.UpdateResult;

import org.bson.Document;
import org.bson.conversions.Bson;

public class BoardDBImpl implements BoardDB {
    private final String url = "mongodb://id218:pw218@1.234.5.158:37017/db218";
    private MongoCollection<Document> collection = null;
    // DB연결하기
    public BoardDBImpl(){
        // 접속하기 static으로 되어있음. 접속은 객체를 n개 생성 불가
        MongoClient client = MongoClients.create(this.url);

        // db연결
        MongoDatabase db = client.getDatabase("db218");

        // 컬렉션 선택(member2)
        this.collection = db.getCollection("board2");
    }
    
    @Override
    public int insertBoard(Board board) {
        try {
            Document doc = new Document();
            doc.append("_id", board.getNo());
            doc.append("content", board.getContent());
            doc.append("hit", board.getHit());
            doc.append("title", board.getTitle());
            doc.append("writer", board.getWriter());

            // 컬렉션에 데이터 추가하기 document가 필요
            // result에 추가했던 _id의 값이 리턴됨
            InsertOneResult result = this.collection.insertOne(doc);

            // String aa="aa" => if(aa.equals("aa"))
            // long aa=1L; => if(aa == 1L)
            if(result.getInsertedId().asInt64().getValue() == board.getNo()){
                return 1;
            }
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
        
    }

    @Override
    public int deleteBoard(Board board) {
        try {
            Bson bson = Filters.eq("_id", board.getNo());
            DeleteResult result = this.collection.deleteOne(bson);
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
    public int updateBoard(Board board) {
        try {
            Bson bson = Filters.eq("_id", board.getNo());

            Bson bson1 = Updates.set("title", board.getTitle());
            Bson bson2 = Updates.set("content", board.getContent());
            Bson bson3 = Updates.set("writer", board.getWriter());

            Bson bson4 =  Updates.combine(bson1, bson2, bson3);
            
            UpdateResult result = this.collection.updateOne(bson, bson4);
            if(result.getModifiedCount()  == 1L){
                return 1;
            }
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public Board selectOneBoard(Board board) {
        try {
            Bson bson = Filters.eq("_id", board.getNo());

            // List<Document>
            FindIterable<Document> rows = this.collection.find(bson);

            for(Document tmp : rows){ // 1번만 반복
                return new Board(
                    tmp.getLong("_id"),
                    tmp.getString("title"),
                    tmp.getString("content"),
                    tmp.getString("writer"),
                    tmp.getInteger("hit")
                );
            }
            return null;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Board> selectListBoard() {
        try {
            // 전체 조회
            FindIterable<Document> rows = this.collection.find();

            // Board를 n개 보관할 수 있는 list 변수
            // 가변길이 배열 [{},{},{}]
            List<Board> list = new ArrayList<Board>();

            for(Document tmp : rows){
                // 여러개를 보관할때는 객체를 여기에 만듬
                Board board = new Board(
                    tmp.getLong("_id"),
                    tmp.getString("title"),
                    tmp.getString("content"),
                    tmp.getString("writer"),
                    tmp.getInteger("hit")
                );
                list.add(board);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
}
