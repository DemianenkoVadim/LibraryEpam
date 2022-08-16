package ua.com.epam.library.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import ua.com.epam.library.dao.DaoFactory;
import ua.com.epam.library.dao.GenreDao;
import ua.com.epam.library.entity.Genre;
import ua.com.epam.library.exception.service.ServiceException;
import ua.com.epam.library.service.GenreService;

import java.util.List;
import java.util.Optional;

public class GenreServiceImpl implements GenreService {

    Logger log = (Logger) LogManager.getLogger(GenreServiceImpl.class.getName());

    private final GenreDao genreDao;

    public GenreServiceImpl() {
        this(DaoFactory.getInstance().getGenreDao());
    }

    public GenreServiceImpl(GenreDao genreDao) {
        this.genreDao = genreDao;
    }

    @Override
    public Optional<Genre> findByName(String genre) throws ServiceException {
        log.info("Searching genre by name: {}", genre);
        return genreDao.findByName(genre);
    }

    @Override
    public List<Genre> findAllGenres() {
        log.info("Searching all genres in database");
        return genreDao.getAllGenres();
    }
}
