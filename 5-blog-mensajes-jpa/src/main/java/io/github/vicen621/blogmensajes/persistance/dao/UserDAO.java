package io.github.vicen621.blogmensajes.persistance.dao;

import io.github.vicen621.blogmensajes.model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class UserDAO extends BaseDAO<User> {

    public User findByUsername(String username) {
        EntityManager em = getEntityManager();
        TypedQuery<User> query = em.createNamedQuery("User.findByUsername", User.class);
        query.setParameter("username", username);
        List<User> results = query.getResultList();
        return results.isEmpty() ? null : results.get(0);
    }

    @Override
    public List<User> findAll() {
        EntityManager em = this.getEntityManager();
        TypedQuery<User> query = em.createNamedQuery("User.findAll", User.class);
        return query.getResultList();
    }

    @Override
    public User findById(long id) {
        EntityManager em = this.getEntityManager();
        TypedQuery<User> query = em.createNamedQuery("User.findById", User.class);
        query.setParameter("id", id);
        List<User> results = query.getResultList();
        return results.isEmpty() ? null : results.get(0);
    }
}
