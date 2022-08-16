package ua.com.epam.library.dao;

import ua.com.epam.library.entity.Genre;
import ua.com.epam.library.exception.dao.DaoException;

import java.util.List;
import java.util.Optional;

public interface GenreDao {

    /**
     * Method find genre by name from database
     *
     * @param name genre name
     * @return Object of Genre
     * @throws DaoException DAO exception
     */
    Optional<Genre> findByName(String name) throws DaoException;

    /**
     * Method gets all genres from database
     *
     * @return List of genres
     * @throws DaoException Exception
     */
    List<Genre> getAllGenres() throws DaoException;
}
