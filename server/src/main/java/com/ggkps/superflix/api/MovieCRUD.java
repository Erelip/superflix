package com.ggkps.superflix.api;

import com.ggkps.superflix.models.MovieContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ggkps.superflix.entities.Movie;
import com.ggkps.superflix.repositories.MovieRepository;
import com.ggkps.superflix.services.MovieService;

import java.util.Optional;

@RestController
public class MovieCRUD {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private MovieService movieService;

    public MovieCRUD() {
    }

    @PostMapping("/movie/")
    public String createMovie(@RequestBody MovieContent movieContent) {
        Movie newMovie = movieService.createMovie(movieContent);

        System.out.println(newMovie);

        if (newMovie != null) {
            return newMovie.toString();
        }

        return "Movie not created";
    }

    @GetMapping("/movie/{movie_id}")
    public String readMovie(@PathVariable("movie_id") Long movie_id) {
        Optional<Movie> movie = movieRepository.findById(movie_id);

        if (movie.isPresent()) {
            return movie.get().toString();
        }

        return "Movie not found";
    }

    @PatchMapping("/movie/{movie_id}")
    public String updateMovie(@PathVariable("movie_id") long movie_id, @RequestBody MovieContent movieContent) {
        Optional<Movie> movie = movieRepository.findById(movie_id);

        if (movie.isEmpty()) {
            return "Movie not found";
        }

        Movie updatedMovie = movieService.updateMovie(movie_id, movieContent);

        return updatedMovie.toString();
    }

    @DeleteMapping("/movie/{movie_id}")
    public String deleteMovie(@PathVariable("movie_id") Long movie_id) {
        boolean exists = movieService.deleteMovie(movie_id);

        return exists ? "Movie deleted" : "Movie not found";
    }
}
