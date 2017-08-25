package kz.javalab.va.pool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;


public class ConnectionPool {
    private static final ResourceBundle RB = ResourceBundle.getBundle("db");
    private static final String DRIVER = RB.getString("db.driver");
    private static final String URL = RB.getString("db.url");
    private static final String USER = RB.getString("db.user");
    private static final String PASSWORD = RB.getString("db.password");
    private static final int POOL_SIZE = Integer.parseInt(RB.getString("db.pool_size"));
    private static final ConnectionPool INSTANCE = new ConnectionPool();
    private static BlockingQueue<Connection> connectionQueue;


    private ConnectionPool() {
                connectionQueue = new ArrayBlockingQueue<>(POOL_SIZE);
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < POOL_SIZE; i++) {
           Connection connection = null;
            try {

                connection = new DriverManager.getConnection(URL, USER, PASSWORD);
            } catch (SQLException e) {
                throw new ConnectionPoolException("There are no connection to the database", e);
            }
            connectionQueue.offer(connection);
        }
    }

    public static ConnectionPool getInstance() {
        return INSTANCE;
    }

    public Connection getConnection() throws InterruptedException {
        Connection connection = null;
        try {
            connection = connectionQueue.take();
        } catch (InterruptedException e) {
            throw new ConnectionPoolException("Free connection awaiting interrupted. Returning null.", e);
        }
        return connection;
    }

    public static void clearConnectionQueue() {
        Connection connection;
        while ((connection = connectionQueue.poll()) != null) {
            try {
                if (!connection.getAutoCommit()) {
                    connection.commit();
                }
                connection.close();
            } catch (SQLException e) {
                throw new ConnectionPoolException("", e);
            }
        }
    }

    private static void returnConnection(Connection connection) {
        connectionQueue.offer(connection);
    }


}
