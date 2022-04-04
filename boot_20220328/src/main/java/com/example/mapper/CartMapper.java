package com.example.mapper;

import com.example.dto.CartDTO;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CartMapper {
    @Insert({
            "Insert into cart(cno, ccnt, icode, uemail) ",
            "values(seq_cart_no.nextval, #{obj.ccnt}, #{obj.icode}, #{obj.uemail})"
    })
    public int insertCartOne(@Param(value = "obj") CartDTO cart);

}
