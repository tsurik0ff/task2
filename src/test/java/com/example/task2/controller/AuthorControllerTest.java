package com.example.task2.controller;

import com.example.task2.model.Author;
import com.example.task2.service.AuthorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

public class AuthorControllerTest {

    @InjectMocks
    private AuthorController authorController;

    @Mock
    private AuthorService authorService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllAuthors() {
        // Arrange
        List<Author> authors = Arrays.asList(createAuthor(1L, "Author1"), createAuthor(2L, "Author2"));
        when(authorService.getAllAuthors()).thenReturn(authors);

        // Act
        List<Author> result = authorController.getAllAuthors();

        // Assert
        assertEquals(2, result.size());
        assertEquals("Author1", result.get(0).getName());
        assertEquals("Author2", result.get(1).getName());
    }

    @Test
    public void testGetAuthorById() {
        // Arrange
        Author author = createAuthor(1L, "Author1");
        when(authorService.getAuthorById(1L)).thenReturn(Optional.of(author));

        // Act
        ResponseEntity<Author> responseEntity = authorController.getAuthorById(1L);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Author1", responseEntity.getBody().getName());
    }
    @Test
    public void testCreateAuthor() {
        // Arrange
        Author author = createAuthor(1L, "Author1");
        when(authorService.createAuthor(any(Author.class))).thenReturn(author);

        // Act
        ResponseEntity<Author> responseEntity = authorController.createAuthor(author);

        // Assert
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals("Author1", responseEntity.getBody().getName());
    }

    @Test
    public void testUpdateAuthor() {
        // Arrange
        Author updatedAuthor = createAuthor(1L, "UpdatedAuthor1");
        when(authorService.updateAuthor(1L, updatedAuthor)).thenReturn(Optional.of(updatedAuthor));

        // Act
        ResponseEntity<Author> responseEntity = authorController.updateAuthor(1L, updatedAuthor);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("UpdatedAuthor1", responseEntity.getBody().getName());
    }

    @Test
    public void testDeleteAuthor() {
        // Arrange
        doNothing().when(authorService).deleteAuthor(1L);

        // Act
        ResponseEntity<Void> responseEntity = authorController.deleteAuthor(1L);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
    }

    @Test
    public void testSearchAuthors() {
        // Arrange
        List<Author> authors = Arrays.asList(createAuthor(1L, "Author1"), createAuthor(2L, "Author2"));
        when(authorService.searchAuthors("Author")).thenReturn(authors);

        // Act
        List<Author> result = authorController.searchAuthors("Author");

        // Assert
        assertEquals(2, result.size());
        assertEquals("Author1", result.get(0).getName());
        assertEquals("Author2", result.get(1).getName());
    }

    private Author createAuthor(Long id, String name) {
        Author author = new Author();
        author.setId(id);
        author.setName(name);
        return author;
    }
}
