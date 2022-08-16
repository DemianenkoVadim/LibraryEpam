package ua.com.epam.library.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import ua.com.epam.library.dao.AuthorDao;
import ua.com.epam.library.dao.DaoFactory;
import ua.com.epam.library.entity.Author;
import ua.com.epam.library.exception.myexception.NoSuchAuthorNameException;
import ua.com.epam.library.service.AuthorService;

import java.util.List;
import java.util.Optional;

public class AuthorServiceImpl implements AuthorService {

    Logger log = (Logger) LogManager.getLogger(AuthorServiceImpl.class.getName());

    private final AuthorDao authorDao;

    public AuthorServiceImpl() {
        this(DaoFactory.getInstance().getAuthorDao());
    }

    public AuthorServiceImpl(AuthorDao authorDao) {
        this.authorDao = authorDao;
    }

    @Override
    public Optional<Author> findAuthorByFirstNameAndLastName(String authorFirstName, String authorLastName) {
        log.info("Searching author by first name: {} and last name: {}", authorFirstName, authorLastName);
        return authorDao.findByFirstNameAndLastName(authorFirstName, authorLastName);
    }

    @Override
    public List<Author> findAuthorFirstName(String authorFirstName) {
        log.info("Searching author first name: {}", authorFirstName);
        return authorDao.findAuthorFirstNames(authorFirstName);
    }

    @Override
    public List<Author> findAuthorLastName(String authorLastName) throws NoSuchAuthorNameException {
        log.info("Searching author last name: {}", authorLastName);
        return authorDao.findAuthorLastNames(authorLastName);
    }
}
