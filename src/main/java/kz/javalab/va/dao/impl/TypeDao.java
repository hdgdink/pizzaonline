package kz.javalab.va.dao.impl;

import kz.javalab.va.connection.pool.ConnectionPool;
import kz.javalab.va.connection.pool.ConnectionPoolException;
import kz.javalab.va.dao.AbstractDao;
import kz.javalab.va.dao.DAOException;
import kz.javalab.va.dao.DaoFactory;
import kz.javalab.va.entity.Type;
import kz.javalab.va.util.Constants;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class TypeDao extends AbstractDao<Integer, Type> {
    private static final Logger LOGGER = Logger.getLogger(TypeDao.class);
    private final ConnectionPool pool = ConnectionPool.getInstance();
    private DaoFactory daoFactory = new DaoFactory();
    private static final String GET_TYPE_NAME_BY_ID = "SELECT TYPE FROM TYPE WHERE ID = ?;";
    private static final String FIND_ALL_TYPES = "SELECT * FROM TYPE ";
    private static final String UPDATE_TYPE = "UPDATE TYPE SET TYPE = ?, ACTIVE = ?  WHERE ID = ?;";
    private static final String CREATE_TYPE = "INSERT INTO TYPE ( TYPE, ACTIVE) VALUES(?, ?);";

    public TypeDao() throws ConnectionPoolException {
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

    @Override
    public void statementForCreate(PreparedStatement statement, Type entity) throws DAOException {
        try {
            statement.setString(1, entity.getType());
            statement.setBoolean(2, entity.getActive());
        } catch (Exception e) {
            LOGGER.error("Preparing statement for Create Type error", e);
            throw new DAOException(e);
        }
    }

    @Override
    public void statementForUpdate(PreparedStatement statement, Type entity) throws DAOException {
        try {
            statementForCreate(statement, entity);
            statement.setInt(3, entity.getId());
        } catch (Exception e) {
            LOGGER.error("Preparing statement for Update Type error", e);
            throw new DAOException(e);
        }
    }

    @Override
    public Type parseResultSetInstance(ResultSet resultSet) throws DAOException {
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

    @Override
    public String getReadQuery() {
        return FIND_ALL_TYPES;
    }

    @Override
    public String getCreateQuery() {
        return CREATE_TYPE;
    }

    @Override
    public String getUpdateQuery() {
        return UPDATE_TYPE;
    }


}
