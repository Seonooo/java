package com.example.mapper;

import java.util.List;

import com.example.dto.ItemImageDTO;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.web.bind.annotation.RequestParam;

@Mapper
public interface ItemImageMapper {

    // 물품코드가 오면 관련되어 있는 서브이미지 번호들
    @Select({ "SELECT imgcode FROM ITEMIMAGE WHERE ICODE=#{code}" })
    public List<Long> selectItemImageCodeList(@RequestParam(value = "code") long code);

    // 서브이미지코드에 해당하는 이미지주소를 반환
    @Select({
            "SELECT * FROM ITEMIMAGE WHERE IMGCODE=#{imgcode}"
    })
    public ItemImageDTO selectItemImageCodeOne(@RequestParam(value = "imgcode") long imgcode);

}
