package kz.javalab.va.listener;

import kz.javalab.va.connection.pool.ConnectionPool;
import kz.javalab.va.connection.pool.ConnectionPoolException;
import kz.javalab.va.entity.user.Role;
import org.apache.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created by HdgDink} on 29.09.2017.
 */
public class ContextListener implements ServletContextListener {
    private static final Logger LOGGER = Logger.getLogger(ContextListener.class);


    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        LOGGER.debug("Servlet initilization...");
        context.setAttribute("roles", Role.values());

    }


    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ConnectionPool connectionPool;
        try {
            connectionPool = ConnectionPool.getInstance();
            connectionPool.closeConnections();
            LOGGER.debug("Connections have been closed.");
        } catch (ConnectionPoolException e) {
            LOGGER.error("Somethng fails with connection pool.");
        }
    }
}
