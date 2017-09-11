package kz.javalab.va.dao.impl;

import kz.javalab.va.connection.pool.ConnectionPool;
import kz.javalab.va.dao.AbstractDao;
import kz.javalab.va.dao.DaoException;
import kz.javalab.va.entity.Order;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class OrderDao  extends AbstractDao<Order,Integer>{

    public OrderDao(ConnectionPool connectionPool) {
        super(connectionPool);
    }

    @Override
    public String getSelectQuery() {
        return null;
    }

    @Override
    public String getCreateQuery() {
        return null;
    }

    @Override
    public String getUpdateQuery() {
        return null;
    }

    @Override
    public String getDeleteQuery() {
        return null;
    }

    @Override
    protected List<Order> parseResultSet(ResultSet rs) throws DaoException {
        return null;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, Order object) throws DaoException {

    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Order object) throws DaoException {

    }

    @Override
    public Order create() throws DaoException {
        return null;
    }
}
