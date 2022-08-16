package ua.com.epam.library.dao.impl;

import ua.com.epam.library.dao.AbstractDao;
import ua.com.epam.library.dao.DatabaseTableTitle;
import ua.com.epam.library.dao.RoleDao;
import ua.com.epam.library.dao.mapper.RowMapperFactory;
import ua.com.epam.library.entity.Role;
import ua.com.epam.library.exception.dao.DaoException;

import java.util.Optional;

import static ua.com.epam.library.util.DataBaseQueriesConstants.FIND_ROLE_BY_NAME_ROLE;

public class RoleDaoImpl extends AbstractDao<Role> implements RoleDao {

    public RoleDaoImpl() {
        super(RowMapperFactory.getInstance().getRoleRowMapper(), DatabaseTableTitle.ROLE);
    }

    @Override
    public Optional<Role> findByName(String role) throws DaoException {
        return executeSingleResult(FIND_ROLE_BY_NAME_ROLE, role);
    }
}
