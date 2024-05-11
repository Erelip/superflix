package com.ggkps.superflix.interfaces;

public interface Subscriber extends BasicUser {

    void watchMovie(String movie);

    void watchSeries(String series);

    void rateMovie(String movie, int rating);

    void rateSeries(String series, int rating);

    void commentMovie(String movie, String comment);

    void commentSeries(String series, String comment);
}
