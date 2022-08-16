package ua.com.epam.library.dao.connection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionPool {

    private static final Logger log = (Logger) LogManager.getLogger(ConnectionPool.class.getName());

    private static ConnectionPool instance;
    private final DataSource dataSource;

    public static synchronized ConnectionPool getInstance() {
        if (instance == null)
            instance = new ConnectionPool();
        return instance;
    }

    public ConnectionPool() {
        try {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:comp/env");
            dataSource = (DataSource) envContext.lookup("jdbc/library_epam");
        } catch (NamingException ex) {
            log.error("", ex);
            throw new IllegalStateException(ex.getMessage(), ex);
        }
    }

    public Connection getConnection() {
        Connection connection;
        try {
            connection = dataSource.getConnection();
        } catch (SQLException ex) {
            log.error("Unable to get connection", ex);
            throw new IllegalStateException(ex.getMessage(), ex);
        }
        log.trace("Get connection");
        return connection;
    }
}
