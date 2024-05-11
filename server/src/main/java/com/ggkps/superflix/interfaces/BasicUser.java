package com.ggkps.superflix.interfaces;

import com.ggkps.superflix.entities.Movie;
import com.ggkps.superflix.entities.Serie;

import java.util.List;

public interface BasicUser {

    boolean subscribe();

    boolean unsubscribe();

    List<Movie> getMovieList();

    List<Serie> getSerieList();

    List<Movie> getMovieListByGenre(String genre);

    List<Serie> getSerieListByGenre(String genre);

    List<Movie> getMovieListByDirector(String director);

    List<Serie> getSerieListByDirector(String director);

    void watchTrailer(String movie);

    Movie addFavoriteMovie(String movie);

    Serie addFavoriteSerie(String series);

    List<Movie> getFavoriteMovies();

    List<Serie> getFavoriteSeries();

    boolean removeFavoriteMovie(String movie);

    boolean removeFavoriteSerie(String series);
}
