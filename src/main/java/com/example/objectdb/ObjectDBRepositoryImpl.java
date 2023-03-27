package com.example.objectdb;

import com.example.Survey;
import com.example.SurveyQuestion;

import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;

public class ObjectDBRepositoryImpl<T> implements ObjectDBRepository<T, Long> {

    private final Class<T> entityType;

    public ObjectDBRepositoryImpl(Class<T> entityType) {
        this.entityType = entityType;
    }

    @Override
    public void create(T entity) {
        EntityManager em = ObjectDBEntityManagerFactory.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(entity);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Override
    public Optional<T> findById(Long id) {
        EntityManager em = ObjectDBEntityManagerFactory.getEntityManager();
        try {
            return Optional.ofNullable(em.find(entityType, id));
        } finally {
            em.close();
        }
    }

    @Override
    public T save(T entity) {
        EntityManager em = ObjectDBEntityManagerFactory.getEntityManager();
        Survey s = new Survey();

        em.getTransaction().begin();
        em.persist(s);
        em.getTransaction().commit();
        return entity;
    }

    @Override
    public List<SurveyQuestion> findBySurvey(Survey survey) {
        return null;
    }

    @Override
    public List<T> findAll() {
        EntityManager em = ObjectDBEntityManagerFactory.getEntityManager();
        try {
            return em.createQuery("SELECT e FROM " + entityType.getSimpleName() + " e", entityType).getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public void update(T entity) {
        EntityManager em = ObjectDBEntityManagerFactory.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(entity);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Override
    public void delete(T id) {
        EntityManager em = ObjectDBEntityManagerFactory.getEntityManager();
        try {
            em.getTransaction().begin();
            T entity = em.find(entityType, id);
            em.remove(entity);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
}
