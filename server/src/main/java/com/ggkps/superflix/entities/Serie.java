package com.ggkps.superflix.entities;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "serie", cascade = CascadeType.ALL)
    private List<Season> seasons;

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

    public List<Season> getSeasons() {
        return seasons;
    }

    public void setSeasons(List<Season> seasons) {
        this.seasons = seasons;
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
