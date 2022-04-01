package com.example.mapper;

import java.util.List;

import com.example.dto.ItemDTO;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ItemMapper {

    @Select({
            "<script>",
            "SELECT * FROM (",
            " SELECT ",
            " ICODE, INAME, IPRICE, ",
            " ROW_NUMBER() OVER (",
            "<choose>",
            "<when test='type == 1'>ORDER BY IREGDATE DESC</when>",
            "<when test='type == 2'>ORDER BY INAME ASC</when>",
            "<when test='type == 3'>ORDER BY IPRICE ASC</when>",
            "</choose>",
            ") ROWN ",
            " FROM ",
            "ITEM ",
            ") WHERE ROWN BETWEEN 1 AND  12 ORDER BY ROWN ASC",
            "</script>"

    })
    public List<ItemDTO> selectItemList(@Param(value = "type") int type);

    @Select({
            "SELECT",
            " I.ICODE, I.INAME, I.ICONTENT, I.IPRICE, I.IQUANTITY",
            " FROM ITEM I WHERE I.ICODE=#{code}"
    })
    public ItemDTO selectItemOne(@Param(value = "code") long code);
}
