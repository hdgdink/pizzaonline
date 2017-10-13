package kz.javalab.va.dao.impl;

import kz.javalab.va.connection.pool.ConnectionPool;
import kz.javalab.va.connection.pool.ConnectionPoolException;
import kz.javalab.va.dao.AbstractDao;
import kz.javalab.va.dao.DAOException;
import kz.javalab.va.entity.OrderDetails;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailsDao extends AbstractDao<Integer, OrderDetails> {
    private static final Logger LOGGER = Logger.getLogger(OrderDetailsDao.class);
    private static final String ID = "ID";
    private static final String FOOD_ID = "FOOD_ID";
    private static final String FOOD_NAME_RU = "FOOD_NAME_RU";
    private static final String FOOD_NAME_EN = "FOOD_NAME_EN";
    private static final String ORDER_ID = "ORDER_ID";
    private static final String TYPE_ID = "TYPE_ID";
    private static final String TYPE_NAME = "TYPE_NAME";
    private static final String SIZE_NAME = "SIZE_NAME";
    private static final String QNT = "QUANTITY";
    private static final String PRICE = "PRICE";

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
                OrderDetails orderDetails = new OrderDetails();
                orderDetails.setId(resultSet.getInt(ID));
                orderDetails.setFoodId(resultSet.getInt(FOOD_ID));
                orderDetails.setFoodNameRu(resultSet.getString(FOOD_NAME_RU));
                orderDetails.setFoodNameEn(resultSet.getString(FOOD_NAME_EN));
                orderDetails.setOrderId(resultSet.getInt(ORDER_ID));
                orderDetails.setTypeId(resultSet.getInt(TYPE_ID));
                orderDetails.setTypeName(resultSet.getString(TYPE_NAME));
                orderDetails.setSizeName(resultSet.getString(SIZE_NAME));
                orderDetails.setQuantity(resultSet.getInt(QNT));
                orderDetails.setFinalPrice(resultSet.getInt(PRICE));
                details.add(orderDetails);
            }
        } catch (Exception e) {
            LOGGER.warn("Statement cannot be created.", e);
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
                orderDetails = new OrderDetails();
                orderDetails.setId(resultSet.getInt(ID));
                orderDetails.setFoodId(resultSet.getInt(FOOD_ID));
                orderDetails.setFoodNameRu(resultSet.getString(FOOD_NAME_RU));
                orderDetails.setFoodNameEn(resultSet.getString(FOOD_NAME_EN));
                orderDetails.setOrderId(resultSet.getInt(ORDER_ID));
                orderDetails.setTypeId(resultSet.getInt(TYPE_ID));
                orderDetails.setTypeName(resultSet.getString(TYPE_NAME));
                orderDetails.setSizeName(resultSet.getString(SIZE_NAME));
                orderDetails.setQuantity(resultSet.getInt(QNT));
                orderDetails.setFinalPrice(resultSet.getInt(PRICE));
            }
        } catch (Exception e) {
            LOGGER.warn("Statement cannot be created.", e);
            throw new DAOException(e);
        } finally {
            pool.returnConnection(connection);
        }
        return orderDetails;
    }

    @Override
    public void delete(Integer id) throws DAOException {
        LOGGER.info("OrderDetailsDao.createOrderDetail()");
        Connection connection = daoFactory.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(DELETE_STRING_BY_ID)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (Exception e) {
            LOGGER.warn("Statement cannot be created.", e);
            throw new DAOException(e);
        } finally {
            pool.returnConnection(connection);
        }
    }


    @Override
    public void delete(OrderDetails entity) throws DAOException {
        throw new DAOException("Unsupported operation.");
    }

    @Override
    public int create(OrderDetails entity) throws DAOException {
        LOGGER.info("OrderDetailsDao.createOrderDetail()");
        Connection connection = daoFactory.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(ORDER_DETAILS_CREATE)) {
            statement.setInt(1, entity.getOrderId());
            statement.setInt(2, entity.getFoodId());
            statement.setString(3, entity.getFoodNameRu());
            statement.setString(4, entity.getFoodNameEn());
            statement.setInt(5, entity.getTypeId());
            statement.setString(6, entity.getTypeName());
            statement.setInt(7, entity.getQuantity());
            statement.setInt(8, entity.getFinalPrice());
            statement.setString(9, entity.getSizeName());
            return statement.executeUpdate();
        } catch (Exception e) {
            LOGGER.warn("Statement cannot be created.", e);
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
            statement.setInt(1, entity.getOrderId());
            statement.setInt(2, entity.getFoodId());
            statement.setString(3, entity.getFoodNameRu());
            statement.setString(4, entity.getFoodNameEn());
            statement.setInt(5, entity.getTypeId());
            statement.setString(6, entity.getTypeName());
            statement.setInt(7, entity.getQuantity());
            statement.setInt(8, entity.getFinalPrice());
            statement.setString(9, entity.getSizeName());
            statement.setInt(10, entity.getId());
            LOGGER.debug("Statement has been created.");
            return statement.executeUpdate();
        } catch (Exception e) {
            LOGGER.warn("Statement cannot be created.", e);
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
                OrderDetails orderDetails = new OrderDetails();
                orderDetails.setId(resultSet.getInt(ID));
                orderDetails.setFoodId(resultSet.getInt(FOOD_ID));
                orderDetails.setFoodNameRu(resultSet.getString(FOOD_NAME_RU));
                orderDetails.setFoodNameEn(resultSet.getString(FOOD_NAME_EN));
                orderDetails.setOrderId(resultSet.getInt(ORDER_ID));
                orderDetails.setTypeId(resultSet.getInt(TYPE_ID));
                orderDetails.setQuantity(resultSet.getInt(QNT));
                orderDetails.setTypeName(resultSet.getString(TYPE_NAME));
                orderDetails.setSizeName(resultSet.getString(SIZE_NAME));
                orderDetails.setFinalPrice(resultSet.getInt(PRICE));
                details.add(orderDetails);
            }
        } catch (Exception e) {
            LOGGER.warn("Statement cannot be created.", e);
            throw new DAOException(e);
        } finally {
            pool.returnConnection(connection);
        }
        return details;
    }

}
