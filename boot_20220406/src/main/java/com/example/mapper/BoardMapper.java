package com.example.mapper;

import com.example.dto.BoardDTO;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface BoardMapper {

    @Insert({ "insert into board(bno, btitle, bcontent, bhit, bregdate, btype, uemail)",
            " values( seq_board_no.nextval, #{brd.btitle}, #{brd.bcontent}, #{brd.bhit}, current_date, #{brd.btype}, #{brd.uemail})" })
    public int insertBoardOne(@Param(value = "brd") BoardDTO board);

    @Delete({ "delete from board where bno = #{bno}" })
    public int deleteBoardOne(@Param(value = "bno") long bno);

    @Update({ " update board set btitle =#{obj.btitle}, bcontent = #{obj.bcontent} where bno = #{obj.bno}" })
    public int updateBoardOne(@Param(value = "obj") BoardDTO board);
}
