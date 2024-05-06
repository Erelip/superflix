package com.ggkps.superflix.repositories;

import com.ggkps.superflix.entities.Season;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface SeasonRepository extends JpaRepository<Season, Long> {
    Optional<Season> findBySerieIdAndNumber(Long serieId, Long seasonNumber);
}
