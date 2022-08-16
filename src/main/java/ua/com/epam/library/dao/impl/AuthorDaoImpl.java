package ua.com.epam.library.dao.impl;

import ua.com.epam.library.dao.AbstractDao;
import ua.com.epam.library.dao.AuthorDao;
import ua.com.epam.library.dao.DatabaseTableTitle;
import ua.com.epam.library.dao.mapper.RowMapperFactory;
import ua.com.epam.library.entity.Author;
import ua.com.epam.library.exception.dao.DaoException;

import java.util.List;
import java.util.Optional;

import static ua.com.epam.library.util.DataBaseQueriesConstants.*;

public class AuthorDaoImpl extends AbstractDao<Author> implements AuthorDao {

    public AuthorDaoImpl() {
        super(RowMapperFactory.getInstance().getAuthorRowMapper(), DatabaseTableTitle.AUTHOR);
    }

    @Override
    public Optional<Author> findByFirstNameAndLastName(String firstName, String lastName) throws DaoException {
        return executeSingleResult(FIND_AUTHOR_BY_FIRST_AND_LAST_NAME, firstName, lastName);
    }

    @Override
    public List<Author> findAuthorFirstNames(String authorFirstName) throws DaoException {
        return executeQuery(FIND_AUTHOR_FIRST_NAME, authorFirstName);
    }

    @Override
    public List<Author> findAuthorLastNames(String authorLastName) throws DaoException {
        return executeQuery(FIND_AUTHOR_LAST_NAME, authorLastName);
    }
}
