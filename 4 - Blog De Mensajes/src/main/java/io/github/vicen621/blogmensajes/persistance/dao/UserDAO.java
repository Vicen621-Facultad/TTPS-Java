package io.github.vicen621.blogmensajes.persistance.dao;

import io.github.vicen621.blogmensajes.model.User;
import io.github.vicen621.blogmensajes.persistance.SqlDataSource;

import java.sql.*;
import java.util.List;

public class UserDAO implements BaseDAO<User> {

    public User findByUsername(String username) {
        User user = null;
        try (Connection conn = SqlDataSource.getDataSource().getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Users WHERE username = ?");
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                user = new User(rs.getString("username"));
                user.setId(rs.getLong("id"));
            }
        } catch (SQLException e) {
            System.err.println("Error connecting to the database: " + e.getMessage());
        }
        return user;
    }

    @Override
    public List<User> findAll() {
        try (Connection conn = SqlDataSource.getDataSource().getConnection()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Users");
            List<User> users = new java.util.ArrayList<>();
            while (rs.next()) {
                User user = new User(rs.getString("username"));
                user.setId(rs.getLong("id"));
                users.add(user);
            }
            return users;
        } catch (SQLException e) {
            System.err.println("Error connecting to the database: " + e.getMessage());
            return java.util.Collections.emptyList();
        }
    }

    @Override
    public User findById(long id) {
        User user = null;
        try (Connection conn = SqlDataSource.getDataSource().getConnection()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Users WHERE id = " + id);
            if (rs.next()) {
                user = new User(rs.getString("username"));
                user.setId(rs.getLong("id"));
            }
        } catch (SQLException e) {
            System.err.println("Error connecting to the database: " + e.getMessage());
        }
        return user;
    }

    @Override
    public void create(User entity) {
        String sql = "INSERT INTO Users (username) VALUES (?)";
        try (Connection conn = SqlDataSource.getDataSource().getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, entity.getUsername());
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
    public void update(User entity) {
        try (Connection conn = SqlDataSource.getDataSource().getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("UPDATE Users SET username = ? WHERE id = ?");
            stmt.setString(1, entity.getUsername());
            stmt.setLong(2, entity.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error connecting to the database: " + e.getMessage());
        }
    }

    @Override
    public void delete(User entity) {
        try (Connection conn = SqlDataSource.getDataSource().getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM Users WHERE id = ?");
            stmt.setLong(1, entity.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error connecting to the database: " + e.getMessage());
        }
    }
}
