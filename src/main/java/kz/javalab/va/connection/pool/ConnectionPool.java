package kz.javalab.va.connection.pool;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;


public class ConnectionPool {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConnectionPool.class);

    private static final ResourceBundle RB = ResourceBundle.getBundle("db");
    private static final String DRIVER = RB.getString("db.driver");
    private static final String URL = RB.getString("db.url");
    private static final String USER = RB.getString("db.user");
    private static final String PASSWORD = RB.getString("db.password");
    private static final int POOL_SIZE = Integer.parseInt(RB.getString("db.pool_size"));
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
        pool = new ArrayBlockingQueue<>(POOL_SIZE, true);
        try {
            Class.forName(DRIVER);
            LOGGER.debug("Database driver was initialized.");
        } catch (ClassNotFoundException e) {
            LOGGER.error("Data base driver was not found.");
            throw new ConnectionPoolException();
        }
        for (int i = 0; i < POOL_SIZE; i++) {
            Connection connection = null;
            try {
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
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
