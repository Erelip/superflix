package com.ggkps.superflix.models;

import jakarta.persistence.Column;

import java.util.Date;

public class EpisodeContent {

    private Long serie_id;
    private Long season_number;
    private Integer number;
    private String path;
    private Date release_at;
    private Integer duration;

    public EpisodeContent() {
    }

    public EpisodeContent(Long serie_id, Long season_number, Integer number, String path, Date release_at, Integer duration) {
        this.serie_id = serie_id;
        this.season_number = season_number;
        this.number = number;
        this.path = path;
        this.release_at = release_at;
        this.duration = duration;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Date getReleaseAt() {
        return release_at;
    }

    public void setReleaseAt(Date release_at) {
        this.release_at = release_at;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Long getSerieId() {
        return serie_id;
    }

    public void setSerieId(Long serie_id) {
        this.serie_id = serie_id;
    }

    public Long getSeasonNumber() {
        return season_number;
    }

    public void setSeasonNumber(Long season_number) {
        this.season_number = season_number;
    }
}

