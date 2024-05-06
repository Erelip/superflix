package com.ggkps.superflix.entities;

import jakarta.persistence.*;

import java.util.Date;


/*
CREATE TABLE IF NOT EXISTS episode (
    id bigint PRIMARY KEY NOT NULL AUTO_INCREMENT,
    number INT,
    season_id bigint,
    path VARCHAR(100),
    release_at DATE,
    FOREIGN KEY (season_id) REFERENCES season(id)
);
 */
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

    @ManyToOne
    @JoinColumn(name="season_id", nullable=false)
    private Season season;


    public Episode() {
    }

    public Episode(Integer number, Season season) {
        this.number = number;
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

    public Season getSeason() {
        return season;
    }

    public Episode getSeason(Season season) {
        this.season = season;

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
