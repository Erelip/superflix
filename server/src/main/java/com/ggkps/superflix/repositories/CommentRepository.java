package com.ggkps.superflix.repositories;

import com.ggkps.superflix.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> { }
