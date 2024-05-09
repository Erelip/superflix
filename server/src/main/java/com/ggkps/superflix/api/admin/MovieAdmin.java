package com.ggkps.superflix.api.admin;

import com.ggkps.superflix.entities.User;
import com.ggkps.superflix.entities.VisualContent;
import com.ggkps.superflix.models.MovieContent;
import com.ggkps.superflix.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.ggkps.superflix.entities.Movie;
import com.ggkps.superflix.repositories.MovieRepository;
import com.ggkps.superflix.services.MovieService;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api/v1/admin")
public class MovieAdmin {

    @Autowired
    private UserService userService;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private MovieService movieService;

    public MovieAdmin() {
    }

    @PostMapping("/movie")
    public ResponseEntity<Movie> createMovie(@RequestHeader(value="Authorization") String authorization, @RequestBody MovieContent movieContent) {
        authorization = authorization.replace("Bearer ", "");
        Optional<User> user = userService.getUserFromToken(authorization);
        if (user.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Movie newMovie = movieService.createMovie(movieContent, user.get());

        if (newMovie != null) {
            return ResponseEntity.ok().body(newMovie);
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/movie")
    public ResponseEntity<List<Movie>> readMovies(@RequestHeader(value="Authorization") String authorization) {
        List<Movie> movies = movieRepository.findAll();
        return ResponseEntity.ok().body(movies);
    }

    @GetMapping("/movie/{movie_id}")
    public ResponseEntity<Movie> readMovie(@RequestHeader(value="Authorization") String authorization, @PathVariable("movie_id") Long movie_id) {
        Optional<Movie> movie = movieRepository.findById(movie_id);
        return movie.map(value -> ResponseEntity.ok().body(value)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PatchMapping("/movie/{movie_id}")
    public ResponseEntity<Movie> updateMovie(@RequestHeader(value="Authorization") String authorization, @PathVariable("movie_id") long movie_id, @RequestBody MovieContent movieContent) {
        Movie updatedMovie = movieService.updateMovie(movie_id, movieContent);

        return ResponseEntity.ok().body(updatedMovie);
    }

    @DeleteMapping("/movie/{movie_id}")
    public ResponseEntity<Movie> deleteMovie(@RequestHeader(value="Authorization") String authorization, @PathVariable("movie_id") Long movie_id) {
        boolean exists = movieService.deleteMovie(movie_id);

        return exists ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}
