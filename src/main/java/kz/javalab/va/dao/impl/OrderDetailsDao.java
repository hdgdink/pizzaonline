package kz.javalab.va.dao.impl;

import kz.javalab.va.connection.pool.ConnectionPool;
import kz.javalab.va.connection.pool.ConnectionPoolException;
import kz.javalab.va.dao.AbstractDao;
import kz.javalab.va.dao.DAOException;
import kz.javalab.va.dao.DaoFactory;
import kz.javalab.va.entity.OrderDetails;
import kz.javalab.va.util.Constants;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailsDao extends AbstractDao<Integer, OrderDetails> {
    private static final Logger LOGGER = Logger.getLogger(OrderDetailsDao.class);
    private final ConnectionPool pool = ConnectionPool.getInstance();
    private DaoFactory daoFactory = new DaoFactory();
    private static final String UPDATE_ORDER_DETAILS = "UPDATE ORDER_DETAILS SET ORDER_ID = ?, FOOD_ID = ?," +
            " FOOD_NAME_RU = ?, FOOD_NAME_EN = ?, TYPE_ID = ?, TYPE_NAME = ?, QUANTITY = ?, PRICE = ?," +
            " SIZE_NAME =? WHERE ID = ?;";
    private static final String ORDER_DETAILS_CREATE = "INSERT INTO ORDER_DETAILS(ORDER_ID, FOOD_ID,FOOD_NAME_RU," +
            "FOOD_NAME_EN, TYPE_ID, TYPE_NAME,  QUANTITY, PRICE, SIZE_NAME) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?);";
    private static final String ORDER_DETAILS_FIND_ALL = "SELECT * FROM ORDER_DETAILS";
    private static final String GET_ALL_ORDER_DETAILS_BY_ORDER_ID = "SELECT * FROM ORDER_DETAILS WHERE ORDER_ID = ?;";
    private static final String GET_ORDER_DETAILS_BY_ID = "SELECT * FROM ORDER_DETAILS WHERE ID = ?;";
    private static final String DELETE_STRING_BY_ID = "DELETE FROM ORDER_DETAILS WHERE ID=?;";


    public OrderDetailsDao() throws ConnectionPoolException {
    }


    @Override
    public List<OrderDetails> getAll() throws DAOException, ConnectionPoolException {
        LOGGER.info("OrderDetailsDao.getAll()");
        List<OrderDetails> details = null;
        Connection connection = daoFactory.getConnection();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(ORDER_DETAILS_FIND_ALL);
            while (resultSet.next()) {
                if (details == null) {
                    details = new ArrayList<>();
                }
                OrderDetails orderDetails = parseResultSet(resultSet);
                details.add(orderDetails);
            }
        } catch (Exception e) {
            LOGGER.warn(Constants.STATEMENT_CREATE_ERROR, e);
            throw new DAOException(e);
        } finally {
            pool.returnConnection(connection);
        }
        return details;
    }

    @Override
    public OrderDetails getById(Integer id) throws DAOException {
        LOGGER.info("OrderDetailsDao.getById()");
        OrderDetails orderDetails = null;
        Connection connection = daoFactory.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(GET_ORDER_DETAILS_BY_ID)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                orderDetails = parseResultSet(resultSet);
            }
        } catch (Exception e) {
            LOGGER.warn(Constants.STATEMENT_CREATE_ERROR, e);
            throw new DAOException(e);
        } finally {
            pool.returnConnection(connection);
        }
        return orderDetails;
    }

    public void delete(Integer id) throws DAOException {
        LOGGER.info("OrderDetailsDao.createOrderDetail()");
        Connection connection = daoFactory.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(DELETE_STRING_BY_ID)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (Exception e) {
            LOGGER.warn(Constants.STATEMENT_CREATE_ERROR, e);
            throw new DAOException(e);
        } finally {
            pool.returnConnection(connection);
        }
    }

    @Override
    public int create(OrderDetails entity) throws DAOException {
        LOGGER.info("OrderDetailsDao.createOrderDetail()");
        Connection connection = daoFactory.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(ORDER_DETAILS_CREATE)) {
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
    public int update(OrderDetails entity) throws DAOException {
        LOGGER.info("OrderDetailsDao.updateDetails()");
        Connection connection = daoFactory.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_ORDER_DETAILS)) {
            statementForCreate(statement, entity);
            statement.setInt(10, entity.getId());
            return statement.executeUpdate();
        } catch (Exception e) {
            LOGGER.warn(Constants.STATEMENT_CREATE_ERROR, e);
            throw new DAOException(e);
        } finally {
            pool.returnConnection(connection);
        }
    }

    public List<OrderDetails> getAllByOrderId(Integer orderId) throws DAOException, ConnectionPoolException {
        LOGGER.info("OrderDetailsDao.getAllByOrderID()");
        List<OrderDetails> details = null;
        Connection connection = daoFactory.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(GET_ALL_ORDER_DETAILS_BY_ORDER_ID)) {
            statement.setInt(1, orderId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                if (details == null) {
                    details = new ArrayList<>();
                }
                OrderDetails orderDetails = parseResultSet(resultSet);
                details.add(orderDetails);
            }
        } catch (Exception e) {
            LOGGER.warn(Constants.STATEMENT_CREATE_ERROR, e);
            throw new DAOException(e);
        } finally {
            pool.returnConnection(connection);
        }
        return details;
    }

    private void statementForCreate(PreparedStatement statement, OrderDetails entity) throws DAOException {
        try {
            statement.setInt(1, entity.getOrderId());
            statement.setInt(2, entity.getFoodId());
            statement.setString(3, entity.getFoodNameRu());
            statement.setString(4, entity.getFoodNameEn());
            statement.setInt(5, entity.getTypeId());
            statement.setString(6, entity.getTypeName());
            statement.setInt(7, entity.getQuantity());
            statement.setInt(8, entity.getFinalPrice());
            statement.setString(9, entity.getSizeName());
        } catch (Exception e) {
            LOGGER.error("Preparing statement for order error", e);
            throw new DAOException(e);
        }
    }

    private OrderDetails parseResultSet(ResultSet resultSet) throws DAOException {
        OrderDetails orderDetails = new OrderDetails();
        try {
            orderDetails.setId(resultSet.getInt(Constants.ID_COL));
            orderDetails.setFoodId(resultSet.getInt(Constants.FOOD_ID_COL));
            orderDetails.setFoodNameRu(resultSet.getString(Constants.FOOD_NAME_RU_COL));
            orderDetails.setFoodNameEn(resultSet.getString(Constants.FOOD_NAME_EN_COL));
            orderDetails.setOrderId(resultSet.getInt(Constants.ORDER_ID_COL));
            orderDetails.setTypeId(resultSet.getInt(Constants.TYPE_ID_COL));
            orderDetails.setQuantity(resultSet.getInt(Constants.QNT_COL));
            orderDetails.setTypeName(resultSet.getString(Constants.TYPE_NAME_COL));
            orderDetails.setSizeName(resultSet.getString(Constants.SIZE_NAME_COL));
            orderDetails.setFinalPrice(resultSet.getInt(Constants.PRICE_COL));
        } catch (SQLException e) {
            LOGGER.error("Parsing resultSet to order error", e);
            throw new DAOException(e);
        }
        return orderDetails;
    }

}
