package com.ggkps.superflix.api.user;

import com.ggkps.superflix.entities.Movie;
import com.ggkps.superflix.models.MovieContent;
import com.ggkps.superflix.repositories.MovieRepository;
import com.ggkps.superflix.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/user")
public class MovieUser {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private MovieService movieService;

    public MovieUser() {
    }

    @GetMapping("/movie/{movie_id}")
    public String readMovie(@PathVariable("movie_id") Long movie_id) {
        Optional<Movie> movie = movieRepository.findById(movie_id);

        if (movie.isPresent()) {
            return movie.get().toString();
        }

        return "Movie not found";
    }
}
