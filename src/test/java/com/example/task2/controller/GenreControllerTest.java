package com.example.task2.controller;

import com.example.task2.model.Genre;
import com.example.task2.model.Genre;
import com.example.task2.service.GenreService;
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

public class GenreControllerTest {

    @InjectMocks
    private GenreController GenreController;

    @Mock
    private GenreService GenreService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllGenres() {
        // Arrange
        List<Genre> Genres = Arrays.asList(createGenre(1L, "Genre1"), createGenre(2L, "Genre2"));
        when(GenreService.getAllGenres()).thenReturn(Genres);

        // Act
        List<Genre> result = GenreController.getAllGenres();

        // Assert
        assertEquals(2, result.size());
        assertEquals("Genre1", result.get(0).getName());
        assertEquals("Genre2", result.get(1).getName());
    }

    @Test
    public void testGetGenreById() {
        // Arrange
        Genre Genre = createGenre(1L, "Genre1");
        when(GenreService.getGenreById(1L)).thenReturn(Optional.of(Genre));

        // Act
        ResponseEntity<Genre> responseEntity = GenreController.getGenreById(1L);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Genre1", responseEntity.getBody().getName());
    }
    @Test
    public void testCreateGenre() {
        // Arrange
        Genre Genre = createGenre(1L, "Genre1");
        when(GenreService.createGenre(any(Genre.class))).thenReturn(Genre);

        // Act
        ResponseEntity<Genre> responseEntity = GenreController.createGenre(Genre);

        // Assert
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals("Genre1", responseEntity.getBody().getName());
    }

    @Test
    public void testUpdateGenre() {
        // Arrange
        Genre updatedGenre = createGenre(1L, "UpdatedGenre1");
        when(GenreService.updateGenre(1L, updatedGenre)).thenReturn(Optional.of(updatedGenre));

        // Act
        ResponseEntity<Genre> responseEntity = GenreController.updateGenre(1L, updatedGenre);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("UpdatedGenre1", responseEntity.getBody().getName());
    }

    @Test
    public void testDeleteGenre() {
        // Arrange
        doNothing().when(GenreService).deleteGenre(1L);

        // Act
        ResponseEntity<Void> responseEntity = GenreController.deleteGenre(1L);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
    }

    @Test
    public void testSearchGenres() {
        // Arrange
        List<Genre> genres = Arrays.asList(createGenre(1L, "Genre1"), createGenre(2L, "Genre2"));
        when(GenreService.searchGenres("Genre")).thenReturn(genres);

        // Act
        List<Genre> result = GenreController.searchGenres("Genre");

        // Assert
        assertEquals(2, result.size());
        assertEquals("Genre1", result.get(0).getName());
        assertEquals("Genre2", result.get(1).getName());
    }

    private Genre createGenre(Long id, String name) {
        Genre Genre = new Genre();
        Genre.setId(id);
        Genre.setName(name);
        return Genre;
    }
}
