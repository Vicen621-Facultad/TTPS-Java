package io.github.vicen621.blogmensajes.persistance.dao;

import io.github.vicen621.blogmensajes.persistance.EntityManagerListener;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.List;

public abstract class BaseDAO<T> {
    protected EntityManager getEntityManager() {
        return EntityManagerListener.getEntityManagerFactory().createEntityManager();
    }

    public void create(T entity) {
        EntityManager em = this.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(entity);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            System.err.println("Error connecting to the database: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    public void update(T entity) {
        EntityManager em = this.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.merge(entity);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            System.err.println("Error connecting to the database: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    public void delete(T entity) {
        EntityManager em = this.getEntityManager();
        try {
            em.getTransaction().begin();
            em.remove(em.contains(entity) ? entity : em.merge(entity));
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.err.println("Error connecting to the database: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    public abstract List<T> findAll();

    public abstract T findById(long id);
}
