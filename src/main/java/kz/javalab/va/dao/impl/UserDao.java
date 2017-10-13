package kz.javalab.va.dao.impl;

import kz.javalab.va.connection.pool.ConnectionPool;
import kz.javalab.va.connection.pool.ConnectionPoolException;
import kz.javalab.va.dao.AbstractDao;
import kz.javalab.va.dao.DAOException;
import kz.javalab.va.entity.user.Role;
import kz.javalab.va.entity.user.User;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao extends AbstractDao<Integer, User> {
    private static final Logger LOGGER = Logger.getLogger(UserDao.class);
    private final ConnectionPool pool = ConnectionPool.getInstance();
    private DaoFactory daoFactory = new DaoFactory();
    private static final String USER_CREATE = "INSERT INTO USER(FIRSTNAME, LASTNAME, USERNAME, EMAIL, PASSWORD, ROLE, " +
            "BALANCE) VALUES(?, ?, ?, ?, ?, ?, ?);";
    private static final String GET_USER_BY_USERNAME = "SELECT * FROM USER WHERE USERNAME = ?;";
    private static final String GET_USER_BY_ID = "SELECT * FROM USER WHERE ID = ?;";
    public static final String CHANGE_PASSWORD = "UPDATE USER SET PASSWORD= ? WHERE ID = ?;";
    private static final String USER_FIND_ALL = "SELECT * FROM USER;";
    private static final String USER_UPDATE = "UPDATE USER SET FIRSTNAME = ?, LASTNAME = ?, USERNAME = ?, EMAIL = ?," +
            " PASSWORD = ?, ROLE = ?, BALANCE = ?  WHERE ID = ?;";
    private static final String GET_ALL_USERS_BY_USERNAME = "SELECT * FROM USER WHERE USERNAME = ?;";

    public UserDao() throws ConnectionPoolException {
    }


    public User getByUsername(String username) throws DAOException {
        LOGGER.info("UserDao.getByUsername()");
        User user = null;
        Connection connection = daoFactory.getConnection();
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
            LOGGER.warn("Statement cannot be created.", e);
            throw new DAOException(e);
        } finally {
            pool.returnConnection(connection);
        }
        return user;
    }


    @Override
    public List<User> getAll() throws DAOException {
        LOGGER.info("UserDao.getAll()");
        List<User> users = null;
        Connection connection = daoFactory.getConnection();
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
                user.setRole(Role.valueOf(resultSet.getString("ROLE")));
                user.setBalance(resultSet.getInt("BALANCE"));
                users.add(user);
            }
        } catch (Exception e) {
            LOGGER.warn("Statement cannot be created.", e);
            throw new DAOException(e);
        } finally {
            pool.returnConnection(connection);
        }
        return users;
    }

    @Override
    public User getById(Integer id) throws DAOException {
        LOGGER.info("UserDao.getById()");
        User user = null;
        Connection connection = daoFactory.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(GET_USER_BY_ID)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.first()) {
                user = new User();
                user.setId(resultSet.getInt("id"));
                user.setFirstname(resultSet.getString("FIRSTNAME"));
                user.setLastname(resultSet.getString("LASTNAME"));
                user.setUsername(resultSet.getString("USERNAME"));
                user.setEmail(resultSet.getString("EMAIL"));
                user.setPassword(resultSet.getString("PASSWORD"));
                user.setRole(Role.valueOf(resultSet.getString("ROLE")));
                user.setBalance(resultSet.getInt("BALANCE"));
            }
        } catch (Exception e) {
            LOGGER.warn("Statement cannot be created.", e);
            throw new DAOException(e);
        } finally {
            pool.returnConnection(connection);
        }
        return user;
    }

    @Override
    public void delete(Integer id) throws DAOException {
        throw new DAOException("Unsupported operation.");
    }

    @Override
    public void delete(User entity) throws DAOException {
        throw new DAOException("Unsupported operation.");
    }

    @Override
    public int create(User entity) throws DAOException {
        LOGGER.info("UserDao.createUser()");
        Connection connection = daoFactory.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(USER_CREATE)) {
            statement.setString(1, entity.getFirstname());
            statement.setString(2, entity.getLastname());
            statement.setString(3, entity.getUsername());
            statement.setString(4, entity.getEmail());
            statement.setString(5, entity.getPassword());
            statement.setString(6, String.valueOf(entity.getRole()));
            statement.setInt(7, entity.getBalance());
            return statement.executeUpdate();
        } catch (Exception e) {
            LOGGER.warn("Statement cannot be created.", e);
            throw new DAOException(e);
        } finally {
            pool.returnConnection(connection);
        }
    }

    @Override
    public int update(User entity) throws DAOException {
        LOGGER.info("UserDao.updateUser()");
        Connection connection = daoFactory.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(USER_UPDATE)) {
            statement.setString(1, entity.getFirstname());
            statement.setString(2, entity.getLastname());
            statement.setString(3, entity.getUsername());
            statement.setString(4, entity.getEmail());
            statement.setString(5, entity.getPassword());
            statement.setString(6, String.valueOf(entity.getRole()));
            statement.setInt(7, entity.getBalance());
            statement.setInt(8, entity.getId());
            LOGGER.debug("Statement has been created.");
            return statement.executeUpdate();
        } catch (Exception e) {
            LOGGER.warn("Statement cannot be created.", e);
            throw new DAOException(e);
        } finally {
            pool.returnConnection(connection);
        }
    }

    public void resetPassword(String newPassword, Integer id) throws DAOException {
        LOGGER.info("UserDao.resetPassword()");
        Connection connection = daoFactory.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(CHANGE_PASSWORD)) {
            statement.setString(1, newPassword);
            statement.setInt(2, id);
            statement.executeUpdate();
        } catch (Exception e) {
            LOGGER.warn("Statement cannot be created.", e);
        } finally {
            pool.returnConnection(connection);
        }
    }


    public List<User> getUsersListByUsername(String username) throws DAOException {
        LOGGER.info("UserDao.getUsersListbyUserName()");
        List<User> users = null;
        Connection connection = daoFactory.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(GET_ALL_USERS_BY_USERNAME)) {
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
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
                user.setRole(Role.valueOf(resultSet.getString("ROLE")));
                user.setBalance(resultSet.getInt("BALANCE"));
                users.add(user);
            }
        } catch (Exception e) {
            LOGGER.warn("Statement cannot be created.", e);
            throw new DAOException(e);
        } finally {
            pool.returnConnection(connection);
        }
        return users;
    }
}
