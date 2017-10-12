package kz.javalab.va.dao.impl;

import kz.javalab.va.connection.pool.ConnectionPool;
import kz.javalab.va.connection.pool.ConnectionPoolException;
import kz.javalab.va.dao.AbstractDao;
import kz.javalab.va.dao.DAOException;
import kz.javalab.va.entity.order.Order;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class OrderDao extends AbstractDao<Integer, Order> {
    private static final Logger LOGGER = Logger.getLogger(OrderDao.class);
    private final ConnectionPool pool = ConnectionPool.getInstance();

    private static final String ORDER_CREATE = "INSERT INTO CLIENT_ORDER(USER_ID, SUM, ADDRESS, PHONE, STATUS) VALUES(?, ?, ?, ?, ?);";
    private static final String GET_ORDER_ID_BY_USER_ID = "SELECT MAX(ID) FROM CLIENT_ORDER WHERE USER_ID=?";
    private static final String ORDER_UPDATE = "UPDATE CLIENT_ORDER SET USER_ID = ?, SUM = ?, ADDRESS = ?, PHONE = ?, STATUS = ? WHERE ID = ?;";

    public OrderDao() throws ConnectionPoolException {
    }

    @Override
    public List<Order> getAll() throws DAOException, ConnectionPoolException {
        return null;
    }

    @Override
    public Order getById(Integer id) throws DAOException {
        return null;
    }

    @Override
    public void delete(Integer id) throws DAOException {
        throw new DAOException("Unsupported operation.");
    }

    @Override
    public void delete(Order entity) throws DAOException {
        throw new DAOException("Unsupported operation.");
    }

    @Override
    public int create(Order entity) throws DAOException {
        LOGGER.info("Order.createOrder()");
        Connection connection = null;
        try {
            connection = pool.getConnection();
            LOGGER.debug("Connection has been taken.");
        } catch (ConnectionPoolException e) {
            LOGGER.warn("Connection cannot be taken.", e);
            e.printStackTrace();
        }
        try (PreparedStatement statement = connection.prepareStatement(ORDER_CREATE)) {
            statement.setInt(1, entity.getUserId());
            statement.setInt(2, entity.getSumOfOrder());
            statement.setString(3, entity.getAddress());
            statement.setString(4, entity.getPhone());
            statement.setString(5, String.valueOf(entity.getStatus()));
            System.out.println(entity.toString());
            return statement.executeUpdate();
        } catch (Exception e) {
            LOGGER.warn("Statement cannot be created.", e);
            throw new DAOException(e);
        } finally {
            pool.returnConnection(connection);
        }
    }

    @Override
    public int update(Order entity) throws DAOException {
        LOGGER.info("OrderDao.updateOrder()");
        Connection connection = null;
        try {
            connection = pool.getConnection();
            LOGGER.debug("Connection has been taken.");
        } catch (ConnectionPoolException e) {
            LOGGER.warn("Connection cannot be taken.", e);
            e.printStackTrace();
        }
        try (PreparedStatement statement = connection.prepareStatement(ORDER_UPDATE)) {
            statement.setInt(1, entity.getUserId());
            statement.setInt(2, entity.getSumOfOrder());
            statement.setString(3, entity.getAddress());
            statement.setString(4, entity.getPhone());
            statement.setString(5, String.valueOf(entity.getStatus()));
            statement.setInt(6, entity.getId());
            LOGGER.debug("Statement has been created.");
            return statement.executeUpdate();
        } catch (Exception e) {
            LOGGER.warn("Statement cannot be created.", e);
            throw new DAOException(e);
        } finally {
            pool.returnConnection(connection);
        }
    }

    public int getByUserId(Integer id) throws DAOException {
        LOGGER.info("OrderDao.getByUserId()");
        Integer orderId = null;
        Connection connection = null;
        try {
            connection = pool.getConnection();
            LOGGER.debug("Connection has been taken.");
        } catch (ConnectionPoolException e) {
            LOGGER.warn("Connection cannot be taken.", e);
            e.printStackTrace();
        }
        try (PreparedStatement statement = connection.prepareStatement(GET_ORDER_ID_BY_USER_ID)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                orderId = resultSet.getInt("MAX(ID)");
            }
        } catch (Exception e) {
            LOGGER.warn("Statement cannot be created.", e);
            throw new DAOException(e);
        } finally {
            pool.returnConnection(connection);
        }
        return orderId;
    }
}
