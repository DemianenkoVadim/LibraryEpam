package ua.com.epam.library.service;

import ua.com.epam.library.entity.Author;
import ua.com.epam.library.exception.myexception.AuthorNotFoundException;
import ua.com.epam.library.exception.myexception.NoSuchAuthorNameException;

import java.util.List;
import java.util.Optional;

public interface AuthorService {

    /**
     * Method finds author by first name and last name;
     *
     * @param authorFirstName author first name;
     * @param authorLastName  author last name;
     * @return Optional of author
     * @throws AuthorNotFoundException Exception
     */
    Optional<Author> findAuthorByFirstNameAndLastName(String authorFirstName, String authorLastName) throws AuthorNotFoundException;

    /**
     * Method finds first name author
     *
     * @param authorFirstName author first name
     * @return List of authors
     * @throws NoSuchAuthorNameException Exception
     */
    List<Author> findAuthorFirstName(String authorFirstName) throws NoSuchAuthorNameException;

    /**
     * Method finds author by last name
     *
     * @param authorLastName author last name
     * @return List of authors
     * @throws NoSuchAuthorNameException Exception
     */
    List<Author> findAuthorLastName(String authorLastName) throws NoSuchAuthorNameException;
}
