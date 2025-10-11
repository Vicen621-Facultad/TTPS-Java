package io.github.vicen621.blogmensajes.model;

import jakarta.persistence.*;

@Entity
@Table(name = "messages")
@NamedQueries({
        @NamedQuery(name = "Message.findAll", query = "SELECT m FROM Message m"),
        @NamedQuery(name = "Message.findById", query = "SELECT m FROM Message m WHERE m.id = :id"),
        @NamedQuery(name = "Message.findBySender", query = "SELECT m FROM Message m WHERE m.sender = :sender")
})
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "message_id")
    private long id;
    private String text;

    @ManyToOne
    @JoinColumn(name = "sender_id")
    private User sender;

    protected Message() {

    }

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

    public void setSender(User sender) {
        this.sender = sender;
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
