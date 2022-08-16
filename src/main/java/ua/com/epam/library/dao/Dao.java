package ua.com.epam.library.dao;

import ua.com.epam.library.exception.dao.DaoException;

import java.util.Optional;

public interface Dao<T> {

    /**
     * Method to get entity object from table by ID
     *
     * @param id ID of entity to find
     * @return optional Entity object from table
     * @throws DaoException DAO exception
     */
    Optional<T> findById(long id) throws DaoException;


}
