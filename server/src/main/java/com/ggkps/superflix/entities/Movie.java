package com.ggkps.superflix.entities;

import jakarta.persistence.*;

@Entity(name = "movie")
@Table
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer duration;

    private String path;

    @OneToOne
    @JoinColumn(name = "visualContentId", referencedColumnName = "id")
    private VisualContent visualContent;

    public Movie() {
    }

    public Movie(Integer duration, String path, VisualContent visualContent) {
        this.duration = duration;
        this.path = path;
        this.visualContent = visualContent;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public VisualContent getVisualContent() {
        return visualContent;
    }

    public void setVisualContent(VisualContent visualContent) {
        this.visualContent = visualContent;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", duration=" + duration +
                ", path=" + path +
                ", visualContent=" + visualContent +
                '}';
    }
}
