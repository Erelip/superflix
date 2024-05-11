package com.ggkps.superflix.interfaces;

import com.ggkps.superflix.entities.Episode;
import com.ggkps.superflix.entities.Movie;
import com.ggkps.superflix.entities.Season;
import com.ggkps.superflix.entities.Serie;
import com.ggkps.superflix.interfaces.BasicUser;

import java.util.Optional;

public interface Professional extends BasicUser {

    void launchLiveStream();

    Movie addMovie();

    Serie addSerie();

    Season addSeason();

    Episode addEpisode();

    boolean removeMovie();

    boolean removeSerie();

    boolean removeSeason();

    boolean removeEpisode();

    Movie updateMovie();

    Serie updateSerie();

    Season updateSeason();

    Episode updateEpisode();

    void getStatistics();

}
