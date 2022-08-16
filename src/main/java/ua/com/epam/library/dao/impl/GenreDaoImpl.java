package ua.com.epam.library.dao.impl;

import ua.com.epam.library.dao.AbstractDao;
import ua.com.epam.library.dao.DatabaseTableTitle;
import ua.com.epam.library.dao.GenreDao;
import ua.com.epam.library.dao.mapper.RowMapperFactory;
import ua.com.epam.library.entity.Genre;
import ua.com.epam.library.exception.dao.DaoException;

import java.util.List;
import java.util.Optional;

import static ua.com.epam.library.util.DataBaseQueriesConstants.FIND_ALL_GENRES;
import static ua.com.epam.library.util.DataBaseQueriesConstants.FIND_GENRE;

public class GenreDaoImpl extends AbstractDao<Genre> implements GenreDao {

    public GenreDaoImpl() {
        super(RowMapperFactory.getInstance().getGenreRowMapper(), DatabaseTableTitle.GENRE);
    }

    @Override
    public Optional<Genre> findByName(String name) throws DaoException {
        return executeSingleResult(FIND_GENRE, name);
    }

    @Override
    public List<Genre> getAllGenres() throws DaoException {
        return executeQuery(FIND_ALL_GENRES);
    }
}