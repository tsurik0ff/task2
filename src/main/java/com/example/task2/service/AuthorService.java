package com.example.task2.service;

import com.example.task2.model.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    List<Author> getAllAuthors();

    Optional<Author> getAuthorById(Long id);

    Author createAuthor(Author author);

    Optional<Author> updateAuthor(Long id, Author updatedAuthor);

    void deleteAuthor(Long id);

    List<Author> searchAuthors(String name);
}
