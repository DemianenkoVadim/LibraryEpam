package ua.com.epam.library.service;

import ua.com.epam.library.entity.Role;
import ua.com.epam.library.exception.service.ServiceException;

import java.util.Optional;

public interface RoleService {

    /**
     * Method retrieve role by ID
     *
     * @param roleId ID of role to retrieve
     * @return Optional of Role
     * @throws ServiceException Service Exception
     */
    Optional<Role> retrieveRoleById(long roleId) throws ServiceException;

    /**
     * Method retrieve role by role name
     *
     * @param role role name
     * @return Optional of Role
     * @throws ServiceException Service Exception
     */
    Optional<Role> retrieveRoleByName(String role) throws ServiceException;
}
