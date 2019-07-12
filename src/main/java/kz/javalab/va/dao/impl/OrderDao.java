package kz.javalab.va.dao.impl;

import kz.javalab.va.dao.AbstractDao;
import kz.javalab.va.dao.DaoException;
import kz.javalab.va.dao.DaoFactory;
import kz.javalab.va.entity.order.Order;
import kz.javalab.va.entity.order.Status;
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

@Component()
@Repository
@ComponentScan("kz.javalab.va")
@Qualifier("orderDao")
public class OrderDao extends AbstractDao<Integer, Order> implements Dao<Order> {
    private static final Logger logger = Logger.getRootLogger();

    @Autowired
    private DaoFactory daoFactory;

    private static final String CREATE_ORDER = "INSERT INTO CLIENT_ORDER(USER_ID, SUM, ADDRESS, PHONE, STATUS) VALUES(?, ?, ?, ?, ?);";
    private static final String GET_ORDER_ID_BY_USER_ID = "SELECT MAX(ID) FROM CLIENT_ORDER WHERE USER_ID=?";
    private static final String UPDATE_ORDER = "UPDATE CLIENT_ORDER SET USER_ID = ?, SUM = ?, ADDRESS = ?, PHONE = ?, STATUS = ? WHERE ID = ?;";
    private static final String FIND_ALL_ORDERS = "SELECT * FROM CLIENT_ORDER ";

    public OrderDao() {
    }

    public OrderDao(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public String getReadQuery() {
        return FIND_ALL_ORDERS;
    }

    @Override
    public String getCreateQuery() {
        return CREATE_ORDER;
    }

    public Integer getLastOrder(Integer id) {
        Integer orderId = null;
        Connection connection = daoFactory.getConnection();
        ResultSet resultSet = null;

        try (PreparedStatement statement = connection.prepareStatement(GET_ORDER_ID_BY_USER_ID)) {
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                orderId = resultSet.getInt("MAX(ID)");
            }

        } catch (Exception e) {
            logger.error("Statement cannot be created.", e);
        } finally {
            daoFactory.closeResultSet(resultSet, connection);
        }

        return orderId;
    }

    @Override
    public void statementForCreate(PreparedStatement statement, Order entity) throws DaoException {
        try {
            statement.setInt(1, entity.getUserId());
            statement.setInt(2, entity.getSumOfOrder());
            statement.setString(3, entity.getAddress());
            statement.setString(4, entity.getPhone());
            statement.setString(5, String.valueOf(entity.getStatus()));
        } catch (Exception e) {
            logger.error("Preparing statement for Create Order error", e);
            throw new DaoException(e);
        }
    }

    @Override
    public void statementForUpdate(PreparedStatement statement, Order entity) throws DaoException {
        try {
            statementForCreate(statement, entity);
            statement.setInt(6, entity.getId());
        } catch (Exception e) {
            logger.error("Preparing statement for Update Order error", e);
            throw new DaoException(e);
        }
    }

    @Override
    public Order parseResultSetInstance(ResultSet resultSet) throws DaoException {
        Order order = new Order();

        try {
            order.setId(resultSet.getInt("ID"));
            order.setUserId(resultSet.getInt("USER_ID"));
            order.setSumOfOrder(resultSet.getInt("SUM"));
            order.setAddress(resultSet.getString("ADDRESS"));
            order.setPhone(resultSet.getString("PHONE"));
            order.setStatus(Status.valueOf(resultSet.getString("STATUS")));
        } catch (SQLException e) {
            logger.error("Error of results parsing", e);
            throw new DaoException(e);
        }
        return order;
    }

    @Override
    public String getUpdateQuery() {
        return UPDATE_ORDER;
    }
}