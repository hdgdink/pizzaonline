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
import java.util.ArrayList;
import java.util.List;

public class SizeDao extends AbstractDao<Integer, Size> {
    private static final Logger LOGGER = Logger.getLogger(SizeDao.class);
    private final ConnectionPool pool = ConnectionPool.getInstance();
    private DaoFactory daoFactory = new DaoFactory();
    private static final String UPDATE_SIZE = "UPDATE SIZE SET SIZE = ?, NAME = ?, ACTIVE = ?  WHERE ID = ?;";
    private static final String CREATE_SIZE = "INSERT INTO SIZE ( SIZE, NAME, ACTIVE) VALUES(?, ?, ?);";
    private static final String FIND_ALL_SIZES = "SELECT * FROM SIZE ";
    private static final String GET_SIZE_BY_VALUE = "SELECT * FROM SIZE WHERE SIZE = ?;";
    private static final String GET_ALL_SIZES_BY_ACTIVE = "SELECT * FROM SIZE WHERE ACTIVE = ?;";

    public SizeDao() throws ConnectionPoolException {
    }


    @Override
    public String getReadQuery() {
        return FIND_ALL_SIZES;
    }

    @Override
    public String getCreateQuery() {
        return CREATE_SIZE;
    }

    public Size getByValue(Integer value) throws DAOException {
        LOGGER.info("SizeDao.getByValue()");
        Size size = null;
        Connection connection = daoFactory.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(GET_SIZE_BY_VALUE)) {
            statement.setInt(1, value);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                size = parseResultSetInstance(resultSet);
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
                Size size = parseResultSetInstance(resultSet);
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

    @Override
    public void statementForCreate(PreparedStatement statement, Size entity) throws DAOException {
        try {
            statement.setInt(1, entity.getSize());
            statement.setString(2, entity.getName());
            statement.setBoolean(3, entity.getActive());
        } catch (Exception e) {
            LOGGER.error("Preparing statement for Create Size error", e);
            throw new DAOException(e);
        }
    }

    @Override
    public void statementForUpdate(PreparedStatement statement, Size entity) throws DAOException {
        try {
            statementForCreate(statement, entity);
            statement.setInt(4, entity.getId());
        } catch (Exception e) {
            LOGGER.error("Preparing statement for Update Size error", e);
            throw new DAOException(e);
        }
    }

    @Override
    public Size parseResultSetInstance(ResultSet resultSet) throws DAOException {
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

    @Override
    public String getUpdateQuery() {
        return UPDATE_SIZE;
    }

}
