package com.ggkps.superflix.controllers;

import com.ggkps.superflix.SuperflixApplication;
import com.ggkps.superflix.entities.Movie;
import com.ggkps.superflix.entities.Serie;
import com.ggkps.superflix.entities.VisualContent;
import com.ggkps.superflix.repositories.MovieRepository;
import com.ggkps.superflix.repositories.SerieRepository;
import com.ggkps.superflix.repositories.VisualContentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class MovieController {

    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private VisualContentRepository visualContentRepository;
    private final Logger logger = LoggerFactory.getLogger(SuperflixApplication.class);

    @GetMapping("/movie/mock/add")
    public String index() {

        List<VisualContent> allVisualContent = this.visualContentRepository.findByTitle("Zen Emission");
        VisualContent visualContent = this.visualContentRepository.findOneByTitle("Zen Emission");
        logger.info("Number of visual contents: " + allVisualContent.size());
        logger.info("Visual content: " + visualContent);

        List<Movie> allMovies = this.movieRepository.findAll();
        logger.info("Number of series: " + allMovies.size());

        Movie newMovie = new Movie();
        newMovie.setDuration(120);
        newMovie.setVisualContent(visualContent);
        newMovie.setPath("path/to/movie");

        logger.info("Saving new visual content...");
        this.movieRepository.save(newMovie);

        allMovies = this.movieRepository.findAll();
        logger.info("Number of visual content: " + allMovies.size());
        return newMovie.toString();
    }
}