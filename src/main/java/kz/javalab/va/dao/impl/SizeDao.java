package kz.javalab.va.dao.impl;

import kz.javalab.va.connection.pool.ConnectionPool;
import kz.javalab.va.connection.pool.ConnectionPoolException;
import kz.javalab.va.dao.AbstractDao;
import kz.javalab.va.dao.DAOException;
import kz.javalab.va.entity.Food;
import kz.javalab.va.entity.Size;
import kz.javalab.va.entity.user.User;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SizeDao extends AbstractDao<Integer, Size> {
    private static final Logger LOGGER = Logger.getLogger(SizeDao.class);
    private final ConnectionPool pool = ConnectionPool.getInstance();
    private static final String SIZE_FIND_ALL = "SELECT * FROM SIZE ";
    private static final String GET_SIZE_BY_ID = "SELECT * FROM SIZE WHERE ID = ?;";
    private static final String GET_SIZE_BY_VALUE = "SELECT * FROM SIZE WHERE SIZE = ?;";

    public SizeDao() throws ConnectionPoolException {
    }


    @Override
    public List<Size> getAll() throws DAOException, ConnectionPoolException {
        List<Size> sizes = null;
        Connection connection = null;
        try {
            connection = pool.getConnection();
        } catch (ConnectionPoolException e) {
            e.printStackTrace();
        }
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SIZE_FIND_ALL);
            while (resultSet.next()) {
                if (sizes == null) {
                    sizes = new ArrayList<>();
                }
                Size size = new Size();
                size.setId(resultSet.getInt("ID"));
                size.setSize(resultSet.getInt("SIZE"));
                size.setName(resultSet.getString("NAME"));
                sizes.add(size);
            }
        } catch (Exception e) {
            throw new DAOException(e);
        } finally {
            pool.returnConnection(connection);
        }
        return sizes;
    }

    @Override
    public Size getById(Integer id) throws DAOException {
        LOGGER.info("SizeDao.getById()");
        Size size = null;
        Connection connection = null;
        try {
            connection = pool.getConnection();
            LOGGER.debug("Connection has been taken.");
        } catch (ConnectionPoolException e) {
            LOGGER.warn("Connection cannot be taken.", e);
            e.printStackTrace();
        }
        try (PreparedStatement statement = connection.prepareStatement(GET_SIZE_BY_ID)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                size = new Size();
                size.setId(resultSet.getInt("ID"));
                size.setName(resultSet.getString("NAME"));
                size.setSize(resultSet.getInt("SIZE"));
            }
        } catch (Exception e) {
            LOGGER.warn("Statement cannot be created.", e);
            throw new DAOException(e);
        } finally {
            pool.returnConnection(connection);
        }
        return size;
    }

    @Override
    public void delete(Integer id) throws DAOException {
        throw new DAOException("Unsupported operation.");
    }

    @Override
    public void delete(Size entity) throws DAOException {
        throw new DAOException("Unsupported operation.");
    }

    @Override
    public int create(Size entity) throws DAOException {
        return 0;
    }

    @Override
    public int update(Size entity) throws DAOException {
        return 0;
    }

    public Size getByValue (Integer value) throws DAOException {
        LOGGER.info("SizeDao.getByValue()");
        Size size = null;
        Connection connection = null;
        try {
            connection = pool.getConnection();
            LOGGER.debug("Connection has been taken.");
        } catch (ConnectionPoolException e) {
            LOGGER.warn("Connection cannot be taken.", e);
            e.printStackTrace();
        }
        try (PreparedStatement statement = connection.prepareStatement(GET_SIZE_BY_VALUE)) {
            statement.setInt(1, value);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                size = new Size();
                size.setId(resultSet.getInt("ID"));
                size.setName(resultSet.getString("NAME"));
                size.setSize(resultSet.getInt("SIZE"));
            }
        } catch (Exception e) {
            LOGGER.warn("Statement cannot be created.", e);
            throw new DAOException(e);
        } finally {
            pool.returnConnection(connection);
        }
        return size;
    }

}
