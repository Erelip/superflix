package com.ggkps.superflix.services;

import com.ggkps.superflix.entities.User;
import com.ggkps.superflix.models.MovieContent;
import com.ggkps.superflix.repositories.VisualContentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ggkps.superflix.entities.Movie;
import com.ggkps.superflix.repositories.MovieRepository;
import com.ggkps.superflix.entities.VisualContent;

import java.util.Date;
import java.util.Optional;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private VisualContentRepository visualContentRepository;

    public MovieService() {
    }

    public Movie createMovie(Integer duration, String path, VisualContent visualContent) {
        return new Movie(duration, path, visualContent);
    }

    public Movie createMovie(MovieContent movieContent, User user) {
        VisualContent visualContent = new VisualContent()
                .setTitle(movieContent.getTitle())
                .setReleaseAt(movieContent.getReleaseAt() != null ? movieContent.getReleaseAt() : new Date())
                .setCategory(movieContent.getCategory())
                .setCreator(movieContent.getCreator())
                .setDescription(movieContent.getDescription())
                .setUser(user);

        Movie newMovie = new Movie(movieContent.getDuration(), movieContent.getPath(), visualContent);
        visualContentRepository.save(visualContent);
        return movieRepository.save(newMovie);
    }

    public Movie updateMovie(long id, MovieContent movieContent) {
        Optional<Movie> movie = movieRepository.findById(id);

        if (movie.isEmpty()) {
            return null;
        }

        VisualContent visualContent = movie.get().getVisualContent();
        if (movieContent.getTitle() != null) visualContent.setTitle(movieContent.getTitle());
        if (movieContent.getReleaseAt() != null) visualContent.setReleaseAt(movieContent.getReleaseAt());
        if (movieContent.getCategory() != null) visualContent.setCategory(movieContent.getCategory());
        if (movieContent.getCreator() != null) visualContent.setCreator(movieContent.getCreator());
        if (movieContent.getDescription() != null) visualContent.setDescription(movieContent.getDescription());
        if (movieContent.getDuration() != null) movie.get().setDuration(movieContent.getDuration());
        if (movieContent.getPath() != null) movie.get().setPath(movieContent.getPath());

        visualContentRepository.save(visualContent);
        return movieRepository.save(movie.get());
    }

    public boolean deleteMovie(Long id) {
        Optional<Movie> movie = movieRepository.findById(id);

        if (movie.isPresent()) {
            visualContentRepository.deleteById(movie.get().getVisualContent().getId());
            movieRepository.deleteById(id);
            movieRepository.flush();
            return true;
        }
        return false;
    }
}