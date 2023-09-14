package com.example.task2.service;

import com.example.task2.model.Genre;
import com.example.task2.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class DefaultGenreService implements GenreService {
    @Autowired
    private GenreRepository genreRepository;

    @Override
    public List<Genre> getAllGenres() {
        return genreRepository.findAll();
    }

    @Override
    public Optional<Genre> getGenreById(Long id) {
        return genreRepository.findById(id);
    }

    @Override
    public Genre createGenre(Genre genre) {
        return genreRepository.save(genre);
    }

    @Override
    public Optional<Genre> updateGenre(Long id, Genre updatedGenre) {
        if (genreRepository.existsById(id)) {
            updatedGenre.setId(id);
            return Optional.of(genreRepository.save(updatedGenre));
        }
        return Optional.empty();
    }

    @Override
    public void deleteGenre(Long id) {
        if (genreRepository.existsById(id)) {
            genreRepository.deleteById(id);
        }
    }

    @Override
    public List<Genre> searchGenres(String name) {
        if (name != null) {
            return genreRepository.findByNameContainingIgnoreCase(name);
        } else {
            return Collections.emptyList();
        }
    }
}