package io.github.vicen621.blogmensajes.model;

public class User {
    private long id;
    private String username;

    public User(String username) {
        this.id = 0;
        this.username = username;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                '}';
    }
}
