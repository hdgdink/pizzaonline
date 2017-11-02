package kz.javalab.va.dao.impl;

import kz.javalab.va.connection.pool.ConnectionPool;
import kz.javalab.va.connection.pool.ConnectionPoolException;
import kz.javalab.va.dao.AbstractDao;
import kz.javalab.va.dao.DAOException;
import kz.javalab.va.dao.DaoFactory;
import kz.javalab.va.entity.OrderDetails;
import kz.javalab.va.util.Constants;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailsDao extends AbstractDao<Integer, OrderDetails> {
    private static final Logger LOGGER = Logger.getLogger(OrderDetailsDao.class);
    private final ConnectionPool pool = ConnectionPool.getInstance();
    private DaoFactory daoFactory = new DaoFactory();
    private static final String UPDATE_ORDER_DETAILS = "UPDATE ORDER_DETAILS SET ORDER_ID = ?, FOOD_ID = ?," +
            " FOOD_NAME_RU = ?, FOOD_NAME_EN = ?, TYPE_ID = ?, TYPE_NAME = ?, QUANTITY = ?, PRICE = ?," +
            " SIZE_NAME =? WHERE ID = ?;";
    private static final String CREATE_ORDER_DETAILS = "INSERT INTO ORDER_DETAILS(ORDER_ID, FOOD_ID,FOOD_NAME_RU," +
            "FOOD_NAME_EN, TYPE_ID, TYPE_NAME,  QUANTITY, PRICE, SIZE_NAME) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?);";
    private static final String FIND_ALL_ORDER_DETAILS = "SELECT * FROM ORDER_DETAILS";
    private static final String GET_ALL_ORDER_DETAILS_BY_ORDER_ID = "SELECT * FROM ORDER_DETAILS WHERE ORDER_ID = ?;";
    private static final String DELETE_STRING_BY_ID = "DELETE FROM ORDER_DETAILS WHERE ID=?;";


    public OrderDetailsDao() throws ConnectionPoolException {
    }


    public void delete(Integer id) throws DAOException {
        LOGGER.info("OrderDetailsDao.deleteOrderDetails()");
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
    public String getReadQuery() {
        return FIND_ALL_ORDER_DETAILS;
    }

    @Override
    public String getCreateQuery() {
        return CREATE_ORDER_DETAILS;
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
                OrderDetails orderDetails = parseResultSetInstance(resultSet);
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
    public void statementForCreate(PreparedStatement statement, OrderDetails entity) throws DAOException {
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
            LOGGER.error("Preparing statement for Create Order Details error", e);
            throw new DAOException(e);
        }
    }

    @Override
    public void statementForUpdate(PreparedStatement statement, OrderDetails entity) throws DAOException {
        try {
            statementForCreate(statement, entity);
            statement.setInt(10, entity.getId());
        } catch (Exception e) {
            LOGGER.error("Preparing statement for Update Order Details error", e);
            throw new DAOException(e);
        }

    }

    @Override
    public OrderDetails parseResultSetInstance(ResultSet resultSet) throws DAOException {
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

    @Override
    public String getUpdateQuery() {
        return UPDATE_ORDER_DETAILS;
    }

}
