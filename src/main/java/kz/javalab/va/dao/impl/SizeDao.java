package kz.javalab.va.dao.impl;

import kz.javalab.va.connection.pool.ConnectionPool;
import kz.javalab.va.connection.pool.ConnectionPoolException;
import kz.javalab.va.dao.AbstractDao;
import kz.javalab.va.dao.DAOException;
import kz.javalab.va.dao.DaoFactory;
import kz.javalab.va.entity.Size;
import kz.javalab.va.util.Constants;
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
    private DaoFactory daoFactory = new DaoFactory();
    private static final String UPDATE_SIZE = "UPDATE SIZE SET SIZE = ?, NAME = ?, ACTIVE = ?  WHERE ID = ?;";
    private static final String CREATE_SIZE = "INSERT INTO SIZE ( SIZE, NAME, ACTIVE) VALUES(?, ?, ?);";
    private static final String FIND_ALL_SIZES = "SELECT * FROM SIZE ";
    private static final String GET_SIZE_BY_ID = "SELECT * FROM SIZE WHERE ID = ?;";
    private static final String GET_SIZE_BY_VALUE = "SELECT * FROM SIZE WHERE SIZE = ?;";
    private static final String GET_ALL_SIZES_BY_ACTIVE = "SELECT * FROM SIZE WHERE ACTIVE = ?;";

    public SizeDao() throws ConnectionPoolException {
    }

    @Override
    public List<Size> getAll() throws DAOException, ConnectionPoolException {
        List<Size> sizes = null;
        Connection connection = daoFactory.getConnection();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(FIND_ALL_SIZES);
            while (resultSet.next()) {
                if (sizes == null) {
                    sizes = new ArrayList<>();
                }
                Size size = parseResultSet(resultSet);
                sizes.add(size);
            }
        } catch (Exception e) {
            LOGGER.warn(Constants.STATEMENT_CREATE_ERROR, e);
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
        Connection connection = daoFactory.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(GET_SIZE_BY_ID)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                size = parseResultSet(resultSet);
            }
        } catch (Exception e) {
            LOGGER.warn(Constants.STATEMENT_CREATE_ERROR, e);
            throw new DAOException(e);
        } finally {
            pool.returnConnection(connection);
        }
        return size;
    }

    @Override
    public int create(Size entity) throws DAOException {
        LOGGER.info("SizeDao.createSize()");
        Connection connection = daoFactory.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(CREATE_SIZE)) {
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
    public int update(Size entity) throws DAOException {
        LOGGER.info("SizeDao.updateSize()");
        Connection connection = daoFactory.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_SIZE)) {
            statementForCreate(statement, entity);
            statement.setInt(4, entity.getId());
            return statement.executeUpdate();
        } catch (Exception e) {
            LOGGER.warn(Constants.STATEMENT_CREATE_ERROR, e);
            throw new DAOException(e);
        } finally {
            pool.returnConnection(connection);
        }
    }

    public Size getByValue(Integer value) throws DAOException {
        LOGGER.info("SizeDao.getByValue()");
        Size size = null;
        Connection connection = daoFactory.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(GET_SIZE_BY_VALUE)) {
            statement.setInt(1, value);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                size = parseResultSet(resultSet);
            }
        } catch (Exception e) {
            LOGGER.warn(Constants.STATEMENT_CREATE_ERROR, e);
            throw new DAOException(e);
        } finally {
            pool.returnConnection(connection);
        }
        return size;
    }

    public List<Size> getAllByActive(Boolean active) throws DAOException {
        List<Size> sizeList = null;
        Connection connection = daoFactory.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(GET_ALL_SIZES_BY_ACTIVE)) {
            statement.setBoolean(1, active);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                if (sizeList == null) {
                    sizeList = new ArrayList<>();
                }
                Size size = parseResultSet(resultSet);
                if (size.getActive())
                    sizeList.add(size);
            }
        } catch (Exception e) {
            LOGGER.warn(Constants.STATEMENT_CREATE_ERROR, e);
            throw new DAOException(e);
        } finally {
            pool.returnConnection(connection);
        }
        return sizeList;
    }

    private void statementForCreate(PreparedStatement statement, Size entity) throws DAOException {
        try {
            statement.setInt(1, entity.getSize());
            statement.setString(2, entity.getName());
            statement.setBoolean(3, entity.getActive());
        } catch (Exception e) {
            LOGGER.error("Preparing statement for Size error", e);
            throw new DAOException(e);
        }
    }

    private Size parseResultSet(ResultSet resultSet) throws DAOException {
        Size size = new Size();
        try {
            size.setId(resultSet.getInt(Constants.ID_COL));
            size.setSize(resultSet.getInt(Constants.SIZE_COL));
            size.setName(resultSet.getString(Constants.NAME_COL));
            size.setActive(resultSet.getBoolean(Constants.ACTIVE_COL));
        } catch (Exception e) {
            LOGGER.error("Parsing resultSet to Size error", e);
            throw new DAOException(e);
        }
        return size;
    }

}
