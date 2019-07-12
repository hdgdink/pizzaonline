package kz.javalab.va.dao.impl;

import kz.javalab.va.dao.AbstractDao;
import kz.javalab.va.dao.DaoException;
import kz.javalab.va.dao.DaoFactory;
import kz.javalab.va.entity.Size;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Component()
@Repository
@ComponentScan("kz.javalab.va")
@Qualifier("sizeDao")
public class SizeDao extends AbstractDao<Integer, Size> implements Dao<Size> {
    private static final String STATEMENT_CREATE_ERROR = "Statement cannot be created.";
    private static final Logger logger = Logger.getRootLogger();

    @Autowired
    private DaoFactory daoFactory;

    private static final String UPDATE_SIZE = "UPDATE PROD_SIZE SET SIZE_VAL = ?, NAME = ?, ACTIVE = ?  WHERE ID = ?;";
    private static final String CREATE_SIZE = "INSERT INTO PROD_SIZE ( SIZE_VAL, NAME, ACTIVE) VALUES(?, ?, ?);";
    private static final String FIND_ALL_SIZES = "SELECT * FROM PROD_SIZE ";
    private static final String GET_SIZE_BY_VALUE = "SELECT * FROM PROD_SIZE WHERE SIZE_VAL = ?;";
    private static final String GET_ALL_SIZES_BY_ACTIVE = "SELECT * FROM PROD_SIZE WHERE ACTIVE = ?;";

    public SizeDao(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    public SizeDao() {
    }


    @Override
    public String getReadQuery() {
        return FIND_ALL_SIZES;
    }

    @Override
    public String getCreateQuery() {
        return CREATE_SIZE;
    }

    public Size getByValue(Integer value) {
        Size size = null;
        Connection connection = daoFactory.getConnection();
        ResultSet resultSet = null;

        try (PreparedStatement statement = connection.prepareStatement(GET_SIZE_BY_VALUE)) {
            statement.setInt(1, value);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                size = parseResultSetInstance(resultSet);
            }
        } catch (Exception e) {
            logger.error(STATEMENT_CREATE_ERROR, e);
        } finally {
            daoFactory.closeResultSet(resultSet, connection);
        }

        return size;
    }

    public List<Size> getAllByActive(Boolean active) throws DaoException {
        List<Size> sizeList = new ArrayList<>();
        Connection connection = daoFactory.getConnection();
        ResultSet resultSet = null;

        try (PreparedStatement statement = connection.prepareStatement(GET_ALL_SIZES_BY_ACTIVE)) {
            statement.setBoolean(1, active);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Size size = parseResultSetInstance(resultSet);

                if (size.getActive())
                    sizeList.add(size);
            }
        } catch (Exception e) {
            logger.error(STATEMENT_CREATE_ERROR, e);
            throw new DaoException(e);
        } finally {
            daoFactory.closeResultSet(resultSet, connection);
        }

        return sizeList;
    }

    @Override
    public void statementForCreate(PreparedStatement statement, Size entity) throws DaoException {
        try {
            statement.setInt(1, entity.getSize());
            statement.setString(2, entity.getName());
            statement.setBoolean(3, entity.getActive());
        } catch (Exception e) {
            logger.error("Preparing statement for Create Size error", e);
            throw new DaoException(e);
        }
    }

    @Override
    public void statementForUpdate(PreparedStatement statement, Size entity) throws DaoException {
        try {
            statementForCreate(statement, entity);
            statement.setInt(4, entity.getId());
        } catch (Exception e) {
            logger.error("Preparing statement for Update Size error", e);
            throw new DaoException(e);
        }
    }

    @Override
    public Size parseResultSetInstance(ResultSet resultSet) throws DaoException {
        Size size = new Size();

        try {
            size.setId(resultSet.getInt("ID"));
            size.setSize(resultSet.getInt("SIZE_VAL"));
            size.setName(resultSet.getString("NAME"));
            size.setActive(resultSet.getBoolean("ACTIVE"));
        } catch (Exception e) {
            logger.error("Error of results parsing", e);
            throw new DaoException(e);
        }
        return size;
    }

    @Override
    public String getUpdateQuery() {
        return UPDATE_SIZE;
    }
}