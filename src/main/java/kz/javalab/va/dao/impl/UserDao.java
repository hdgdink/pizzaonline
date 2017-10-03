package kz.javalab.va.dao.impl;

import kz.javalab.va.connection.pool.ConnectionPool;
import kz.javalab.va.connection.pool.ConnectionPoolException;
import kz.javalab.va.dao.AbstractDao;
import kz.javalab.va.dao.DAOException;
import kz.javalab.va.entity.user.Role;
import kz.javalab.va.entity.user.User;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDao extends AbstractDao<Integer, User> {
    private static final Logger LOGGER = Logger.getLogger(UserDao.class);
    private final ConnectionPool pool = ConnectionPool.getInstance();

    private static final String USER_CREATE = "INSERT INTO USER(FIRSTNAME, LASTNAME, USERNAME, EMAIL, PASSWORD, ROLE, " +
            "BALANCE) VALUES(?, ?, ?, ?, ?, ?, ?);";
    private static final String GET_USER_BY_USERNAME = "SELECT * FROM USER WHERE USERNAME = ?;";
    private static final String GET_USER_BY_ID = "SELECT * FROM USER WHERE ID = ?;";
    private static final String GET_USER_BY_UUID = "SELECT * FROM USER WHERE remember_uuid = ?;";
    private static final String GET_USER_BY_STATUS = "SELECT * FROM USER WHERE account_status = ?;";
    private static final String USER_FIND_ALL = "SELECT * FROM USER;";
    private static final String USER_UPDATE = "UPDATE USER SET FIRSTNAME = ?, LASTNAME = ?, USERNAME = ?, EMAIL = ?," +
            " PASSWORD = ?, ROLE = ?, BALANCE = ?  WHERE ID = ?;";

    public UserDao() throws ConnectionPoolException {
    }


    public User getByUsername(String username) throws DAOException {
        User user = null;
        Connection connection = null;
        try {
            connection = pool.getConnection();
        } catch (ConnectionPoolException e) {
            e.printStackTrace();
        }
        try (PreparedStatement statement = connection.prepareStatement(GET_USER_BY_USERNAME)) {
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.first()) {
                user = new User();
                user.setId(resultSet.getInt("id"));
                user.setFirstname(resultSet.getString("firstname"));
                user.setLastname(resultSet.getString("lastname"));
                user.setUsername(resultSet.getString("username"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setRole(Role.valueOf(resultSet.getString("role")));
                user.setBalance(resultSet.getInt("balance"));
            }
        } catch (Exception e) {
            throw new DAOException(e);
        } finally {
            pool.returnConnection(connection);
        }
        return user;
    }


    @Override
    public List<User> getAll() throws DAOException {
        List<User> users = null;
        Connection connection = null;
        try {
            connection = pool.getConnection();
        } catch (ConnectionPoolException e) {
            e.printStackTrace();
        }
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(USER_FIND_ALL);
            while (resultSet.next()) {
                if (users == null) {
                    users = new ArrayList<>();
                }
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setFirstname(resultSet.getString("FIRSTNAME"));
                user.setLastname(resultSet.getString("LASTNAME"));
                user.setUsername(resultSet.getString("USERNAME"));
                user.setEmail(resultSet.getString("EMAIL"));
                user.setPassword(resultSet.getString("PASSWORD"));
                user.setRole(Role.valueOf("ROLE"));
                user.setBalance(resultSet.getInt("BALANCE"));
                users.add(user);
            }
        } catch (Exception e) {
            throw new DAOException(e);
        } finally {
            pool.returnConnection(connection);
        }
        return users;
    }

    @Override
    public User getById(Integer id) throws DAOException {
        User user = null;
        Connection connection = null;
        try {
            connection = pool.getConnection();
        } catch (ConnectionPoolException e) {
            e.printStackTrace();
        }
        try (PreparedStatement statement = connection.prepareStatement(GET_USER_BY_ID)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.first()) {
                user.setId(resultSet.getInt("id"));
                user.setFirstname(resultSet.getString("FIRSTNAME"));
                user.setLastname(resultSet.getString("LASTNAME"));
                user.setUsername(resultSet.getString("USERNAME"));
                user.setEmail(resultSet.getString("EMAIL"));
                user.setPassword(resultSet.getString("PASSWORD"));
                user.setRole(Role.valueOf("ROLE"));
                user.setBalance(resultSet.getInt("BALANCE"));
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new DAOException(e);
        } finally {
            pool.returnConnection(connection);
        }
        return user;
    }

    @Override
    public boolean delete(Integer id) throws DAOException {
        return false;// TODO  auto-generated method stub
    }

    @Override
    public boolean delete(User entity) throws DAOException {
        return false;// TODO  auto-generated method stub
    }

    @Override
    public int create(User entity) throws DAOException {
        Connection connection = null;
        try {
            connection = pool.getConnection();
        } catch (ConnectionPoolException e) {
            e.printStackTrace();
        }
        try (PreparedStatement statement = connection.prepareStatement(USER_CREATE)) {
            entity.setRole(Role.CLIENT);
            entity.setBalance(0);
            statement.setString(1, entity.getFirstname());
            statement.setString(2, entity.getLastname());
            statement.setString(3, entity.getUsername());
            statement.setString(4, entity.getEmail());
            statement.setString(5, entity.getPassword());
            statement.setString(6, String.valueOf(entity.getRole()));
            statement.setInt(7, entity.getBalance());
            System.out.println(entity.toString());
            return statement.executeUpdate();
        } catch (Exception e) {
            throw new DAOException(e);
        } finally {
            pool.returnConnection(connection);
        }
    }

    @Override
    public int update(User entity) throws DAOException {
        Connection connection = null;
        try {
            connection = pool.getConnection();
            LOGGER.debug("Connection has been taken.");
        } catch (ConnectionPoolException e) {
            LOGGER.warn("Connection cannot be taken.", e);
            e.printStackTrace();
        }
        try (PreparedStatement statement = connection.prepareStatement(USER_UPDATE)) {
            statement.setString(1, entity.getFirstname());
            statement.setString(2, entity.getLastname());
            statement.setString(3, entity.getUsername());
            statement.setString(4, entity.getEmail());
            statement.setString(5, entity.getPassword());
            statement.setString(6, String.valueOf(entity.getRole()));
            statement.setInt(7, entity.getBalance());
            statement.setInt(8, entity.getId());
            System.out.println("userdao update entity:" +entity.getId()+ entity.toString());
            LOGGER.debug("Statement has been created.");
            return statement.executeUpdate();
        } catch (Exception e) {
            LOGGER.warn("Statement cannot be created.", e);
            throw new DAOException(e);
        } finally {
            pool.returnConnection(connection);
        }
    }

}
