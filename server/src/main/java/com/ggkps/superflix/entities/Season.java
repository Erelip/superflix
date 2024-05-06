package com.ggkps.superflix.entities;

import jakarta.persistence.*;

@Entity(name = "season")
@Table
public class Season {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Integer numberOfEpisodes;

    @ManyToOne
    @JoinColumn(name="serie_id", nullable=false)
    private Serie serie;

    public Season() {
    }

    public Season(Integer numberOfEpisodes, Serie serie) {
        this.numberOfEpisodes = numberOfEpisodes;
        this.serie = serie;
    }

    public Long getId() {
        return id;
    }

    public Season setId(Long id) {
        this.id = id;

        return this;
    }

    public Integer getNumberOfEpisodes() {
        return numberOfEpisodes;
    }

    public Season setNumberOfEpisodes(Integer numberOfEpisodes) {
        this.numberOfEpisodes = numberOfEpisodes;

        return this;
    }

    public Serie getSerie() {
        return serie;
    }

    public Season setSerie(Serie serie) {
        this.serie = serie;

        return this;
    }

    @Override
    public String toString() {
        return "Season{" +
                "id=" + id +
                ", numberOfEpisodes=" + numberOfEpisodes +
                ", serie=" + serie +
                '}';
    }
}
