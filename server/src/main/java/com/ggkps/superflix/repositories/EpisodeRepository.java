package com.ggkps.superflix.repositories;

import com.ggkps.superflix.entities.Episode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EpisodeRepository extends JpaRepository<Episode, Long> { }
