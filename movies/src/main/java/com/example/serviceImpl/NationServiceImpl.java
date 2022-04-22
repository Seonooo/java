package com.example.serviceImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import com.example.entity.NationEntity;
import com.example.service.NationService;

import org.springframework.beans.factory.annotation.Autowired;

public class NationServiceImpl implements NationService {

    @Autowired
    EntityManagerFactory emf;

    @Override
    public int insertNationList(List<NationEntity> list) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            for (NationEntity nation : list) {
                em.persist(nation);
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
