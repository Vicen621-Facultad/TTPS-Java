package io.github.vicen621.blogmensajes.model;

public class Message {
    private long id;
    private String text;
    private User sender;

    public Message(String text, User sender) {
        this.id = 0;
        this.text = text;
        this.sender = sender;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public User getSender() {
        return sender;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", sender=" + sender +
                '}';
    }
}
