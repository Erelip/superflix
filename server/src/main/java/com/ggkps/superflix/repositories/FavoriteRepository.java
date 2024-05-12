package com.ggkps.superflix.repositories;

import com.ggkps.superflix.entities.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> { }
