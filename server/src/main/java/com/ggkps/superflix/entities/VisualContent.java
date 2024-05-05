package com.ggkps.superflix.entities;

import jakarta.persistence.*;

import java.util.Date;

@Entity(name = "visual_content")
@Table
public class VisualContent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private String category;
    private String creator;
    private Date release_at;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return this.category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCreator() {
        return this.creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Date getReleaseAt() {
        return this.release_at;
    }

    public void setReleaseAt(Date release_at) {
        this.release_at = release_at;
    }

    public String toString() {
        return "VisualContent{" +
                "id=" + this.id +
                ", title='" + this.title + '\'' +
                ", description='" + this.description + '\'' +
                ", category='" + this.category + '\'' +
                ", creator='" + this.creator + '\'' +
                ", release=" + this.release_at +
                '}';
    }
}
