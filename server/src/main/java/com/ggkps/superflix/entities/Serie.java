package com.ggkps.superflix.entities;

import jakarta.persistence.*;

import java.util.Date;


/*
    CREATE TABLE IF NOT EXISTS serie (
        id bigint PRIMARY KEY NOT NULL AUTO_INCREMENT,
        numberOfSeasons INT,
        visualContentId bigint REFERENCES visual_content(id)
    );

    CREATE TABLE IF NOT EXISTS season (
        id bigint PRIMARY KEY NOT NULL AUTO_INCREMENT,
        numberOfEpisode INT,
        serieId bigint REFERENCES serie(id),
        release_at DATE
    );

    CREATE TABLE IF NOT EXISTS episode (
        id bigint PRIMARY KEY NOT NULL AUTO_INCREMENT,
        number INT,
        seasonId bigint REFERENCES season(id),
        path VARCHAR(100),
        release_at DATE
    );
 */
@Entity(name = "serie")
@Table
public class Serie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Integer numberOfSeasons;

    @OneToOne
    @JoinColumn(name = "visualContentId", referencedColumnName = "id")
    private VisualContent visualContent;

    public Serie() {
    }

    public Serie(Integer numberOfSeasons, VisualContent visualContent) {
        this.numberOfSeasons = numberOfSeasons;
        this.visualContent = visualContent;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumberOfSeasons() {
        return numberOfSeasons;
    }

    public void setNumberOfSeasons(Integer numberOfSeasons) {
        this.numberOfSeasons = numberOfSeasons;
    }

    public VisualContent getVisualContent() {
        return visualContent;
    }

    public void setVisualContent(VisualContent visualContent) {
        this.visualContent = visualContent;
    }

    @Override
    public String toString() {
        return "Serie{" +
                "id=" + id +
                ", numberOfSeasons=" + numberOfSeasons +
                ", visualContent=" + visualContent +
                '}';
    }
}
