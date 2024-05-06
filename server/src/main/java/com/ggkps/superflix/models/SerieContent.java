package com.ggkps.superflix.models;

import java.util.Date;

public class SerieContent {
    private String path;
    private String title;
    private String description;
    private String category;
    private String creator;
    private Date release_at;

    private Integer numberOfSeasons;
    private Integer numberOfEpisodes;
    private Integer duration;

    public SerieContent() {
    }

    public SerieContent(Integer duration, String path, String title, String description, String category, String creator, Date release_at) {
        this.duration = duration;
        this.path = path;
        this.title = title;
        this.description = description;
        this.category = category;
        this.creator = creator;
        this.release_at = release_at;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
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

    public Integer getNumberOfSeasons() {
        return numberOfSeasons;
    }

    public void setNumberOfSeasons(Integer numberOfSeasons) {
        this.numberOfSeasons = numberOfSeasons;
    }

    public Integer getNumberOfEpisodes() {
        return numberOfEpisodes;
    }

    public void setNumberOfEpisodes(Integer numberOfEpisodes) {
        this.numberOfEpisodes = numberOfEpisodes;
    }
}

