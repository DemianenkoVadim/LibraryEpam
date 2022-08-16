package ua.com.epam.library.dao.mapper.impl;

import ua.com.epam.library.dao.mapper.Column;
import ua.com.epam.library.dao.mapper.RowMapper;
import ua.com.epam.library.entity.Status;
import ua.com.epam.library.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class UserRowMapper implements RowMapper<User> {

    @Override
    public User map(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getLong(Column.ID));
        user.setName(resultSet.getString(Column.NAME));
        user.setEmail(resultSet.getString(Column.EMAIL));
        user.setPhone(resultSet.getString(Column.PHONE));
        user.setPassword(resultSet.getString(Column.PASSWORD));
        user.setRoleId(resultSet.getLong(Column.ROLE_ID));
        user.setRole(resultSet.getString(Column.ROLE));
        user.setCreated((LocalDateTime) resultSet.getObject(Column.CREATED));
        user.setUpdated((LocalDateTime) resultSet.getObject(Column.UPDATED));
        user.setStatus(Status.valueOf(resultSet.getString(Column.STATUS)));
        return user;
    }
}
