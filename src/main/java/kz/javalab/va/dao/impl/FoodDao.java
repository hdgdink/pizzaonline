package kz.javalab.va.dao.impl;

import kz.javalab.va.connection.pool.ConnectionPool;
import kz.javalab.va.connection.pool.ConnectionPoolException;
import kz.javalab.va.dao.AbstractDao;
import kz.javalab.va.dao.DAOException;
import kz.javalab.va.entity.Food;
import kz.javalab.va.entity.user.Role;
import kz.javalab.va.entity.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class FoodDao extends AbstractDao<Integer, Food>  {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserDao.class);
    private final ConnectionPool pool = ConnectionPool.getInstance();
    private static final String FOOD_FIND_ALL = "SELECT * FROM FOOD;";

    public FoodDao() throws ConnectionPoolException {
    }

    @Override
    public List<Food> getAll() throws DAOException, ConnectionPoolException {
        List<Food> foods = null;
        Connection connection = null;
        try {
            connection = pool.getConnection();
        } catch (ConnectionPoolException e) {
            e.printStackTrace();
        }

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(FOOD_FIND_ALL);
            while (resultSet.next()) {
                if (foods == null) {
                    foods = new ArrayList<>();
                }
                Food food = new Food();
                food.setId(resultSet.getInt("id"));
                food.setTypeId(resultSet.getInt("TYPE_ID"));
                food.setNameRu(resultSet.getString("NAME_RU"));
                food.setNameEn(resultSet.getString("NAME_EN"));
                food.setDiscriptionRu(resultSet.getString("COMPOS_RU"));
                food.setDiscriptionEn(resultSet.getString("COMPOS_EN"));
                food.setSizeId(resultSet.getInt("SIZE_ID"));
                food.setPrice(resultSet.getInt("PRICE"));
                food.setImg(resultSet.getString("IMG"));
                foods.add(food);
            }
        } catch (Exception e) {
            throw new DAOException(e);
        } finally {
            pool.returnConnection(connection);
        }
        return foods;
    }

    @Override
    public Food getById(Integer id) throws DAOException {
        return null;
    }

    @Override
    public boolean delete(Integer id) throws DAOException {
        return false;
    }

    @Override
    public boolean delete(Food entity) throws DAOException {
        return false;
    }

    @Override
    public int create(Food entity) throws DAOException {
        return 0;
    }

    @Override
    public int update(Food entity) throws DAOException {
        return 0;
    }
}
