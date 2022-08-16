package ua.com.epam.library.dao;

import ua.com.epam.library.dao.connection.QueryHelper;
import ua.com.epam.library.dao.mapper.RowMapper;
import ua.com.epam.library.entity.Identifiable;
import ua.com.epam.library.exception.dao.DaoException;

import java.util.Optional;

public abstract class AbstractDao<T extends Identifiable> extends QueryHelper<T> implements Dao<T> {

    private final String databaseTableName;

    public AbstractDao(RowMapper<T> rowMapper, String databaseTableName) {
        super(rowMapper);
        this.databaseTableName = databaseTableName;
    }

    @Override
    public Optional<T> findById(long id) throws DaoException {
        String FIND_BY_ID = "SELECT * FROM " + databaseTableName + " WHERE id=?";
        return executeSingleResult(FIND_BY_ID, id);
    }
}
