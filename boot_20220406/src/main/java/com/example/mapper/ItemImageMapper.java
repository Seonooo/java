package com.example.mapper;

import java.util.List;
import com.example.dto.ItemImageDTO;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import io.lettuce.core.dynamic.annotation.Param;

@Mapper
public interface ItemImageMapper {

    @Select({ "select imgcode from itemimage where icode=#{icode}" })
    public List<Long> selectItemImageList(@Param(value = "icode") long code);

    @Select({ "select * from itemimage where imgcode=#{imgcode}" })
    public ItemImageDTO selectItemImageOne(@Param(value = "imgcode") long code);

    @Update({
            "<script>",
            "update itemimage set",
            "iimagesize=#{obj.iimagesize},",
            "iimagetype=#{obj.iimagesize},",
            "iimagename=#{obj.iimagename},",
            "iimage=#{obj.iimage, jdbcType=BLOB}",
            " where imgcode = #{obj.imgcode}",
            "</script>" })
    public int updateItemImageOne(@Param(value = "obj") ItemImageDTO obj);

    @Insert({
            "<script>",
            "insert all",
            "<foreach collection='list' item='obj' separator=''>",
            " into itemimage(imgcode, iimage, iimagesize, iimagetype, iimagename, icode",
            " values(#{obj.imgcode}, #{obj.iimage}, #{obj.iimagesize}, #{obj.iimagetype}, #{obj.iimagename}, #{obj.icode})",
            " </foreach>", "select * from dual ", "</script>" })
    public int insertItemImageBatch(@Param(value = "list") List<ItemImageDTO> list);

    @Delete({ "delete from itemimage where imgcode=#{imgcode}" })
    public int deleteItemImageOne(@Param(value = "imgcode") long code);

    @Insert({ "insert into itemimage(imgcode, iimage, iimagesize, iimagetype, iimagename, icode",
            " values(#{obj.imgcode}, #{obj.iimage}, #{obj.iimagesize}, #{obj.iimagetype}, #{obj.iimagename}, #{obj.icode})" })
    public int insertItemImageOne(@Param(value = "obj") ItemImageDTO obj);

    /*
     * <insert id="insertItemImageOne"
     * parameterType="com.example.dto.ItemImageDTO">
     * INSERT INTO ITEMIMAGE( IMGCODE, IIMAGE, IIMAGESIZE,
     * IIMAGETYPE, IIMAGENAME, ICODE)
     * VALUES ( #{imgcode},
     * #{iimage, jdbcType=BLOB},
     * #{iimagesize}, #{iimagetype},
     * #{iimagename}, #{icode} )
     * </insert>
     */

}
