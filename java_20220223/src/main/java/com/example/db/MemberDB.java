package com.example.db;

import java.util.ArrayList;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.example.vo.Member;
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

// 파일명 : MemberDB.java
public class MemberDB {

	// 컬렉션
	private MongoCollection<Document> collection = null;

	// 생성자
	// DB연결
	public MemberDB() {
		String url = "mongodb://id218:pw218@1.234.5.158:37017/db218";
		// 접속하기 static으로 되어있음. 접속은 객체를 n개 생성 불가
		MongoClient client = MongoClients.create(url);

		// db연결
		MongoDatabase db = client.getDatabase("db218");

		// 컬렉션 선택(member2)
		this.collection = db.getCollection("member2");
	}

	// 메소드
	// 추가하기
	public int insertData(Member member) {
		try {
			Document doc = new Document();
			doc.append("_id", member.getId());
			doc.append("name", member.getName());
			doc.append("role", member.getRole());
			doc.append("age", member.getAge());
			doc.append("regdate", member.getRegdate());

			InsertOneResult result = this.collection.insertOne(doc);
			System.out.println("결과 : " + result);

			if (result.getInsertedId().asString().getValue().equals(member.getId())) {
				return 1;
			}
			return 0;
		} catch (Exception e) {
			e.printStackTrace(); // 오류출력
			return -1;
		}
	}

	// 삭제하기
	public int deleteData(Member member) {
		try {
			// 조건 만들기
			Bson bson = Filters.eq("_id", member.getId());
			DeleteResult result = this.collection.deleteOne(bson);
			if (result.getDeletedCount() == 1L) {
				return 1;
			}
			return 0;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	// 수정
	public int updateData(Member member) {

		try {
			// 수정하고자 하는 아이디 (조건)
			Bson bson = Filters.eq("_id", member.getId());

			// 변경하고자 하는 내용
			Bson bson1 = Updates.set("name", member.getName());
			Bson bson2 = Updates.set("age", member.getAge());
			Bson bson3 = Updates.combine(bson1, bson2);

			UpdateResult result = this.collection.updateOne(bson, bson3);
			if (result.getMatchedCount() == 1L) {
				return 1;
			}
			return 0;

		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	// 한개조회
	public Member selectOneData(Member member) {
		try {
			// 조건
			Bson bson = Filters.eq("_id", member.getId());

			FindIterable<Document> docs = this.collection.find(bson);
			Member sendMember = new Member();

			for (Document tmp : docs) {
				sendMember.setId(tmp.getString("_id"));
				sendMember.setAge(tmp.getInteger("age"));
				sendMember.setName(tmp.getString("name"));
				sendMember.setRole(tmp.getString("role"));
				sendMember.setRegdate(tmp.getString("regdate"));
			}
			return sendMember;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// 여러개 조회
	public ArrayList<Member> selectListData() {
		try {
			FindIterable<Document> docs = this.collection.find().sort(Filters.eq("_id", 1));

			// 클래스명<E> list = new 클래스명<E>();
			ArrayList<Member> list = new ArrayList<Member>();
			for (Document tmp : docs) {
				Member sendMember = new Member();
				sendMember.setId(tmp.getString("_id"));
				sendMember.setAge(tmp.getInteger("age"));
				sendMember.setName(tmp.getString("name"));
				sendMember.setRole(tmp.getString("role"));
				sendMember.setRegdate(tmp.getString("regdate"));

				list.add(sendMember);
			}
			return list;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
