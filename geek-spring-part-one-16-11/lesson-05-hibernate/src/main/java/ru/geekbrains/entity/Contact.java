package ru.geekbrains.entity;

import javax.persistence.*;

@Entity
@Table(name = "contacts")
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false, length = 512)
    private String contactText;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Contact() {
    }

    public Contact(Long id, String type, String contactText, User user) {
        this.id = id;
        this.type = type;
        this.contactText = contactText;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContactText() {
        return contactText;
    }

    public void setContactText(String contactText) {
        this.contactText = contactText;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", contactText='" + contactText + '\'' +
                ", userId=" + user.getId() +
                '}';
    }
}
