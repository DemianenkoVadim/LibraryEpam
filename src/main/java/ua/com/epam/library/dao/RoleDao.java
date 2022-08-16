package ua.com.epam.library.dao;

import ua.com.epam.library.entity.Role;
import ua.com.epam.library.exception.dao.DaoException;

import java.util.Optional;

public interface RoleDao extends Dao<Role> {

    /**
     * Method find user by name role
     *
     * @param role name role
     * @return Optional of role
     * @throws DaoException DAO Exception
     */
    Optional<Role> findByName(String role) throws DaoException;
}
