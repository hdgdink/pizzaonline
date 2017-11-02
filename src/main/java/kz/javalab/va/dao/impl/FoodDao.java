package kz.javalab.va.dao.impl;

import kz.javalab.va.connection.pool.ConnectionPool;
import kz.javalab.va.connection.pool.ConnectionPoolException;
import kz.javalab.va.dao.AbstractDao;
import kz.javalab.va.dao.DAOException;
import kz.javalab.va.dao.DaoFactory;
import kz.javalab.va.entity.Food;
import kz.javalab.va.util.Constants;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class FoodDao extends AbstractDao<Integer, Food> {
    private static final Logger LOGGER = Logger.getLogger(FoodDao.class);
    private final ConnectionPool pool = ConnectionPool.getInstance();
    private DaoFactory daoFactory = new DaoFactory();
    private static final String FIND_ALL_FOOD = "SELECT * FROM FOOD ";
    private static final String CREATE_FOOD = "INSERT INTO FOOD(TYPE_ID,NAME_RU, NAME_EN, COMPOS_RU, COMPOS_EN, PRICE," +
            " IMG, ACTIVE) VALUES(?, ?, ?, ?, ?, ?, ?, ?);";
    private static final String GET_ALL_FOOD_BY_TYPE = "SELECT * FROM FOOD WHERE TYPE_ID = ?;";
    private static final String UPDATE_FOOD = "UPDATE FOOD SET TYPE_ID = ?, NAME_RU = ?, NAME_EN = ?, COMPOS_RU = ?," +
            " COMPOS_EN = ?,PRICE = ?,  IMG = ?, ACTIVE = ?  WHERE ID = ?;";

    public FoodDao() throws ConnectionPoolException {
    }

    public List<Food> getAllByTypeId(Integer typeId) throws DAOException {
        LOGGER.info("FoodDao.GetAllByTypeId()");
        List<Food> foods = null;
        Connection connection = daoFactory.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(GET_ALL_FOOD_BY_TYPE)) {
            statement.setInt(1, typeId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                if (foods == null) {
                    foods = new ArrayList<>();
                }
                Food food = parseResultSetInstance(resultSet);
                if (food.isActive())
                    foods.add(food);
            }
        } catch (Exception e) {
            LOGGER.warn(Constants.STATEMENT_CREATE_ERROR, e);
            throw new DAOException(e);
        } finally {
            pool.returnConnection(connection);
        }
        return foods;
    }

    @Override
    public void statementForCreate(PreparedStatement statement, Food entity) throws DAOException {
        try {
            statement.setInt(1, entity.getTypeId());
            statement.setString(2, entity.getNameRu());
            statement.setString(3, entity.getNameEn());
            statement.setString(4, entity.getDiscriptionRu());
            statement.setString(5, entity.getDiscriptionEn());
            statement.setInt(6, entity.getPrice());
            statement.setString(7, entity.getImg());
            statement.setBoolean(8, entity.isActive());
        } catch (Exception e) {
            LOGGER.error("Preparing statement for Create Product error", e);
            throw new DAOException(e);
        }
    }

    @Override
    public void statementForUpdate(PreparedStatement statement, Food entity) throws DAOException {
        try {
            statementForCreate(statement, entity);
            statement.setInt(9, entity.getId());
        } catch (Exception e) {
            LOGGER.error("Preparing statement for Update Product error", e);
            throw new DAOException(e);
        }
    }


    @Override
    public Food parseResultSetInstance(ResultSet resultSet) throws DAOException {
        Food food = new Food();
        try {
            food.setId(resultSet.getInt(Constants.ID_COL));
            food.setTypeId(resultSet.getInt(Constants.TYPE_ID_COL));
            food.setNameRu(resultSet.getString(Constants.NAME_RU_COL));
            food.setNameEn(resultSet.getString(Constants.NAME_EN_COL));
            food.setDiscriptionRu(resultSet.getString(Constants.COMPOS_RU_COL));
            food.setDiscriptionEn(resultSet.getString(Constants.COMPOS_EN_COL));
            food.setPrice(resultSet.getInt(Constants.PRICE_COL));
            food.setImg(resultSet.getString(Constants.IMG_COL));
            food.setActive(resultSet.getBoolean(Constants.ACTIVE_COL));
        } catch (Exception e) {
            LOGGER.error("Parsing resultSet to Product error", e);
            throw new DAOException(e);
        }
        return food;
    }

    @Override
    public String getReadQuery() {
        return FIND_ALL_FOOD;
    }

    @Override
    public String getCreateQuery() {
        return CREATE_FOOD;
    }

    @Override
    public String getUpdateQuery() {
        return UPDATE_FOOD;
    }

}
