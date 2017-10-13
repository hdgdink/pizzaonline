package kz.javalab.va.dao.impl;

import kz.javalab.va.connection.pool.ConnectionPool;
import kz.javalab.va.connection.pool.ConnectionPoolException;
import kz.javalab.va.dao.AbstractDao;
import kz.javalab.va.dao.DAOException;
import kz.javalab.va.entity.Type;

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
    private static final String TYPE_UPDATE = "UPDATE TYPE SET TYPE = ?, ACTIVE = ?  WHERE ID = ?;";
    private static final String TYPE_CREATE = "INSERT INTO TYPE ( TYPE, ACTIVE) VALUES(?, ?);";
    private final ConnectionPool pool = ConnectionPool.getInstance();
    private DaoFactory daoFactory = new DaoFactory();

    public TypeDao() throws ConnectionPoolException {
    }


    @Override
    public List<Type> getAll() throws DAOException, ConnectionPoolException {
        List<Type> types = null;
        LOGGER.info("TypeDao.getAllTypes()");
        Connection connection = daoFactory.getConnection();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(TYPE_FIND_ALL);
            while (resultSet.next()) {
                if (types == null) {
                    types = new ArrayList<>();
                }
                Type type = new Type();
                type.setId(resultSet.getInt("id"));
                type.setType(resultSet.getString("TYPE"));
                type.setActive(resultSet.getBoolean("ACTIVE"));
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
        Connection connection = daoFactory.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(GET_TYPE_BY_ID)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.first()) {
                type = new Type();
                type.setId(resultSet.getInt("id"));
                type.setType(resultSet.getString("TYPE"));
                type.setActive(resultSet.getBoolean("ACTIVE"));
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
        LOGGER.info("TypeDao.createType()");
        Connection connection = daoFactory.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(TYPE_CREATE)) {
            statement.setString(1, entity.getType());
            statement.setBoolean(2, entity.getActive());
            return statement.executeUpdate();
        } catch (Exception e) {
            LOGGER.warn("Statement cannot be created.", e);
            throw new DAOException(e);
        } finally {
            pool.returnConnection(connection);
        }
    }

    @Override
    public int update(Type entity) throws DAOException {
        LOGGER.info("typeDao.updateType()");
        Connection connection = daoFactory.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(TYPE_UPDATE)) {
            statement.setString(1, entity.getType());
            statement.setBoolean(2, entity.getActive());
            statement.setInt(3, entity.getId());
            LOGGER.debug("Statement has been created.");
            return statement.executeUpdate();
        } catch (Exception e) {
            LOGGER.warn("Statement cannot be created.", e);
            throw new DAOException(e);
        } finally {
            pool.returnConnection(connection);
        }
    }

    public String getNameById(Integer id) throws DAOException {
        String name = null;
        LOGGER.info("TypeDao.getNameByID()");
        Connection connection = daoFactory.getConnection();
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
        LOGGER.info("TypeDao.getIDByName()");
        Connection connection = daoFactory.getConnection();
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
