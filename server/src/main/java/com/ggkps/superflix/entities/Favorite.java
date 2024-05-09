package com.ggkps.superflix.entities;

import jakarta.persistence.*;

@Entity(name = "favorite")
@Table
public class Favorite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User user;

    @ManyToOne
    @JoinColumn(name="visual_content_id", nullable=false)
    private VisualContent visual_content;

    public Favorite() {
    }

    public Favorite(User user, VisualContent visual_content) {
        this.user = user;
        this.visual_content = visual_content;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public VisualContent getVisualContent() {
        return visual_content;
    }

    public void setVisualContent(VisualContent visual_content) {
        this.visual_content = visual_content;
    }

    @Override
    public String toString() {
        return "Favorite [id=" + id + ", user=" + user + ", visual_content=" + visual_content + "]";
    }
}
