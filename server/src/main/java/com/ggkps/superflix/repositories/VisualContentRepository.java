package com.ggkps.superflix.repositories;

import com.ggkps.superflix.entities.VisualContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface VisualContentRepository extends JpaRepository<VisualContent, Long> {

    @Query(value = "SELECT * FROM visual_content WHERE title=? LIMIT 1",nativeQuery = true)
    public VisualContent findOneByTitle(String title);

    @Query(value = "SELECT * FROM visual_content WHERE id=? LIMIT 1",nativeQuery = true)
    public VisualContent findOneById(Integer id);

    @Query(value = "SELECT * FROM visual_content WHERE title=?",nativeQuery = true)
    public List<VisualContent> findByTitle(String title);

    @Query(value = "SELECT * FROM visual_content WHERE id=?",nativeQuery = true)
    public List<VisualContent> findById(Integer id);
}
