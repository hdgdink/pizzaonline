package kz.javalab.va.dao.impl;

import kz.javalab.va.dao.AbstractDao;
import kz.javalab.va.dao.DaoException;
import kz.javalab.va.dao.DaoFactory;
import kz.javalab.va.entity.Type;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Component()
@Repository
@ComponentScan("kz.javalab.va")
@Qualifier("typeDao")
public class TypeDao extends AbstractDao<Integer, Type> implements Dao<Type> {
    private static final Logger logger = Logger.getRootLogger();

    @Autowired
    private DaoFactory daoFactory;

    private static final String GET_TYPE_NAME_BY_ID = "SELECT TYPE FROM TYPE WHERE ID = ?;";
    private static final String FIND_ALL_TYPES = "SELECT * FROM TYPE;";
    private static final String UPDATE_TYPE = "UPDATE TYPE SET TYPE = ?, ACTIVE = ?  WHERE ID = ?;";
    private static final String CREATE_TYPE = "INSERT INTO TYPE ( TYPE, ACTIVE) VALUES(?, ?);";

    public TypeDao(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    public TypeDao() {
    }

    public String getNameById(Integer id) throws DaoException {
        String name = null;
        Connection connection = daoFactory.getConnection();
        ResultSet resultSet = null;

        try (PreparedStatement statement = connection.prepareStatement(GET_TYPE_NAME_BY_ID)) {
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                name = resultSet.getString("TYPE");
            }
        } catch (Exception e) {
            logger.warn("Statement cannot be created.", e);
            throw new DaoException(e);
        } finally {
            daoFactory.closeResultSet(resultSet, connection);
        }
        return name;
    }

    @Override
    public void statementForCreate(PreparedStatement statement, Type entity) throws DaoException {
        try {
            statement.setString(1, entity.getType());
            statement.setBoolean(2, entity.getActive());
        } catch (Exception e) {
            logger.error("Preparing statement for Create Type error", e);
            throw new DaoException(e);
        }
    }

    @Override
    public void statementForUpdate(PreparedStatement statement, Type entity) throws DaoException {
        try {
            statementForCreate(statement, entity);
            statement.setInt(3, entity.getId());
        } catch (Exception e) {
            logger.error("Preparing statement for Update Type error", e);
            throw new DaoException(e);
        }
    }

    @Override
    public Type parseResultSetInstance(ResultSet resultSet) throws DaoException {
        Type type = new Type();

        try {
            type.setId(resultSet.getInt("ID"));
            type.setType(resultSet.getString("TYPE"));
            type.setActive(resultSet.getBoolean("ACTIVE"));
        } catch (Exception e) {
            logger.error("Error of results parsing", e);
            throw new DaoException(e);
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