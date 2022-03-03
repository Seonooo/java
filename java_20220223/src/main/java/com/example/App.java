package com.example;
import java.util.Map;

import com.example.db.ItemDB;
import com.example.db.ItemDBImpl;
import com.example.vo.Item;

// 프로그램 시작위치
public class App {
    public static void main( String[] args ){ 
        ItemDB obj = new ItemDBImpl();


        // array => ["aaa","bbb","ccc"]
        //List<String> list = new ArrayList<>();
        // json => {"id":"aaa","name":"bbb","age":13} 한개씩 꺼내고 싶을땐 map
        //Map<String, Object> map1 = new HashMap<>();

        
        // 3. 한개조회
        Map<String, Object> map = obj.selectOneMapItem(3L);
        System.out.println(map.get("ID"));
        System.out.println(map.get("NAME"));
        System.out.println(map.get("PRICE"));
        System.out.println(map.get("QTY"));
        System.out.println(map);

        Item item = obj.selectOneItem(3L);
        System.out.println(item.getNo());
        System.out.println(item.getName());
        System.out.println(item.getPrice());
        System.out.println(item.getQuantity());
        System.out.println(item);
        


        /*
        // 2. 물품 삭제하기
        try {
            int ret = obj.deleteItem(1L);
            System.out.println(ret);
        } catch (Exception e) {
            e.printStackTrace();
        }
        */
        

        /*
        // 1. 물품등록 테스트
        Item item = new Item();
        item.setName("이름1");
        item.setPrice(1000L);
        item.setQuantity(100L);
        int ret = obj.insertItem(item);
        System.out.println(ret);
        */

        //BoardDB obj = new BoardDBImpl();

        /*
        // 1. 게시판 글쓰기 수행
        Board board 
            = new Board(1L, "제목1", "내용1", "작성자1", 100);

        int ret = obj.insertBoard(board);
        System.out.println(ret);
        */
        
        /*
        // 2. 게시판 삭제수행
        Board board = new Board();
        board.setNo(1L);
        int ret = obj.deleteBoard(board);
        System.out.println(ret);
        */

        /*
        // 3. 게시판 수정 수행
        Board board = new Board(1L, "제목111","내용111","작성자111",1000);
        int ret = obj.updateBoard(board);
        System.out.println(ret);
        */

        /*
        // 4. 1개 조회하기
        Board board = new Board();
        board.setNo(1L);
        Board board1 = obj.selectOneBoard(board);
        System.out.println(board1.toString());
        */

        /*
        // 5. 여러개 조회
        List<Board> list = obj.selectListBoard();
        for(Board tmp : list){
            System.out.println(tmp.toString());
        }
        */
    }
}

// while(true){
//     System.out.println("1.추가, 2.삭제, 0.종료");
//     int menu = scanner.nextInt();
//     if(menu == 1){
//         MemberDB obj = new MemberDB();

//         Member member = new Member();
//         member.setId("e");
//         member.setAge(123);
//         member.setRole("CUSTOMER");
//         member.setName("가나다");
//         member.setRegdate("2022");
//         int ret = obj.insertData(member);
        
//         if(ret == 1) {
//             System.out.println("추가성공");
//         }
//         else {
//             System.out.println("추가실패");
//         }
//     }
//     else if (menu == 2){

//     }
//     else if (menu == 3){
//         break;
//     }
// }  