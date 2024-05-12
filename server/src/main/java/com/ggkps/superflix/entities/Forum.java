package com.ggkps.superflix.entities;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity(name = "forum")
@Table
public class Forum {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @ManyToOne
    @JoinColumn(name="episode_id", nullable=false)
    private Episode episode;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "forum", cascade = CascadeType.ALL)
    private List<Comment> comments;

    public Forum() {
    }

    public Forum(String title) {
        this.title = title;
    }

    public Forum(String title, List<Comment> comments) {
        this.title = title;
        this.comments = comments;
    }

    public Forum(String title, Episode episode, List<Comment> comments) {
        this.title = title;
        this.episode = episode;
        this.comments = comments;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Episode getEpisode() {
        return episode;
    }

    public void setEpisode(Episode episode) {
        this.episode = episode;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "Forum{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", episode=" + episode +
                ", comments=" + comments +
                '}';
    }
}
