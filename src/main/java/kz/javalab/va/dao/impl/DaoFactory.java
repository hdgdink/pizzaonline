package kz.javalab.va.dao.impl;

import kz.javalab.va.connection.pool.ConnectionPool;
import kz.javalab.va.connection.pool.ConnectionPoolException;
import org.apache.log4j.Logger;

import java.sql.Connection;

public class DaoFactory {
    private final ConnectionPool pool = ConnectionPool.getInstance();
    private static final Logger LOGGER = Logger.getLogger(DaoFactory.class);

    public DaoFactory() throws ConnectionPoolException {
    }

    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = pool.getConnection();
            LOGGER.debug("Connection has been taken.");
        } catch (ConnectionPoolException e) {
            LOGGER.warn("Connection can not be taken.", e);
            e.printStackTrace();
        }
        return connection;
    }
}
