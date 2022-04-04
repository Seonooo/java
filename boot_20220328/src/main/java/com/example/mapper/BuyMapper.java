package com.example.mapper;

import com.example.dto.BuyDTO;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface BuyMapper {

    // INSERT INTO 테이블명(컬럼명들...) VALUES(값들...)
    @Insert({
            "INSERT INTO BUY(BNO, BCNT, ICODE, UEMAIL, BREGDATE) ",
            "VALUES (SEQ_BUY_NO.NEXTVAL, #{obj.bcnt}, ",
            "#{obj.icode}, #{obj.uemail}, CURRENT_DATE)"
    })
    public int insertBuyOne(
            @Param(value = "obj") BuyDTO buy);

}
