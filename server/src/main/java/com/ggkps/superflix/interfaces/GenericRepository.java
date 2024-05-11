package com.ggkps.superflix.interfaces;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface GenericRepository<T, ID extends Serializable> extends JpaRepository<T, ID> {

    @SuppressWarnings("NullableProblems")
    List<T> findAll();

    @SuppressWarnings("NullableProblems")
    Optional<T> findById(ID id);

    @SuppressWarnings("NullableProblems")
    void deleteById(ID id);
}