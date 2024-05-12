package com.ggkps.superflix.interfaces;

public interface EntityServiceInterface<T, C> {
    public T create(C content);
    public T update(C content);
    public boolean delete(Long id);
}
