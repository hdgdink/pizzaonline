package kz.javalab.va.dao.impl;

import kz.javalab.va.connection.pool.ConnectionPool;
import kz.javalab.va.connection.pool.ConnectionPoolException;
import kz.javalab.va.dao.AbstractDao;
import kz.javalab.va.dao.DAOException;
import kz.javalab.va.entity.Type;
import kz.javalab.va.util.Constants;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TypeDao extends AbstractDao<Integer, Type> {
    private static final Logger LOGGER = Logger.getLogger(TypeDao.class);
    private final ConnectionPool pool = ConnectionPool.getInstance();
    private DaoFactory daoFactory = new DaoFactory();
    private static final String GET_TYPE_NAME_BY_ID = "SELECT TYPE FROM TYPE WHERE ID = ?;";
    private static final String GET_TYPE_BY_ID = "SELECT * FROM TYPE WHERE ID = ?;";
    private static final String FIND_ALL_TYPES = "SELECT * FROM TYPE ";
    private static final String UPDATE_TYPE = "UPDATE TYPE SET TYPE = ?, ACTIVE = ?  WHERE ID = ?;";
    private static final String CREATE_TYPE = "INSERT INTO TYPE ( TYPE, ACTIVE) VALUES(?, ?);";

    public TypeDao() throws ConnectionPoolException {
    }

    @Override
    public List<Type> getAll() throws DAOException, ConnectionPoolException {
        LOGGER.info("TypeDao.getAllTypes()");
        List<Type> types = null;
        Connection connection = daoFactory.getConnection();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(FIND_ALL_TYPES);
            while (resultSet.next()) {
                if (types == null) {
                    types = new ArrayList<>();
                }
                Type type = parseResultSet(resultSet);
                types.add(type);
            }
        } catch (Exception e) {
            LOGGER.warn(Constants.STATEMENT_CREATE_ERROR, e);
            throw new DAOException(e);
        } finally {
            pool.returnConnection(connection);
        }
        return types;
    }

    @Override
    public Type getById(Integer id) throws DAOException {
        LOGGER.info("TypeDao.getById()");
        Type type = null;
        Connection connection = daoFactory.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(GET_TYPE_BY_ID)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.first()) {
                type = parseResultSet(resultSet);
            }
        } catch (Exception e) {
            LOGGER.warn(Constants.STATEMENT_CREATE_ERROR, e);
            throw new DAOException(e);
        } finally {
            pool.returnConnection(connection);
        }
        return type;
    }

       @Override
    public int create(Type entity) throws DAOException {
        LOGGER.info("TypeDao.createType()");
        Connection connection = daoFactory.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(CREATE_TYPE)) {
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
    public int update(Type entity) throws DAOException {
        LOGGER.info("typeDao.updateType()");
        Connection connection = daoFactory.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_TYPE)) {
            statementForCreate(statement, entity);
            statement.setInt(3, entity.getId());
            return statement.executeUpdate();
        } catch (Exception e) {
            LOGGER.warn(Constants.STATEMENT_CREATE_ERROR, e);
            throw new DAOException(e);
        } finally {
            pool.returnConnection(connection);
        }
    }

    public String getNameById(Integer id) throws DAOException {
        LOGGER.info("TypeDao.getNameByID()");
        String name = null;
        Connection connection = daoFactory.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(GET_TYPE_NAME_BY_ID)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                name = resultSet.getString(Constants.TYPE_COL);
            }
        } catch (Exception e) {
            LOGGER.warn(Constants.STATEMENT_CREATE_ERROR, e);
            throw new DAOException(e);
        } finally {
            pool.returnConnection(connection);
        }
        return name;
    }

    private void statementForCreate(PreparedStatement statement, Type entity) throws DAOException {
        try {
            statement.setString(1, entity.getType());
            statement.setBoolean(2, entity.getActive());
        } catch (Exception e) {
            LOGGER.error("Preparing statement for Type error", e);
            throw new DAOException(e);
        }
    }

    private Type parseResultSet(ResultSet resultSet) throws DAOException {
        Type type = new Type();
        try {
            type.setId(resultSet.getInt(Constants.ID_COL));
            type.setType(resultSet.getString(Constants.TYPE_COL));
            type.setActive(resultSet.getBoolean(Constants.ACTIVE_COL));
        } catch (Exception e) {
            LOGGER.error("Parsing resultSet to Type error", e);
            throw new DAOException(e);
        }
        return type;
    }


}
