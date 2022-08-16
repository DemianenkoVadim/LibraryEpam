package ua.com.epam.library.dao;

import ua.com.epam.library.entity.Author;
import ua.com.epam.library.exception.dao.DaoException;

import java.util.List;
import java.util.Optional;

public interface AuthorDao {

    /**
     * Method find Author by firstName and secondName from database
     *
     * @param firstName first author name
     * @param lastName  second author name
     * @return Object of Author
     * @throws DaoException DAO exception
     */
    Optional<Author> findByFirstNameAndLastName(String firstName, String lastName) throws DaoException;

    /**
     * Method finds author first names
     *
     * @param authorFirstName first name author
     * @return List of authors
     * @throws DaoException Exception
     */
    List<Author> findAuthorFirstNames(String authorFirstName) throws DaoException;

    /**
     * Method finds author last names
     *
     * @param authorLastName last name author
     * @return List of authors
     * @throws DaoException Exception
     */
    List<Author> findAuthorLastNames(String authorLastName) throws DaoException;
}
