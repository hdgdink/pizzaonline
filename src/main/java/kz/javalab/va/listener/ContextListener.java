package kz.javalab.va.listener;

import kz.javalab.va.connection.pool.ConnectionPool;
import kz.javalab.va.connection.pool.ConnectionPoolException;
import org.apache.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContextListener implements ServletContextListener {
    private static final Logger LOGGER = Logger.getLogger(ContextListener.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        LOGGER.debug("Servlet initilization...");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ConnectionPool connectionPool;
        try {
            connectionPool = ConnectionPool.getInstance();
            connectionPool.closeConnections();
            LOGGER.debug("Connections have been closed.");
        } catch (ConnectionPoolException e) {
            LOGGER.error("Something fails with connection pool.", e);
        }
    }
}
