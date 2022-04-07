package com.example.mapper;

import java.util.List;

import com.example.dto.ItemDTO;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;

@Mapper
public interface ItemMapper {

        // 물품목록
        @Select({ "SELECT * FROM (",
                        "SELECT ",
                        "I.ICODE, I.INAME, I.IPRICE, I.IQUANTITY, I.IREGDATE,ROW_NUMBER() OVER (ORDER BY I.ICODE DESC) ROWN",
                        " FROM ITEM I	WHERE I.INAME LIKE '%' || #{txt} || '%'AND I.UEMAIL = #{email})",
                        "WHERE ROWN BETWEEN #{start} AND #{end}" })
        public List<ItemDTO> selectItemList(
                        @Param(value = "email") String email,
                        @Param(value = "txt") String txt,
                        @Param(value = "start") int start,
                        @Param(value = "end") int end);

        // 물품추가
        @Insert({ " INSERT INTO ITEM(ICODE, INAME, ICONTENT, IPRICE, ",
                        "IQUANTITY, IIMAGE, IIMAGESIZE, IIMAGETYPE, ",
                        "IIMAGENAME, UEMAIL)",
                        " VALUES(SEQ_ITEM_ICODE.NEXTVAL, #{obj.iname},",
                        " #{obj.icontent}, #{obj.iprice},",
                        " #{obj.iquantity}, #{obj.iimage, jdbcType=BLOB},",
                        " #{obj.iimagesize}, #{obj.iimagetype},",
                        " #{obj.iimagename}, #{obj.uemail})" })
        public int insertItemOne(@Param(value = "obj") ItemDTO item);

        // 물품한개
        @Select({ "SELECT ICODE, INAME, ICONTENT, IPRICE, IQUANTITY,",
                        " IREGDATE FROM ITEM WHERE ICODE = #{code}" })
        public ItemDTO selectItemOne(long code);

        // 물품개수
        @Select({ "SELECT COUNT(*) CNT ",
                        "FROM ITEM I WHERE ",
                        "I.INAME LIKE '%' || #{txt} || '%' AND I.UEMAIL = #{uemail}" })
        public long selectItemCount(
                        @Param(value = "txt") String txt,
                        @Param(value = "uemail") String uemail);

        // 이미지 가져오기
        @Results({
                        @Result(column = "icode", property = "icode"),
                        @Result(column = "iimage", property = "iimage", jdbcType = JdbcType.BLOB, javaType = byte[].class)
        })
        @Select({ "SELECT ICODE, IIMAGE, IIMAGESIZE, IIMAGETYPE, IIMAGENAME",
                        "FROM ITEM WHERE ICODE = #{code}" })
        public ItemDTO selectItemImageOne(long code);

        // 물품삭제
        @Delete({ "DELETE FROM ITEM WHERE ICODE = #{code} and uemail = #{email}" })
        public int deleteItemOne(@Param(value = "code") long code, @Param(value = "email") String email);

        // 물품 변경
        @Update({
                        "<script>",
                        "UPDATE ITEM SET INAME =#{obj.iname}, ICONTENT=#{obj.icontent},",
                        " IPRICE=#{obj.iprice}, IQUANTITY=#{obj.iquantity}",
                        "<if test = 'obj.iimage != null'>",
                        ", iimage=#{obj.iimage} ",
                        ", iimagesize = #{obj.iimagesize}",
                        ", iimagename = #{obj.iimagename}",
                        ", iimagetype = #{obj.iimagetype}",
                        "</if>",
                        "where icode = #{obj.icode} and uemail=#{obj.uemail}",
                        "</script>" })
        public int updateItemOne(@Param(value = "obj") ItemDTO item);
}
