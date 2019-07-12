package kz.javalab.va.dao.impl;

import kz.javalab.va.dao.AbstractDao;
import kz.javalab.va.dao.DaoException;
import kz.javalab.va.dao.DaoFactory;
import kz.javalab.va.entity.OrderDetails;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component()
@Repository
@ComponentScan("kz.javalab.va")
@Qualifier("orderDetailsDao")
public class OrderDetailsDao extends AbstractDao<Integer, OrderDetails> implements Dao<OrderDetails> {
    private static final String STATEMENT_CREATE_ERROR = "Statement cannot be created.";
    private static final Logger logger = Logger.getRootLogger();

    @Autowired
    private DaoFactory daoFactory;

    private static final String UPDATE_ORDER_DETAILS = "UPDATE ORDER_DETAILS SET ORDER_ID = ?, FOOD_ID = ?," +
            " FOOD_NAME_RU = ?, FOOD_NAME_EN = ?, TYPE_ID = ?, QUANTITY = ?, PRICE = ?," +
            " SIZE_NAME =? WHERE ID = ?;";
    private static final String CREATE_ORDER_DETAILS = "INSERT INTO ORDER_DETAILS(ORDER_ID, FOOD_ID,FOOD_NAME_RU," +
            "FOOD_NAME_EN, TYPE_ID,  QUANTITY, PRICE, SIZE_NAME) VALUES(?, ?, ?, ?, ?, ?, ?, ?);";
    private static final String FIND_ALL_ORDER_DETAILS = "SELECT * FROM ORDER_DETAILS";
    private static final String GET_ALL_ORDER_DETAILS_BY_ORDER_ID = "SELECT * FROM ORDER_DETAILS WHERE ORDER_ID = ?;";
    private static final String DELETE_STRING_BY_ID = "DELETE FROM ORDER_DETAILS WHERE ID=?;";

    public OrderDetailsDao() {
    }

    public OrderDetailsDao(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    public void delete(Integer id) throws DaoException {
        Connection connection = daoFactory.getConnection();

        try (PreparedStatement statement = connection.prepareStatement(DELETE_STRING_BY_ID)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (Exception e) {
            logger.error(STATEMENT_CREATE_ERROR, e);
            throw new DaoException(e);
        } finally {
            daoFactory.returnConnection(connection);
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

    public List<OrderDetails> getAllByOrderId(Integer orderId) {
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
            logger.error(STATEMENT_CREATE_ERROR, e);
        } finally {
            daoFactory.returnConnection(connection);
        }
        return details;
    }

    @Override
    public void statementForCreate(PreparedStatement statement, OrderDetails entity) throws DaoException {
        try {
            statement.setInt(1, entity.getOrderId());
            statement.setInt(2, entity.getFoodId());
            statement.setString(3, entity.getFoodNameRu());
            statement.setString(4, entity.getFoodNameEn());
            statement.setInt(5, entity.getTypeId());
            statement.setInt(6, entity.getQuantity());
            statement.setInt(7, entity.getFinalPrice());
            statement.setString(8, entity.getSizeName());
        } catch (Exception e) {
            logger.error("Preparing statement for Create Order Details error", e);
            throw new DaoException(e);
        }
    }

    @Override
    public void statementForUpdate(PreparedStatement statement, OrderDetails entity) throws DaoException {
        try {
            statementForCreate(statement, entity);
            statement.setInt(9, entity.getId());
        } catch (Exception e) {
            logger.error("Preparing statement for Update Order Details error", e);
            throw new DaoException(e);
        }

    }

    @Override
    public OrderDetails parseResultSetInstance(ResultSet resultSet) throws DaoException {
        OrderDetails orderDetails = new OrderDetails();

        try {
            orderDetails.setId(resultSet.getInt("ID"));
            orderDetails.setFoodId(resultSet.getInt("FOOD_ID"));
            orderDetails.setFoodNameRu(resultSet.getString("FOOD_NAME_RU"));
            orderDetails.setFoodNameEn(resultSet.getString("FOOD_NAME_EN"));
            orderDetails.setOrderId(resultSet.getInt("ORDER_ID"));
            orderDetails.setTypeId(resultSet.getInt("TYPE_ID"));
            orderDetails.setQuantity(resultSet.getInt("QUANTITY"));
            orderDetails.setSizeName(resultSet.getString("SIZE_NAME"));
            orderDetails.setFinalPrice(resultSet.getInt("PRICE"));
        } catch (SQLException e) {
            logger.error("Error of results parsing", e);
            throw new DaoException(e);
        }
        return orderDetails;
    }

    @Override
    public String getUpdateQuery() {
        return UPDATE_ORDER_DETAILS;
    }

}
