package com.ggkps.superflix.entities;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity(name = "comment")
@Table
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String content;

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User user;

    @ManyToOne
    @JoinColumn(name="forum_id", nullable=false)
    private Forum forum;

    public Comment() {
    }

    public Comment(String content) {
        this.content = content;
    }

    public Comment(String content, User user) {
        this.content = content;
        this.user = user;
    }

    public Comment(String content, User user, Forum forum) {
        this.content = content;
        this.user = user;
        this.forum = forum;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Forum getForum() {
        return forum;
    }

    public void setForum(Forum forum) {
        this.forum = forum;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", user=" + user +
                ", forum=" + forum +
                '}';
    }
}
