package com.ggkps.superflix.repositories;

import com.ggkps.superflix.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> { }
