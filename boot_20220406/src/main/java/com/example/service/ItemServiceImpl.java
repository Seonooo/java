package com.example.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.ItemEntity;
import com.example.repository.ItemRepository;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    ItemRepository iRepository;
    @Autowired
    EntityManagerFactory emf;

    @Override
    public List<ItemEntity> selectItemEntityIn(Long[] no) {
        return iRepository.findByIcodeIn(no);
    }

    @Override
    public int updateItemBatch(List<ItemEntity> list) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            // 여러개 추가
            for (ItemEntity item : list) {
                // 기존의 데이터 추출
                ItemEntity oldItem = em.find(ItemEntity.class, item.getIcode());

                // 변경할 항복 set
                oldItem.setIname(item.getIname());
                oldItem.setIcontent(item.getIcontent());
                oldItem.setIprice(item.getIprice());
                oldItem.setIquantity(item.getIquantity());

                // 새로 save
                em.persist(oldItem);
            }
            em.getTransaction().commit();
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
            return 0;
        }
    }

    @Override
    public int deleteItemBatch(Long[] no) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            // 여러개 추가
            for (Long tmp : no) {
                ItemEntity oldItem = em.find(ItemEntity.class, tmp);
                em.remove(oldItem);
            }
            em.getTransaction().commit();
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
            return 0;
        }
    }

    @Override
    public int insertItemBatch(List<ItemEntity> list) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            // 여러개 추가
            for (ItemEntity item : list) {
                em.persist(item);
            }
            em.getTransaction().commit();
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
            return 0;
        }
    }

}