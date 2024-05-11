package com.ggkps.superflix.models;

import java.util.Date;

public class SeasonContent {
    private String serie_id;
    private String number;

    public SeasonContent() {
    }

    public SeasonContent(String serie_id, String number) {
        this.serie_id = serie_id;
        this.number = number;
    }

    public String getSerie_id() {
        return serie_id;
    }

    public void setSerie_id(String serie_id) {
        this.serie_id = serie_id;
    }

    public String getNumber() {
        return number;
    }
}
