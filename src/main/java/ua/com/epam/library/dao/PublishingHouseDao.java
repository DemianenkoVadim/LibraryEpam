package ua.com.epam.library.dao;

import ua.com.epam.library.entity.PublishingHouse;
import ua.com.epam.library.exception.dao.DaoException;

import java.util.List;
import java.util.Optional;

public interface PublishingHouseDao {

    /**
     * Method find genre by name from database
     *
     * @param name genre name
     * @return Object of Genre
     * @throws DaoException DAO exception
     */
    Optional<PublishingHouse> findByName(String name) throws DaoException;

    /**
     * Method finds all publishing house in database
     *
     * @return List of publishing house
     * @throws DaoException exception
     */
    List<PublishingHouse> getAllPublishingHouse() throws DaoException;
}
