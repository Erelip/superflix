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

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumberOfEpisodes() {
        return numberOfEpisodes;
    }

    public void setNumberOfEpisodes(Integer numberOfEpisodes) {
        this.numberOfEpisodes = numberOfEpisodes;
    }

    public Serie getSerie() {
        return serie;
    }

    public void setSerie(Serie serie) {
        this.serie = serie;
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
