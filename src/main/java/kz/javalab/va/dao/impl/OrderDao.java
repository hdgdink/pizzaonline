package kz.javalab.va.dao.impl;

import kz.javalab.va.connection.pool.ConnectionPool;
import kz.javalab.va.connection.pool.ConnectionPoolException;
import kz.javalab.va.dao.AbstractDao;
import kz.javalab.va.dao.DAOException;
import kz.javalab.va.entity.order.Order;
import kz.javalab.va.entity.order.Status;
import kz.javalab.va.util.Constants;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDao extends AbstractDao<Integer, Order> {
    private static final Logger LOGGER = Logger.getLogger(OrderDao.class);
    private final ConnectionPool pool = ConnectionPool.getInstance();
    private DaoFactory daoFactory = new DaoFactory();
    private static final String CREATE_ORDER = "INSERT INTO CLIENT_ORDER(USER_ID, SUM, ADDRESS, PHONE, STATUS) VALUES(?, ?, ?, ?, ?);";
    private static final String GET_ORDER_ID_BY_USER_ID = "SELECT MAX(ID) FROM CLIENT_ORDER WHERE USER_ID=?";
    private static final String UPDATE_ORDER = "UPDATE CLIENT_ORDER SET USER_ID = ?, SUM = ?, ADDRESS = ?, PHONE = ?, STATUS = ? WHERE ID = ?;";
    private static final String FIND_ALL_ORDERS = "SELECT * FROM CLIENT_ORDER ";
    private static final String GET_ORDER_BY_ID = "SELECT * FROM CLIENT_ORDER WHERE ID = ?;";

    public OrderDao() throws ConnectionPoolException {
    }

    @Override
    public List<Order> getAll() throws DAOException, ConnectionPoolException {
        List<Order> orders = null;
        LOGGER.info("OrderDao.getAll()");
        Connection connection = daoFactory.getConnection();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(FIND_ALL_ORDERS);
            while (resultSet.next()) {
                if (orders == null) {
                    orders = new ArrayList<>();
                }
                Order order = parseResultSet(resultSet);
                orders.add(order);
            }
        } catch (Exception e) {
            LOGGER.warn(Constants.STATEMENT_CREATE_ERROR, e);
            throw new DAOException(e);
        } finally {
            pool.returnConnection(connection);
        }
        return orders;
    }

    @Override
    public Order getById(Integer id) throws DAOException {
        LOGGER.info("OrderDao.getById()");
        Order order = null;
        Connection connection = daoFactory.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(GET_ORDER_BY_ID)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                order = parseResultSet(resultSet);
            }
        } catch (Exception e) {
            LOGGER.warn(Constants.STATEMENT_CREATE_ERROR, e);
            throw new DAOException(e);
        } finally {
            pool.returnConnection(connection);
        }
        return order;
    }

    @Override
    public int create(Order entity) throws DAOException {
        LOGGER.info("OrderDao.createOrder()");
        Connection connection = daoFactory.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(CREATE_ORDER)) {
            statementForCreate(statement, entity);
            return statement.executeUpdate();
        } catch (Exception e) {
            LOGGER.warn(Constants.STATEMENT_CREATE_ERROR, e);
            throw new DAOException(e);
        } finally {
            pool.returnConnection(connection);
        }
    }

    @Override
    public int update(Order entity) throws DAOException {
        LOGGER.info("OrderDao.updateOrder()");
        Connection connection = daoFactory.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_ORDER)) {
            statementForCreate(statement, entity);
            statement.setInt(6, entity.getId());
            return statement.executeUpdate();
        } catch (Exception e) {
            LOGGER.warn(Constants.STATEMENT_CREATE_ERROR, e);
            throw new DAOException(e);
        } finally {
            pool.returnConnection(connection);
        }
    }

    public int getByUserId(Integer id) throws DAOException {
        LOGGER.info("OrderDao.getByUserId()");
        Integer orderId = null;
        Connection connection = daoFactory.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(GET_ORDER_ID_BY_USER_ID)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                orderId = resultSet.getInt(Constants.MAX_ID_COL);
            }
        } catch (Exception e) {
            LOGGER.warn(Constants.STATEMENT_CREATE_ERROR, e);
            throw new DAOException(e);
        } finally {
            pool.returnConnection(connection);
        }
        return orderId;
    }

    private void statementForCreate(PreparedStatement statement, Order entity) throws DAOException {
        try {
            statement.setInt(1, entity.getUserId());
            statement.setInt(2, entity.getSumOfOrder());
            statement.setString(3, entity.getAddress());
            statement.setString(4, entity.getPhone());
            statement.setString(5, String.valueOf(entity.getStatus()));
        } catch (Exception e) {
            LOGGER.error("Preparing statement for order error", e);
            throw new DAOException(e);
        }
    }

    private Order parseResultSet(ResultSet resultSet) throws DAOException {
        Order order = new Order();
        try {
            order.setId(resultSet.getInt(Constants.ID_COL));
            order.setUserId(resultSet.getInt(Constants.USER_ID_COL));
            order.setSumOfOrder(resultSet.getInt(Constants.SUM_COL));
            order.setAddress(resultSet.getString(Constants.ADDRESS_COL));
            order.setPhone(resultSet.getString(Constants.PHONE_COL));
            order.setStatus(Status.valueOf(resultSet.getString(Constants.STATUS_COL)));
        } catch (SQLException e) {
            LOGGER.error("Parsing resultSet to order error", e);
            throw new DAOException(e);
        }
        return order;
    }

}
