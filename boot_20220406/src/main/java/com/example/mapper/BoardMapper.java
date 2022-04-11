package com.example.mapper;

import java.util.List;

import com.example.dto.BoardDTO;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface BoardMapper {

    // 게시물 등록
    @Insert({ "insert into board(bno, btitle, bcontent, bhit, bregdate, btype, uemail)",
            " values( seq_board_no.nextval, #{brd.btitle}, #{brd.bcontent}, #{brd.bhit}, current_date, #{brd.btype}, #{brd.uemail})" })
    public int insertBoardOne(@Param(value = "brd") BoardDTO board);

    // 게시물 삭제
    @Delete({ "delete from board where bno = #{bno}" })
    public int deleteBoardOne(@Param(value = "bno") long bno);

    // 게시물 수정
    @Update({ " update board set btitle =#{brd.btitle}, bcontent = #{brd.bcontent} where bno = #{brd.bno}" })
    public int updateBoardOne(@Param(value = "brd") BoardDTO board);

    // 조회수 1증가
    @Update({ "update board set bhit=bhit+1 where bno=#{bno}" })
    public int updateBoardHit(@Param(value = "bno") long bno);

    // 페이지 네이션
    @Select({ "select * from (", "select b.*, row_number() over (order by bno desc) rown from",
            " board b) where rown between #{start} and #{end}" })
    public List<BoardDTO> selectBoardList(@Param(value = "start") int s, @Param(value = "end") int e);

    // 게시물 1개 조회
    @Select({ " select * from board where bno = #{bno}" })
    public BoardDTO selectBoardOne(@Param(value = "bno") long bno);

}
