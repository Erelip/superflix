package com.ggkps.superflix.repositories;

import com.ggkps.superflix.entities.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    @Query(value = "SELECT movie.* " +
            "FROM movie " +
            "LEFT JOIN visual_content ON movie.visual_content_id = visual_content.id " +
            "WHERE movie.id = :id", nativeQuery = true)
    List<Movie> findMoviesByUserId(@Param("id") Long id);

}
