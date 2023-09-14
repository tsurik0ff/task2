package com.example.task2.controller;


import com.example.task2.model.Author;
import com.example.task2.model.Book;
import com.example.task2.model.Genre;
import com.example.task2.service.BookService;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

public class BookControllerTest {

    private List<Book> books;
    private Book book;

    @InjectMocks
    private BookController bookController;

    @Mock
    private BookService bookService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        books = Arrays.asList(
                createBook(1L, "Book1", createAuthor(1L, "Author1"), createGenre(1L, "Genre1"), 10.0, 5),
                createBook(2L, "Book2", createAuthor(1L, "Author1"), createGenre(2L, "Genre2"), 15.0, 3)
        );
        book = createBook(1L, "Book1", createAuthor(1L, "Author1"), createGenre(1L, "Genre1"), 10.0, 5);
    }

    @Test
    public void testGetAllBooks() {
        // Arrange
        when(bookService.getAllBooks()).thenReturn(books);

        // Act
        List<Book> result = bookController.getAllBooks();

        // Assert
        assertEquals(2, result.size());
        assertEquals("Book1", result.get(0).getTitle());
        assertEquals("Book2", result.get(1).getTitle());
    }

    @Test
    public void testGetBookById() {
        // Arrange
        when(bookService.getBookById(1L)).thenReturn(Optional.of(book));

        // Act
        ResponseEntity<Book> responseEntity = bookController.getBookById(1L);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Book1", responseEntity.getBody().getTitle());
    }

    @Test
    public void testCreateBook() {
        // Arrange
        when(bookService.createBook(any(Book.class))).thenReturn(book);

        // Act
        ResponseEntity<Book> responseEntity = bookController.createBook(book);

        // Assert
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals("Book1", responseEntity.getBody().getTitle());
    }

    @Test
    public void testUpdateBook() {
        // Arrange
        when(bookService.updateBook(1L, book)).thenReturn(Optional.of(book));

        // Act
        ResponseEntity<Book> responseEntity = bookController.updateBook(1L, book);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Book1", responseEntity.getBody().getTitle());
    }

    @Test
    public void testDeleteBook() {
        // Arrange
        doNothing().when(bookService).deleteBook(1L);

        // Act
        ResponseEntity<Void> responseEntity = bookController.deleteBook(1L);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
    }

    @Test
    public void testSearchBookByTitle() {
        // Arrange
        when(bookService.searchBookByTitle("Book")).thenReturn(books);

        // Act
        List<Book> result = bookController.searchBookByTitle("Book");

        // Assert
        assertEquals(2, result.size());
        assertEquals("Book1", result.get(0).getTitle());
        assertEquals("Book2", result.get(1).getTitle());
    }

    @Test
    public void testSearchBooksByAuthor() {
        // Arrange
        when(bookService.searchBookByAuthor("Author1")).thenReturn(books);

        // Act
        List<Book> result = bookController.searchBooksByAuthor("Author1");

        // Assert
        assertEquals(2, result.size());
        assertEquals("Author1", result.get(0).getAuthor().getName());
        assertEquals("Author1", result.get(1).getAuthor().getName());
    }

    @Test
    public void testSearchBooksByGenre() {
        // Arrange
        when(bookService.searchBookByGenre("Genre1")).thenReturn(books);

        // Act
        List<Book> result = bookController.searchBooksByGenre("Genre1");

        // Assert
        assertEquals(2, result.size());
        assertEquals("Genre1", result.get(0).getGenre().getName());
        assertEquals("Genre2", result.get(1).getGenre().getName());
    }

    private Book createBook(Long id, String title, Author author, Genre genre, double price, int quantity) {
        Book book = new Book();
        book.setId(id);
        book.setTitle(title);
        book.setAuthor(author);
        book.setGenre(genre);
        book.setPrice(price);
        book.setQuantity(quantity);
        return book;
    }

    private Genre createGenre(Long id, String name) {
        Genre Genre = new Genre();
        Genre.setId(id);
        Genre.setName(name);
        return Genre;
    }

    private Author createAuthor(Long id, String name) {
        Author author = new Author();
        author.setId(id);
        author.setName(name);
        return author;
    }
}

