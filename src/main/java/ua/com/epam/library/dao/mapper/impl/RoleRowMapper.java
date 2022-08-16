package ua.com.epam.library.dao.mapper.impl;

import ua.com.epam.library.dao.mapper.Column;
import ua.com.epam.library.dao.mapper.RowMapper;
import ua.com.epam.library.entity.Role;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RoleRowMapper implements RowMapper<Role> {

    @Override
    public Role map(ResultSet resultSet) throws SQLException {
        Role role = new Role();
        role.setId(resultSet.getLong(Column.ID));
        role.setName(resultSet.getString(Column.ROLE));
        return role;
    }
}
