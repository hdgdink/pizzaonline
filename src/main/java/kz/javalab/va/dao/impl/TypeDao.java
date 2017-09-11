package kz.javalab.va.dao.impl;

import kz.javalab.va.connection.pool.ConnectionPool;
import kz.javalab.va.dao.AbstractDao;
import kz.javalab.va.dao.DaoException;
import kz.javalab.va.entity.Type;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class TypeDao  extends AbstractDao<Type, Integer> {

    public TypeDao(ConnectionPool connectionPool) {
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
    protected List<Type> parseResultSet(ResultSet rs) throws DaoException {
        return null;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, Type object) throws DaoException {

    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Type object) throws DaoException {

    }

    @Override
    public Type create() throws DaoException {
        return null;
    }
}
