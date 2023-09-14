package com.example.task2.service;

import com.example.task2.model.Author;
import com.example.task2.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class DefaultAuthorService implements AuthorService {
    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    @Override
    public Optional<Author> getAuthorById(Long id) {
        return authorRepository.findById(id);
    }

    @Override
    public Author createAuthor(Author author) {
        return authorRepository.save(author);
    }

    @Override
    public Optional<Author> updateAuthor(Long id, Author updatedAuthor) {
        if (authorRepository.existsById(id)) {
            updatedAuthor.setId(id);
            return Optional.of(authorRepository.save(updatedAuthor));
        }
        return Optional.empty();
    }

    @Override
    public void deleteAuthor(Long id) {
        if (authorRepository.existsById(id)) {
            authorRepository.deleteById(id);
        }
    }

    @Override
    public List<Author> searchAuthors(String name) {
        if (name != null) {
            return authorRepository.findByNameContainingIgnoreCase(name);
        } else {
            return Collections.emptyList();
        }
    }
}

