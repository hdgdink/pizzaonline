package kz.javalab.va.dao.impl;

import kz.javalab.va.connection.pool.ConnectionPool;
import kz.javalab.va.dao.AbstractDao;
import kz.javalab.va.dao.DaoException;
import kz.javalab.va.entity.Size;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class SizeDao extends AbstractDao<Size, Integer> {

    public SizeDao(ConnectionPool connectionPool) {
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
    protected List<Size> parseResultSet(ResultSet rs) throws DaoException {
        return null;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, Size object) throws DaoException {

    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Size object) throws DaoException {

    }

    @Override
    public Size create() throws DaoException {
        return null;
    }
}
