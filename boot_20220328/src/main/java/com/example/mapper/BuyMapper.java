package com.example.mapper;

import java.util.List;
import java.util.Map;

import com.example.dto.BuyDTO;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface BuyMapper {

        // INSERT INTO 테이블명(컬럼명들...) VALUES(값들...)
        @Insert({
                        "INSERT INTO BUY(BNO, BCNT, ICODE, UEMAIL, BREGDATE) ",
                        "VALUES (SEQ_BUY_NO.NEXTVAL, #{obj.bcnt}, ",
                        "#{obj.icode}, #{obj.uemail}, CURRENT_DATE)"
        })
        public int insertBuyOne(@Param(value = "obj") BuyDTO buy);

        @Select({ "select * from buylist_view where uemail = #{em}" })
        public List<Map<String, Object>> selectBuyListMap(@Param(value = "em") String em);

}
