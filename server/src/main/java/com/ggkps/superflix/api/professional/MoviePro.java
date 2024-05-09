package com.ggkps.superflix.api.professional;

import com.ggkps.superflix.entities.Movie;
import com.ggkps.superflix.entities.User;
import com.ggkps.superflix.entities.VisualContent;
import com.ggkps.superflix.models.MovieContent;
import com.ggkps.superflix.repositories.MovieRepository;
import com.ggkps.superflix.services.MovieService;
import com.ggkps.superflix.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/professional")
public class MoviePro {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private MovieService movieService;

    @Autowired
    private UserService userService;

    public MoviePro() {
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
    public ResponseEntity<Movie> readMovies(@RequestHeader(value="Authorization") String authorization) {
        authorization = authorization.replace("Bearer ", "");
        Optional<User> user = userService.getUserFromToken(authorization);
        if (user.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        List<Movie> movies = movieRepository.findMoviesByUserId(user.get().getId());
        if (movies.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(movies.get(0));
    }

    @GetMapping("/movie/{movie_id}")
    public ResponseEntity<Movie> readMovie(@RequestHeader(value="Authorization") String authorization, @PathVariable("movie_id") Long movie_id) {
        authorization = authorization.replace("Bearer ", "");
        Optional<User> user = userService.getUserFromToken(authorization);
        if (user.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Optional<Movie> movie = movieRepository.findById(movie_id);
        if (movie.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        VisualContent visualContent = movie.get().getVisualContent();
        if (visualContent == null) {
            return ResponseEntity.notFound().build();
        }

        if (!Objects.equals(visualContent.getUser().getId(), user.get().getId())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        return movie.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PatchMapping("/movie/{movie_id}")
    public ResponseEntity<Movie> updateMovie(@RequestHeader(value="Authorization") String authorization, @PathVariable("movie_id") long movie_id, @RequestBody MovieContent movieContent) {
        authorization = authorization.replace("Bearer ", "");
        Optional<User> user = userService.getUserFromToken(authorization);
        if (user.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Optional<Movie> movie = movieRepository.findById(movie_id);
        if (movie.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        VisualContent visualContent = movie.get().getVisualContent();
        if (visualContent == null) {
            return ResponseEntity.notFound().build();
        }

        if (!Objects.equals(visualContent.getUser().getId(), user.get().getId())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        Movie updatedMovie = movieService.updateMovie(movie_id, movieContent);

        return ResponseEntity.ok().body(updatedMovie);
    }

    @DeleteMapping("/movie/{movie_id}")
    public ResponseEntity<Movie> deleteMovie(@RequestHeader(value="Authorization") String authorization, @PathVariable("movie_id") Long movie_id) {
        authorization = authorization.replace("Bearer ", "");
        Optional<User> user = userService.getUserFromToken(authorization);
        if (user.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Optional<Movie> movie = movieRepository.findById(movie_id);
        if (movie.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        VisualContent visualContent = movie.get().getVisualContent();
        if (visualContent == null) {
            return ResponseEntity.notFound().build();
        }

        if (!Objects.equals(visualContent.getUser().getId(), user.get().getId())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        boolean exists = movieService.deleteMovie(movie_id);

        return exists ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}
