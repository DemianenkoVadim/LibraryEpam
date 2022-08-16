package ua.com.epam.library.dao.mapper;

import ua.com.epam.library.entity.Identifiable;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface RowMapper<T extends Identifiable> {

    /**
     * Method create entity Object from ResultSet
     *
     * @param resultSet {@link ResultSet} pointer that are set to row data to be mapped on entity object
     * @return Entity object with fields set from row database table
     * @throws SQLException exception
     */
    T map(ResultSet resultSet) throws SQLException;
}
