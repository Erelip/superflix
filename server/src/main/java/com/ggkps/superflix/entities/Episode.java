package com.ggkps.superflix.entities;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity(name = "episode")
@Table
public class Episode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Integer number;

    @Column
    private String path;

    @Column
    private Date release_at;

    @Column
    private Integer duration;

    @ManyToOne
    @JoinColumn(name="season_id", nullable=false)
    private Season season;

    @OneToMany
    @JoinColumn(name="forum_id", nullable=false)
    private List<Forum> forums;

    public Episode() {
    }

    public Episode(Integer number, String path, Integer duration, Season season) {
        this.duration = duration;
        this.number = number;
        this.path = path;
        this.season = season;
    }

    public Long getId() {
        return id;
    }

    public Episode setId(Long id) {
        this.id = id;

        return this;
    }

    public Integer getNumber() {
        return number;
    }

    public Episode setNumber(Integer number) {
        this.number = number;

        return this;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Date getRelease_at() {
        return release_at;
    }

    public Episode setRelease_at(Date release_at) {
        this.release_at = release_at;

        return this;
    }

    public Integer getDuration() {
        return duration;
    }

    public Episode setDuration(Integer duration) {
        this.duration = duration;

        return this;
    }

    public Season getSeason() {
        return season;
    }

    public Episode getSeason(Season season) {
        this.season = season;

        return this;
    }

    public List<Forum> getForums() {
        return forums;
    }

    public Episode setForums(List<Forum> forums) {
        this.forums = forums;

        return this;
    }

    @Override
    public String toString() {
        return "Serie{" +
                "id=" + id +
                ", number=" + number +
                ", path=" + path +
                ", release_at=" + release_at +
                '}';
    }
}
