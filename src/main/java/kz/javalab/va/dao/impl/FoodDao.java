package kz.javalab.va.dao.impl;

import kz.javalab.va.dao.AbstractDao;
import kz.javalab.va.dao.DaoException;
import kz.javalab.va.dao.DaoFactory;
import kz.javalab.va.entity.Food;
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
@Qualifier("foodDao")
public class FoodDao extends AbstractDao<Integer, Food> implements Dao<Food> {
    private static final Logger logger = Logger.getRootLogger();

    @Autowired
    private DaoFactory daoFactory;

    private static final String FIND_ALL_FOOD = "SELECT * FROM FOOD ";
    private static final String CREATE_FOOD = "INSERT INTO FOOD(TYPE_ID,NAME_RU, NAME_EN, COMPOS_RU, COMPOS_EN, PRICE," +
            " IMG, ACTIVE) VALUES(?, ?, ?, ?, ?, ?, ?, ?);";
    private static final String GET_ALL_FOOD_BY_TYPE = "SELECT * FROM FOOD WHERE TYPE_ID = ?;";
    private static final String UPDATE_FOOD = "UPDATE FOOD SET TYPE_ID = ?, NAME_RU = ?, NAME_EN = ?, COMPOS_RU = ?," +
            " COMPOS_EN = ?,PRICE = ?,  IMG = ?, ACTIVE = ?  WHERE ID = ?;";


    public FoodDao(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    public FoodDao() {
    }

    public List<Food> getAllByTypeId(Integer typeId) throws DaoException {
        List<Food> foods = new ArrayList<>();
        Connection connection = daoFactory.getConnection();
        ResultSet resultSet = null;

        try (PreparedStatement statement = connection.prepareStatement(GET_ALL_FOOD_BY_TYPE)) {
            statement.setInt(1, typeId);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Food food = parseResultSetInstance(resultSet);

                if (food.isActive())
                    foods.add(food);
            }

        } catch (Exception e) {
            logger.error("Statement cannot be created.", e);
            throw new DaoException(e);
        } finally {
            daoFactory.closeResultSet(resultSet, connection);
        }

        return foods;
    }

    @Override
    public void statementForCreate(PreparedStatement statement, Food entity) throws DaoException {
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
            logger.error("Preparing statement for Create Product error", e);
            throw new DaoException(e);
        }
    }

    @Override
    public void statementForUpdate(PreparedStatement statement, Food entity) throws DaoException {
        try {
            statementForCreate(statement, entity);
            statement.setInt(9, entity.getId());
        } catch (Exception e) {
            logger.error("Preparing statement for Update Product error", e);
            throw new DaoException(e);
        }
    }


    @Override
    public Food parseResultSetInstance(ResultSet resultSet) throws DaoException {
        Food food = new Food();

        try {
            food.setId(resultSet.getInt("ID"));
            food.setTypeId(resultSet.getInt("TYPE_ID"));
            food.setNameRu(resultSet.getString("NAME_RU"));
            food.setNameEn(resultSet.getString("NAME_EN"));
            food.setDiscriptionRu(resultSet.getString("COMPOS_RU"));
            food.setDiscriptionEn(resultSet.getString("COMPOS_EN"));
            food.setPrice(resultSet.getInt("PRICE"));
            food.setImg(resultSet.getString("IMG"));
            food.setActive(resultSet.getBoolean("ACTIVE"));
        } catch (Exception e) {
            logger.error("Erro of results parsing", e);
            throw new DaoException(e);
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