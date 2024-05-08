package com.ggkps.superflix.api.admin;

import com.ggkps.superflix.models.MovieContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.ggkps.superflix.entities.Movie;
import com.ggkps.superflix.repositories.MovieRepository;
import com.ggkps.superflix.services.MovieService;

import java.util.Optional;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api/v1/admin")
public class MovieAdmin {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private MovieService movieService;

    public MovieAdmin() {
    }

    @PostMapping("/movie")
    public String createMovie(@RequestBody MovieContent movieContent) {
        Movie newMovie = movieService.createMovie(movieContent);

        System.out.println(newMovie);

        if (newMovie != null) {
            return newMovie.toString();
        }

        return "Movie not created";
    }

    @GetMapping("/movie/{movie_id}")
    public ResponseEntity<Movie> readMovie(@PathVariable("movie_id") Long movie_id) {
        Optional<Movie> movie = movieRepository.findById(movie_id);
        System.out.println(movie);

        return movie.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());

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
