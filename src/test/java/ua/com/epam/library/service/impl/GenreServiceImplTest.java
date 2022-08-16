package ua.com.epam.library.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ua.com.epam.library.dao.GenreDao;
import ua.com.epam.library.entity.Genre;
import ua.com.epam.library.exception.myexception.GenreNotFoundException;
import ua.com.epam.library.service.GenreService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GenreServiceImplTest {

    private GenreService genreService;

    @Mock
    private GenreDao genreDao;

    @BeforeEach
    void setUp() {
        genreService = new GenreServiceImpl(genreDao);
    }

    @Test
    void testFindByName_ShouldReturnSuccessfullGenre() {
        //given
        String genre = "fantasy";
        given(genreDao.findByName(genre)).willReturn(Optional.of(new Genre()));

        //when
        when(genreDao.findByName(genre)).thenReturn(Optional.of(new Genre()));
        Optional<Genre> genreOptional = genreService.findByName(genre);

        //then
        assertTrue(genreOptional.isPresent(), "Genre should be present");

        verify(genreDao).findByName(genre);
    }

    @Test
    void testFindGenreByName_ShouldReturnThrownWhenGenreNameIsNull() {
        //given
        given(genreDao.findByName(null))
                .willThrow(new GenreNotFoundException("Genre with genre name null is not found"));
        //when
        //then
        assertThrows(GenreNotFoundException.class, () -> Optional
                .ofNullable(genreService.findByName(null))
                .orElseThrow(() -> new GenreNotFoundException("Genre with genre name null is not found")));
    }

    @Test
    void testFindGenreByName_ShouldReturnThrownWithEmptyParameters() {
        //given
        String genre = "";
        given(genreDao.findByName(genre))
                .willThrow(new GenreNotFoundException("Genre with genre empty genre name does`t found "));
        //when
        //then
        assertThrows(GenreNotFoundException.class, () -> Optional
                .ofNullable(genreService.findByName(genre))
                .orElseThrow(() -> new GenreNotFoundException("Genre with genre empty genre name does`t found ")));
    }
}