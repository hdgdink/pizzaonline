package kz.javalab.va.dao.impl;

import kz.javalab.va.connection.pool.ConnectionPool;
import kz.javalab.va.connection.pool.ConnectionPoolException;
import kz.javalab.va.dao.AbstractDao;
import kz.javalab.va.dao.DAOException;
import kz.javalab.va.entity.order.Order;
import kz.javalab.va.entity.order.Status;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OrderDao extends AbstractDao<Integer, Order> {
    private static final Logger LOGGER = Logger.getLogger(OrderDao.class);
    private static final String STATUS = "STATUS";
    private static final String ID = "ID";
    private static final String USER_ID = "USER_ID";
    private static final String SUM = "SUM";
    private static final String ADDRESS = "ADDRESS";
    private static final String PHONE = "PHONE";
    private final ConnectionPool pool = ConnectionPool.getInstance();
    private DaoFactory daoFactory = new DaoFactory();
    private static final String ORDER_CREATE = "INSERT INTO CLIENT_ORDER(USER_ID, SUM, ADDRESS, PHONE, STATUS) VALUES(?, ?, ?, ?, ?);";
    private static final String GET_ORDER_ID_BY_USER_ID = "SELECT MAX(ID) FROM CLIENT_ORDER WHERE USER_ID=?";
    private static final String ORDER_UPDATE = "UPDATE CLIENT_ORDER SET USER_ID = ?, SUM = ?, ADDRESS = ?, PHONE = ?, STATUS = ? WHERE ID = ?;";
    private static final String ORDER_FIND_ALL = "SELECT * FROM CLIENT_ORDER ";
    private static final String GET_ORDER_BY_ID = "SELECT * FROM CLIENT_ORDER WHERE ID = ?;";

    public OrderDao() throws ConnectionPoolException {
    }


    @Override
    public List<Order> getAll() throws DAOException, ConnectionPoolException {
        List<Order> orders = null;
        LOGGER.info("OrderDao.getAll()");
        Connection connection = daoFactory.getConnection();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(ORDER_FIND_ALL);
            while (resultSet.next()) {
                if (orders == null) {
                    orders = new ArrayList<>();
                }
                Order order = new Order();
                order.setId(resultSet.getInt(ID));
                order.setUserId(resultSet.getInt(USER_ID));
                order.setSumOfOrder(resultSet.getInt(SUM));
                order.setAddress(resultSet.getString(ADDRESS));
                order.setPhone(resultSet.getString(PHONE));
                order.setStatus(Status.valueOf(resultSet.getString(STATUS)));
                orders.add(order);
            }
        } catch (Exception e) {
            LOGGER.warn("Statement cannot be created.", e);
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
                order = new Order();
                order.setId(resultSet.getInt(ID));
                order.setUserId(resultSet.getInt(USER_ID));
                order.setSumOfOrder(resultSet.getInt(SUM));
                order.setAddress(resultSet.getString(ADDRESS));
                order.setPhone(resultSet.getString(PHONE));
                order.setStatus(Status.valueOf(resultSet.getString(STATUS)));
            }
        } catch (Exception e) {
            LOGGER.warn("Statement cannot be created.", e);
            throw new DAOException(e);
        } finally {
            pool.returnConnection(connection);
        }
        return order;
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
        Connection connection = daoFactory.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(ORDER_CREATE)) {
            statement.setInt(1, entity.getUserId());
            statement.setInt(2, entity.getSumOfOrder());
            statement.setString(3, entity.getAddress());
            statement.setString(4, entity.getPhone());
            statement.setString(5, String.valueOf(entity.getStatus()));

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
        Connection connection = daoFactory.getConnection();
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
        Connection connection = daoFactory.getConnection();
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
