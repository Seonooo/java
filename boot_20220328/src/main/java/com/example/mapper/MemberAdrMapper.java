package com.example.mapper;

import java.util.List;

import com.example.dto.MemberAdrDTO;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface MemberAdrMapper {

        @Insert({ "INSERT INTO MEMBERADDR(UCODE, uaddress, UPOSTCODE, UEMAIL)",
                        " VALUES(SEQ_MEMBERADDR_UCODE.NEXTVAL, #{obj.uaddress},#{obj.upostcode},#{obj.uemail})" })
        public int insertMemberAdr(@Param(value = "obj") MemberAdrDTO adr);

        @Select({ "SELECT * FROM MEMBERADDR WHERE UEMAIL = #{em} ORDER BY UCHK DESC" })
        public List<MemberAdrDTO> selectMemberAdrList(@Param(value = "em") String em);

        @Update({
                        "UPDATE MEMBERADDR SET UCHK = ",
                        "( SELECT MAX(UCHK) FROM MEMBERADDR WHERE UEMAIL=#{email} ) + 1",
                        " WHERE UCODE=#{code}"
        })
        public int updateMemberAddressSet(@Param("code") long code, @Param("email") String em);

        @Select({
                        "SELECT UCODE, uaddress, UPOSTCODE FROM MEMBERADDR",
                        " WHERE UCODE =#{code} AND UEMAIL =#{email}"
        })
        public MemberAdrDTO selectMemberAdressOne(@Param("code") long code, @Param("email") String em);

        @Delete({ "DELETE FROM MEMBERADDR WHERE UCODE=#{code} AND UEMAIL=#{email}" })
        public int deleteMemeberAddress(@Param("code") long code, @Param("email") String email);

        @Update({
                        "UPDATE MEMBERADDR SET UPOSTCODE=#{obj.upostcode}, ",
                        " UADDRESS=#{obj.uaddress} ",
                        " WHERE UCODE=#{obj.ucode} AND UEMAIL=#{obj.uemail}"
        })
        public int updateMemberAddress(@Param(value = "obj") MemberAdrDTO addr);
}
