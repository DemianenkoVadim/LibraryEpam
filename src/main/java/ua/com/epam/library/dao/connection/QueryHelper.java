package ua.com.epam.library.dao.connection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import ua.com.epam.library.dao.mapper.RowMapper;
import ua.com.epam.library.dao.mapper.RowMapperFactory;
import ua.com.epam.library.entity.Identifiable;
import ua.com.epam.library.exception.dao.DaoException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class QueryHelper<T extends Identifiable> {

    private static final Logger log = (Logger) LogManager.getLogger(QueryHelper.class.getName());

    private final RowMapper<T> rowMapper;

    public QueryHelper(RowMapper<T> rowMapper) {
        this.rowMapper = rowMapper;
    }

    protected List<T> executeQuery(String sqlRequest, Object... parameters) throws DaoException {
        List<T> entities;
        log.info("Creating connection... ");
        try (Connection con = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = createStatementConnection(con, sqlRequest, parameters);
             ResultSet resultSet = statement.executeQuery()) {
            log.info("Getting result set...");
            entities = createEntitiesList(resultSet);
        } catch (SQLException exception) {
            log.error("Can`t fulfill request", exception);
            throw new DaoException(exception.getMessage(), exception);
        }
        log.info("Returned result of request");
        return entities;
    }

    protected boolean executeInsertQuery(String sqlRequest, Object... parameters) throws DaoException {
        boolean entityIsAdded = false;
        log.info("Creating connection... ");
        try (Connection con = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = createStatementConnection(con, sqlRequest, parameters)) {
            if (statement.executeUpdate() == 1) {
                entityIsAdded = true;
            }
        } catch (SQLException exception) {
            log.error("Can`t add entity", exception);
            throw new DaoException(exception.getMessage(), exception);
        }
        log.info("Entity is added successfully");
        return entityIsAdded;
    }

    protected Optional<T> executeSingleResult(String sqlRequest, Object... parameters) throws DaoException {
        List<T> items = executeQuery(sqlRequest, parameters);
        if (items.isEmpty()) {
            return Optional.empty();
        }
        if (!(items.size() == 1)) {
            return Optional.empty();
        }
        log.info("Returned single request`s result");
        return Optional.of(items.get(0));

    }

    protected boolean executeToUpdateQuery(String sqlRequest, Object... parameters) throws DaoException {
        log.info("Creating connection... ");
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = createStatementConnection(connection, sqlRequest, parameters)) {
            statement.executeUpdate();
            log.info("Execute is updated... ");
            return true;
        } catch (SQLException exception) {
            log.error("Can`t execute to update", exception);
            throw new DaoException(exception.getMessage(), exception);
        }
    }

    private PreparedStatement createStatementConnection(Connection connection, String sqlRequest, Object... parameters) throws DaoException {
        try {
            log.info("Creating statement...");
            PreparedStatement statement = connection.prepareStatement(sqlRequest, Statement.RETURN_GENERATED_KEYS);
            for (int i = 0; i < parameters.length; i++) {
                statement.setObject(i + 1, parameters[i]);
            }
            log.info("Return created statement");
            return statement;
        } catch (SQLException exception) {
            log.error("Can`t create statement", exception);
            throw new DaoException(exception.getMessage(), exception);
        }
    }

    private List<T> createEntitiesList(ResultSet resultSet) throws DaoException {
        List<T> entities = new ArrayList<>();
        try {
            while (resultSet.next()) {
                T entity = rowMapper.map(resultSet);
                entities.add(entity);
            }
        } catch (SQLException exception) {
            log.error("Can`t create list of entities", exception);
            throw new DaoException(exception.getMessage(), exception);
        }
        log.info("Return created list of entities");
        return entities;
    }

    protected double executeQueryGetSize(String sqlRequest) throws DaoException {
        double size = 0;
        try (Connection connection = ConnectionPool.getInstance().getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlRequest);
            if (resultSet.next()) {
                size = resultSet.getInt(1);
            }
        } catch (SQLException exception) {
            log.info("Can`t retrieve size", exception);
            throw new DaoException(exception.getMessage(), exception);
        }
        return size;
    }

    protected <Q extends Identifiable> List<Q> getListEntities(Class<Q> type, String sqlRequest, Object... parameters) {
        List<Q> entities = new ArrayList<>();
        log.info("Creating connection... ");
        try (Connection con = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = createStatementConnection(con, sqlRequest, parameters);
             ResultSet resultSet = statement.executeQuery()) {
            log.info("Getting result set...");
            var mapper = RowMapperFactory.getInstance().getMapper(type);
            try {
                while (resultSet.next()) {
                    Q entity = mapper.map(resultSet);
                    entities.add(entity);
                }
            } catch (SQLException exception) {
                log.error("Can`t create list of entities", exception);
                throw new DaoException(exception.getMessage(), exception);
            }
            log.info("Return created list of entities");
        } catch (SQLException exception) {
            log.error("Can`t fulfill request", exception);
            throw new DaoException(exception.getMessage(), exception);
        }
        log.info("Returned result of request");
        return entities;
    }

}

