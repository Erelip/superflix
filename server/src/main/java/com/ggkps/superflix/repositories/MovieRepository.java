package com.ggkps.superflix.repositories;

import com.ggkps.superflix.entities.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> { }
