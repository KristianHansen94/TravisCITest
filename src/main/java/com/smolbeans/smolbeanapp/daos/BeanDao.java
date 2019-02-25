package com.smolbeans.smolbeanapp.daos;

import com.smolbeans.smolbeanapp.entities.Bean;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

public class BeanDao {
    private EntityManager entityManager;
    private EntityTransaction entityTransaction;

    public BeanDao(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.entityTransaction = this.entityManager.getTransaction();
    }

    public Bean persist(Bean bean) {
        beginTransaction();
        entityManager.persist(bean);
        commitTransaction();
        return bean;
    }

    public Bean find(int id) {
        return entityManager.find(Bean.class, id);
    }

    public List<Bean> findAll(){
        return entityManager.createQuery("FROM Bean").getResultList();
    }

    public void update(int id, String name, int weight) {
        beginTransaction();
        Bean bean = entityManager.find(Bean.class, id);
        bean.setName(name);
        bean.setWeight(weight);
        entityManager.merge(bean);
        commitTransaction();
    }

    public void remove(int id) {
        beginTransaction();
        Bean bean = entityManager.find(Bean.class, id);
        entityManager.remove(bean);
        commitTransaction();
    }

    private void beginTransaction() {
        try {
            entityTransaction.begin();
        } catch (IllegalStateException e) {
            rollbackTransaction();
        }
    }

    private void commitTransaction() {
        try {
            entityTransaction.commit();
            entityManager.close();
        } catch (IllegalStateException e) {
            rollbackTransaction();
        }
    }

    private void rollbackTransaction() {
        try {
            entityTransaction.rollback();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
    }
}
