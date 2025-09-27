package io.github.vicen621.blogmensajes.persistance.dao;

import io.github.vicen621.blogmensajes.model.Message;
import io.github.vicen621.blogmensajes.model.User;
import io.github.vicen621.blogmensajes.persistance.SqlDataSource;

import java.sql.*;
import java.util.List;

public class MessageDAO implements BaseDAO<Message> {
    @Override
    public List<Message> findAll() {
        try (Connection conn = SqlDataSource.getDataSource().getConnection()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Messages");
            List<Message> messages = new java.util.ArrayList<>();
            while (rs.next()) {
                User sender = FactoryDAO.getUserDAO().findById(rs.getLong("user_id"));
                Message message = new Message(rs.getString("message"), sender);
                message.setId(rs.getLong("id"));
                messages.add(message);
            }
            return messages;
        } catch (SQLException e) {
            System.err.println("Error connecting to the database: " + e.getMessage());
            return java.util.Collections.emptyList();
        }
    }

    @Override
    public Message findById(long id) {
        Message message = null;
        try (Connection conn = SqlDataSource.getDataSource().getConnection()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Messages WHERE id = " + id);
            if (rs.next()) {
                User sender = FactoryDAO.getUserDAO().findById(rs.getLong("user_id"));
                message = new Message(rs.getString("text"), sender);
                message.setId(rs.getLong("id"));
            }
        } catch (SQLException e) {
            System.err.println("Error connecting to the database: " + e.getMessage());
        }
        return message;
    }

    @Override
    public void create(Message entity) {
        String sql = "INSERT INTO Messages (message, user_id) VALUES (?, ?)";
        try (Connection conn = SqlDataSource.getDataSource().getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, entity.getText());
            stmt.setLong(2, entity.getSender().getId());
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    entity.setId(rs.getLong(1));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error connecting to the database: " + e.getMessage());
        }
    }

    @Override
    public void update(Message entity) {
        try (Connection conn = SqlDataSource.getDataSource().getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("UPDATE Messages SET text = ?, user_id = ? WHERE id = ?");
            stmt.setString(1, entity.getText());
            stmt.setLong(2, entity.getSender().getId());
            stmt.setLong(3, entity.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error connecting to the database: " + e.getMessage());
        }
    }

    @Override
    public void delete(Message entity) {
        try (Connection conn = SqlDataSource.getDataSource().getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM Messages WHERE id = ?");
            stmt.setLong(1, entity.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error connecting to the database: " + e.getMessage());
        }
    }
}
