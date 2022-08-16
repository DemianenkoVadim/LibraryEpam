package ua.com.epam.library.dao.mapper.impl;

import ua.com.epam.library.dao.mapper.Column;
import ua.com.epam.library.dao.mapper.RowMapper;
import ua.com.epam.library.entity.PublishingHouse;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PublishingHouseRowMapper implements RowMapper<PublishingHouse> {

    @Override
    public PublishingHouse map(ResultSet resultSet) throws SQLException {
        PublishingHouse company = new PublishingHouse();
        company.setId(resultSet.getLong(Column.ID));
        company.setName(resultSet.getString(Column.PUBLISHING_HOUSE));
        return company;
    }
}
