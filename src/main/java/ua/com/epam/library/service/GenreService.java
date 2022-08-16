package ua.com.epam.library.service;

import ua.com.epam.library.entity.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreService {

    /**
     * Method retrieve genre by name
     *
     * @param genre genre name;
     * @return Optional of Genre
     */
    Optional<Genre> findByName(String genre);

    /**
     * Method retrieve all genres
     *
     * @return List of genres
     */
    List<Genre> findAllGenres();

}
