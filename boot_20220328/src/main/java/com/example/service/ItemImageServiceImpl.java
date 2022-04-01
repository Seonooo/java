package com.example.service;

import java.util.List;

import com.example.dto.ItemImageDTO;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemImageServiceImpl implements ItemImageService {

    @Autowired
    SqlSessionFactory sqlFactory;

    @Override
    public int insertItemImageBatch(List<ItemImageDTO> list) {

        int cnt = 0;
        for (ItemImageDTO item : list) {
            long seq = sqlFactory.openSession()
                    .selectOne("ItemImage.seqItemImage");
            item.setImgcode(seq);
            cnt = cnt + sqlFactory.openSession()
                    .insert("ItemImage.insertItemImageOne", item);

        }
        if (list.size() == cnt) {
            return 1;
        }

        return 0;
    }

    @Override
    public ItemImageDTO selectItemImageOne(long code) {
        return sqlFactory.openSession().selectOne("ItemImage.selectItemImageOne", code);
    }

    @Override
    public int updateItemImageOne(ItemImageDTO itemimage) {
        return sqlFactory.openSession().update("ItemImage.updateItemImageOne", itemimage);
    }

    @Override
    public int deleteItemImageOne(long code) {
        return sqlFactory.openSession().delete("ItemImage.deleteItemImageOne", code);
    }

    @Override
    public List<Long> selectItemImageList(long icode) {
        return sqlFactory.openSession().selectList("ItemImage.selectItemImageList", icode);
    }

}
