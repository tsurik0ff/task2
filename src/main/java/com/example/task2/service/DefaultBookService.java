package com.example.task2.service;

import com.example.task2.model.Book;
import com.example.task2.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class DefaultBookService implements BookService {
    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Optional<Book> getBookById(Long id) {
        return bookRepository.findById(id);
    }

    @Override
    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Optional<Book> updateBook(Long id, Book updatedBook) {
        if (bookRepository.existsById(id)) {
            updatedBook.setId(id);
            return Optional.of(bookRepository.save(updatedBook));
        }
        return Optional.empty();
    }

    @Override
    public void deleteBook(Long id) {
        if (bookRepository.existsById(id)) {
            bookRepository.deleteById(id);
        }
    }

    @Override
    public List<Book> searchBookByTitle(String title) {
        if (title != null) {
            return bookRepository.findByTitleContainingIgnoreCase(title);
        } else {
            return Collections.emptyList();
        }
    }

    @Override
    public List<Book> searchBookByAuthor(String authorName) {
        if (authorName != null) {
            return bookRepository.findByAuthorNameContainingIgnoreCase(authorName);
        } else {
            return Collections.emptyList();
        }
    }

    @Override
    public List<Book> searchBookByGenre(String genreName) {
        if (genreName != null) {
            return bookRepository.findByGenreNameContainingIgnoreCase(genreName);
        } else {
            return Collections.emptyList();
        }
    }
}

