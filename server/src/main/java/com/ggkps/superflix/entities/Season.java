package com.ggkps.superflix.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity(name = "season")
@Table
public class Season {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Integer numberOfEpisodes;

    @Column
    private Integer number;

    @ManyToOne
    @JoinColumn(name="serie_id", nullable=false)
    private Serie serie;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "season", cascade = CascadeType.ALL)
    private List<Episode> episodes;

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

    public Integer getNumber() {
        return number;
    }

    public Season setNumber(Integer number) {
        this.number = number;

        return this;
    }

    public Serie getSerie() {
        return serie;
    }

    public Season setSerie(Serie serie) {
        this.serie = serie;

        return this;
    }

    public List<Episode> getEpisodes() {
        return episodes;
    }

    public Season setEpisodes(List<Episode> episodes) {
        this.episodes = episodes;

        return this;
    }

    @Override
    public String toString() {
        return "Season{" +
                "id=" + id +
                ", numberOfEpisodes=" + numberOfEpisodes +
                ", number=" + number +
                ", serie=" + serie +
                '}';
    }
}
