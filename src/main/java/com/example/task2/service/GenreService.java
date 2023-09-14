package com.example.task2.service;

import com.example.task2.model.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreService {
    List<Genre> getAllGenres();

    Optional<Genre> getGenreById(Long id);

    Genre createGenre(Genre genre);

    Optional<Genre> updateGenre(Long id, Genre updatedGenre);

    void deleteGenre(Long id);

    List<Genre> searchGenres(String name);
}
