package io.github.vicen621.blogmensajes.persistance.dao;

public class FactoryDAO {
    public static UserDAO getUserDAO() {
        return new UserDAO();
    }

    public static MessageDAO getMessageDAO() {
        return new MessageDAO();
    }
}
