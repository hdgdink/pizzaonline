package kz.javalab.va.dao.impl;

import kz.javalab.va.connection.pool.ConnectionPool;
import kz.javalab.va.connection.pool.ConnectionPoolException;
import kz.javalab.va.dao.AbstractDao;
import kz.javalab.va.dao.DAOException;
import kz.javalab.va.entity.Food;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class FoodDao extends AbstractDao<Integer, Food> {
    private static final Logger LOGGER = Logger.getLogger(FoodDao.class);
    private final ConnectionPool pool = ConnectionPool.getInstance();
    private static final String GET_FOOD_BY_ID = "SELECT * FROM FOOD WHERE ID = ?;";
    private static final String FOOD_FIND_ALL = "SELECT * FROM FOOD ";
    private static final String FOOD_CREATE = "INSERT INTO FOOD(TYPE_ID,NAME_RU, NAME_EN, COMPOS_RU, COMPOS_EN, PRICE," +
            " IMG, ACTIVE) VALUES(?, ?, ?, ?, ?, ?, ?, ?);";
    private static final String GET_ALL_FOOD_BY_TYPE = "SELECT * FROM FOOD WHERE TYPE_ID = ?;";
    private static final String FOOD_UPDATE = "UPDATE FOOD SET TYPE_ID = ?, NAME_RU = ?, NAME_EN = ?, COMPOS_RU = ?," +
            " COMPOS_EN = ?,PRICE = ?,  IMG = ?, ACTIVE = ?  WHERE ID = ?;";
    private static final String DELETE_BY_ID = "DELETE FROM FOOD WHERE ID=?;";

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
                food.setPrice(resultSet.getInt("PRICE"));
                food.setImg(resultSet.getString("IMG"));
                food.setActive(resultSet.getBoolean("ACTIVE"));
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
        LOGGER.info("FoodDao.getById()");
        Food food = null;
        Connection connection = null;
        try {
            connection = pool.getConnection();
            LOGGER.debug("Connection has been taken.");
        } catch (ConnectionPoolException e) {
            LOGGER.warn("Connection cannot be taken.", e);
            e.printStackTrace();
        }
        try (PreparedStatement statement = connection.prepareStatement(GET_FOOD_BY_ID)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                food = new Food();
                food.setId(resultSet.getInt("ID"));
                food.setTypeId(resultSet.getInt("TYPE_ID"));
                food.setNameRu(resultSet.getString("NAME_RU"));
                food.setNameEn(resultSet.getString("NAME_EN"));
                food.setDiscriptionRu(resultSet.getString("COMPOS_RU"));
                food.setDiscriptionEn(resultSet.getString("COMPOS_EN"));
                food.setPrice(resultSet.getInt("PRICE"));
                food.setImg(resultSet.getString("IMG"));
                food.setActive(resultSet.getBoolean("ACTIVE"));
            }
        } catch (Exception e) {
            LOGGER.warn("Statement cannot be created.", e);
            throw new DAOException(e);
        } finally {
            pool.returnConnection(connection);
        }
        return food;
    }

    @Override
    public void delete(Integer id) throws DAOException {
        throw new DAOException("Unsupported operation.");

    }

    @Override
    public void delete(Food entity) throws DAOException {
        throw new DAOException("Unsupported operation.");
    }

    @Override
    public int create(Food entity) throws DAOException {
        LOGGER.info("UserDao.createUser()");
        Connection connection = null;
        try {
            connection = pool.getConnection();
            LOGGER.debug("Connection has been taken.");
        } catch (ConnectionPoolException e) {
            LOGGER.warn("Connection cannot be taken.", e);
            e.printStackTrace();
        }
        try (PreparedStatement statement = connection.prepareStatement(FOOD_CREATE)) {
            statement.setInt(1, entity.getTypeId());
            statement.setString(2, entity.getNameRu());
            statement.setString(3, entity.getNameEn());
            statement.setString(4, entity.getDiscriptionRu());
            statement.setString(5, entity.getDiscriptionEn());
            statement.setInt(6, entity.getPrice());
            statement.setString(7, entity.getImg());
            statement.setBoolean(8, entity.isActive());
            return statement.executeUpdate();
        } catch (Exception e) {
            LOGGER.warn("Statement cannot be created.", e);
            throw new DAOException(e);
        } finally {
            pool.returnConnection(connection);
        }
    }

    @Override
    public int update(Food entity) throws DAOException {
        LOGGER.info("FoodDao.updateFood()");
        Connection connection = null;
        try {
            connection = pool.getConnection();
            LOGGER.debug("Connection has been taken.");
        } catch (ConnectionPoolException e) {
            LOGGER.warn("Connection cannot be taken.", e);
            e.printStackTrace();
        }
        try (PreparedStatement statement = connection.prepareStatement(FOOD_UPDATE)) {
            statement.setInt(1, entity.getTypeId());
            statement.setString(2, entity.getNameRu());
            statement.setString(3, entity.getNameEn());
            statement.setString(4, entity.getDiscriptionRu());
            statement.setString(5, entity.getDiscriptionEn());
            statement.setInt(6, entity.getPrice());
            statement.setString(7, entity.getImg());
            statement.setBoolean(8, entity.isActive());
            statement.setInt(9, entity.getId());
            System.out.println("fooddao update entity:" + entity.getId() + entity.toString());
            LOGGER.debug("Statement has been created.");
            return statement.executeUpdate();
        } catch (Exception e) {
            LOGGER.warn("Statement cannot be created.", e);
            throw new DAOException(e);
        } finally {
            pool.returnConnection(connection);
        }
    }


    public List<Food> getAllByTypeId(Integer typeId) throws DAOException {
        List<Food> foods = null;
        Connection connection = null;
        try {
            connection = pool.getConnection();
        } catch (ConnectionPoolException e) {
            e.printStackTrace();
        }
        try (PreparedStatement statement = connection.prepareStatement(GET_ALL_FOOD_BY_TYPE)) {
            statement.setInt(1, typeId);
            ResultSet resultSet = statement.executeQuery();
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
                food.setPrice(resultSet.getInt("PRICE"));
                food.setImg(resultSet.getString("IMG"));
                food.setActive(resultSet.getBoolean("ACTIVE"));
                if (food.isActive())     foods.add(food);
            }
        } catch (Exception e) {
            throw new DAOException(e);
        } finally {
            pool.returnConnection(connection);
        }
        return foods;
    }


}
