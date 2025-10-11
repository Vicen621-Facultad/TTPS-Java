package io.github.vicen621.blogmensajes.persistance.dao;

import io.github.vicen621.blogmensajes.model.Message;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class MessageDAO extends BaseDAO<Message> {
    @Override
    public List<Message> findAll() {
        EntityManager em = this.getEntityManager();
        TypedQuery<Message> query = em.createNamedQuery("Message.findAll", Message.class);
        return query.getResultList();
    }

    @Override
    public Message findById(long id) {
        EntityManager em = this.getEntityManager();
        TypedQuery<Message> query = em.createNamedQuery("Message.findById", Message.class);
        query.setParameter("id", id);
        List<Message> results = query.getResultList();
        return results.isEmpty() ? null : results.get(0);
    }
}
