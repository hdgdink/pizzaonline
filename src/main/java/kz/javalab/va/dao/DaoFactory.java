package kz.javalab.va.dao;

import kz.javalab.va.connection.pool.ConnectionPool;
import kz.javalab.va.connection.pool.ConnectionPoolException;
import kz.javalab.va.util.Constants;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class DaoFactory {
    private final ConnectionPool pool = ConnectionPool.getInstance();
    private static final Logger LOGGER = Logger.getLogger(DaoFactory.class);

    public DaoFactory() throws ConnectionPoolException {
    }

    public Connection getConnection() throws DAOException {
        Connection connection;
        try {
            connection = pool.getConnection();
            LOGGER.debug("Connection has been taken.");
        } catch (ConnectionPoolException e) {
            LOGGER.warn("Connection can not be taken.", e);
            throw new DAOException(e);
        }
        return connection;
    }
    }
