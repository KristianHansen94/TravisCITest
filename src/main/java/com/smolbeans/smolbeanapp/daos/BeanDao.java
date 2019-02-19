package com.smolbeans.smolbeanapp.daos;

import com.smolbeans.smolbeanapp.entities.Bean;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class BeanDao {
    private EntityManager entityManager;
    private EntityTransaction entityTransaction;

    public BeanDao(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.entityTransaction = this.entityManager.getTransaction();
    }

    public void persist(String name, int weight) {
        beginTransaction();
        Bean bean = new Bean(name, weight);
        entityManager.persist(bean);
        commitTransaction();
    }

    public Bean find(int id) {
        return entityManager.find(Bean.class, id);
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
