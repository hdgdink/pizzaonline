package kz.javalab.va.dao.impl;

import kz.javalab.va.connection.pool.ConnectionPool;
import kz.javalab.va.connection.pool.ConnectionPoolException;
import kz.javalab.va.dao.AbstractDao;
import kz.javalab.va.dao.DAOException;
import kz.javalab.va.entity.Type;
import kz.javalab.va.entity.user.User;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TypeDao extends AbstractDao<Integer, Type> {
    private static final Logger LOGGER = Logger.getLogger(TypeDao.class);
    private static final String GET_TYPE_NAME_BY_ID = "SELECT TYPE FROM TYPE WHERE ID = ?;";
    private static final String GET_TYPE_BY_ID = "SELECT * FROM TYPE WHERE ID = ?;";
    private static final String GET_ID_BY_TYPE_NAME = "SELECT ID FROM TYPE WHERE TYPE = ?;";
    private static final String TYPE_FIND_ALL = "SELECT * FROM TYPE ";

    private final ConnectionPool pool = ConnectionPool.getInstance();

    public TypeDao() throws ConnectionPoolException {
    }


    @Override
    public List<Type> getAll() throws DAOException, ConnectionPoolException {
        List<Type> types = null;
        LOGGER.info("TypeDao.getAllTypes()");
        Connection connection = null;
        try {
            connection = pool.getConnection();
            LOGGER.debug("Connection has been taken.");
        } catch (ConnectionPoolException e) {
            LOGGER.warn("Connection cannot be taken.", e);
            e.printStackTrace();
        }
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(TYPE_FIND_ALL);
            while (resultSet.next()) {
                if (types == null) {
                    types = new ArrayList<>();
                }
                Type type = new Type();
                type.setId(resultSet.getInt("id"));
                type.setType(resultSet.getString("TYPE"));
                types.add(type);
            }
        } catch (Exception e) {
            LOGGER.warn("Statement cannot be created.", e);
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
        Connection connection = null;
        try {
            connection = pool.getConnection();
            LOGGER.debug("Connection has been taken.");
        } catch (ConnectionPoolException e) {
            LOGGER.warn("Connection cannot be taken.", e);
            e.printStackTrace();
        }
        try (PreparedStatement statement = connection.prepareStatement(GET_TYPE_BY_ID)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.first()) {
                type=new Type();
                type.setId(resultSet.getInt("id"));
                type.setType(resultSet.getString("TYPE"));
            }
        } catch (Exception e) {
            LOGGER.warn("Statement cannot be created.", e);
            throw new DAOException(e);
        } finally {
            pool.returnConnection(connection);
        }
        return type;
    }

    @Override
    public void delete(Integer id) throws DAOException {
        throw new DAOException("Unsupported operation.");
    }

    @Override
    public void delete(Type entity) throws DAOException {
        throw new DAOException("Unsupported operation.");
    }

    @Override
    public int create(Type entity) throws DAOException {
        return 0;
    }

    @Override
    public int update(Type entity) throws DAOException {
        return 0;
    }

    public String getNameById(Integer id) throws DAOException {
        String name = null;
        LOGGER.info("TypeDao.getTypeByID()");
        Connection connection = null;
        try {
            connection = pool.getConnection();
            LOGGER.debug("Connection has been taken.");
        } catch (ConnectionPoolException e) {
            LOGGER.warn("Connection cannot be taken.", e);
            e.printStackTrace();
        }
        try (PreparedStatement statement = connection.prepareStatement(GET_TYPE_NAME_BY_ID)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                name = resultSet.getString("TYPE");
            }
        } catch (Exception e) {
            LOGGER.warn("Statement cannot be created.", e);
            throw new DAOException(e);
        } finally {
            pool.returnConnection(connection);
        }
        return name;
    }

    public Integer getIdByName(String name) throws DAOException {
        Integer id = null;
        LOGGER.info("TypeDao.getTypeByID()");
        Connection connection = null;
        try {
            connection = pool.getConnection();
            LOGGER.debug("Connection has been taken.");
        } catch (ConnectionPoolException e) {
            LOGGER.warn("Connection cannot be taken.", e);
            e.printStackTrace();
        }
        try (PreparedStatement statement = connection.prepareStatement(GET_ID_BY_TYPE_NAME)) {
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                id = resultSet.getInt("ID");
            }
        } catch (Exception e) {
            LOGGER.warn("Statement cannot be created.", e);
            throw new DAOException(e);
        } finally {
            pool.returnConnection(connection);
        }
        return id;
    }


}
