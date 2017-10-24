package kz.javalab.va.connection.pool;

import kz.javalab.va.util.Constants;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;


public class ConnectionPool {
    private static final Logger LOGGER = Logger.getLogger(ConnectionPool.class);

    private static ConnectionPool instance;
    private static BlockingQueue<Connection> pool = null;

    public static ConnectionPool getInstance() throws ConnectionPoolException {
        ConnectionPool localInstance = instance;
        if (localInstance == null) {
            synchronized (ConnectionPool.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new ConnectionPool();
                    instance.createPool();
                }
            }
        }
        return localInstance;
    }

    private ConnectionPool() throws ConnectionPoolException {
        createPool();
    }

    private static void createPool() throws ConnectionPoolException {
        pool = new ArrayBlockingQueue<>(Constants.POOL_SIZE_RB, true);
        try {
            Class.forName(Constants.DRIVER_RB);
            LOGGER.debug("Database driver was initialized.");
        } catch (ClassNotFoundException e) {
            LOGGER.error("Data base driver was not found.");
            throw new ConnectionPoolException();
        }
        for (int i = 0; i < Constants.POOL_SIZE_RB; i++) {
            Connection connection = null;
            try {
                connection = DriverManager.getConnection(Constants.URL_RB, Constants.USER_RB, Constants.PASSWORD_RB);
            } catch (SQLException e) {
                throw new ConnectionPoolException("There are no connection to the database", e);
            }
            pool.add(connection);
        }
    }

    public synchronized Connection getConnection() throws ConnectionPoolException {
        Connection connection = null;
        try {
            connection = pool.take();

        } catch (InterruptedException e) {
            LOGGER.warn("Interrupted while waiting");
            throw new ConnectionPoolException();
        }
        return connection;
    }

    public void returnConnection(Connection connection) {
        if (connection != null) {
            try {
                pool.put(connection);
                LOGGER.debug("Connection has been returned.");
            } catch (InterruptedException e) {
                LOGGER.debug("Connection cannot be returned");
            }
        }
    }

    public void closeConnections() {
        for (Connection connection : pool) {
            try {
                connection.close();
                LOGGER.debug("Connection has been closed.");
            } catch (SQLException e) {
                LOGGER.error("Connection cannot be closed.");
            }
        }
    }

}
