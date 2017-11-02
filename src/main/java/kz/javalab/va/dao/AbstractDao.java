package kz.javalab.va.dao;

import kz.javalab.va.connection.pool.ConnectionPool;
import kz.javalab.va.connection.pool.ConnectionPoolException;
import kz.javalab.va.entity.Entity;
import kz.javalab.va.util.Constants;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public abstract class AbstractDao<K extends Integer, T extends Entity> {
    private DaoFactory daoFactory = new DaoFactory();
    private final ConnectionPool pool = ConnectionPool.getInstance();

    protected AbstractDao() throws ConnectionPoolException {
    }

    public List<T> getAll() throws DAOException, ConnectionPoolException {
        List<T> list = null;
        Connection connection = daoFactory.getConnection();
        String query = getReadQuery();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                if (list == null) {
                    list = new ArrayList<>();
                }
                T object = parseResultSetInstance(resultSet);
                list.add(object);
            }
            resultSet.close();
            statement.close();
        } catch (Exception e) {
            throw new DAOException(Constants.STATEMENT_CREATE_ERROR, e);
        } finally {
            pool.returnConnection(connection);
        }
        return list;
    }

    public T getById(K id) throws DAOException {
        T object = null;
        Connection connection = daoFactory.getConnection();
        String query = getReadQuery() + " WHERE ID = ?;";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                object = parseResultSetInstance(resultSet);
            }
            resultSet.close();
            statement.close();
        } catch (Exception e) {
            throw new DAOException(Constants.STATEMENT_CREATE_ERROR, e);
        } finally {
            pool.returnConnection(connection);
        }
        return object;
    }

    public int create(T entity) throws DAOException {
        Connection connection = daoFactory.getConnection();
        String query = getCreateQuery();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statementForCreate(statement, entity);
            return statement.executeUpdate();
        } catch (Exception e) {
            throw new DAOException(Constants.STATEMENT_CREATE_ERROR, e);
        } finally {
            pool.returnConnection(connection);
        }
    }

    public int update(T entity) throws DAOException {
        Connection connection = daoFactory.getConnection();
        String query = getUpdateQuery();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statementForUpdate(statement, entity);
            return statement.executeUpdate();
        } catch (Exception e) {
            throw new DAOException(e);
        } finally {
            pool.returnConnection(connection);
        }
    }

    public abstract void statementForCreate(PreparedStatement statement, T entity) throws DAOException;

    public abstract void statementForUpdate(PreparedStatement statement, T entity) throws DAOException;

    public abstract T parseResultSetInstance(ResultSet resultSet) throws DAOException;

    public abstract String getReadQuery();

    public abstract String getCreateQuery();

    public abstract String getUpdateQuery();


}



