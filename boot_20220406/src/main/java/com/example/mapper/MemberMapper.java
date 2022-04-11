package com.example.mapper;

import com.example.dto.MemberDTO;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface MemberMapper {

        @Insert({
                        "INSERT INTO MEMBER",
                        "(UEMAIL, UPW, UNAME, UPHONE, UROLE, UREGDATE)",
                        " VALUES(#{obj.uemail}, #{obj.upw}, #{obj.uname}, #{obj.uphone}, #{obj.urole}, CURRENT_DATE)" })
        public int memberJoin(@Param(value = "obj") MemberDTO member);

        @Select({
                        "SELECT UEMAIL, UNAME, UROLE FROM MEMBER",
                        " WHERE UEMAIL=#{uemail} AND UPW=#{upw}"
        })
        public MemberDTO memberLogin(@Param(value = "uemail") String uemail, @Param(value = "upw") String upw);

        @Select({
                        "SELECT UEMAIL, UPW, UROLE, UPHONE, UNAME FROM MEMBER",
                        " WHERE UEMAIL=#{email}"
        })
        public MemberDTO memberEmail(@Param(value = "email") String em);

        @Update({ "update member set upw=#{upw} where uemail = #{em} " })
        public int updateMemberPassword(@Param(value = "em") String email, @Param(value = "upw") String upw1);

        @Update({ "update member set uphone=#{obj.uphone}, uname=#{obj.uname} where uemail=#{email}" })
        public int updateMemberInfo(@Param(value = "obj") MemberDTO member, @Param(value = "email") String email);

}
