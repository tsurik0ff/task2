package com.example.task2.service;

import com.example.task2.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> getAllBooks();

    Optional<Book> getBookById(Long id);

    Book createBook(Book book);

    Optional<Book> updateBook(Long id, Book updatedBook);

    void deleteBook(Long id);

    List<Book> searchBookByTitle(String title);

    List<Book> searchBookByAuthor(String authorName);

    List<Book> searchBookByGenre(String genreName);
}
