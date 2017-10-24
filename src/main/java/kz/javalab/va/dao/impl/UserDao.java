package kz.javalab.va.dao.impl;

import kz.javalab.va.connection.pool.ConnectionPool;
import kz.javalab.va.connection.pool.ConnectionPoolException;
import kz.javalab.va.dao.AbstractDao;
import kz.javalab.va.dao.DAOException;
import kz.javalab.va.entity.user.Role;
import kz.javalab.va.entity.user.User;
import kz.javalab.va.util.Constants;
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
    private DaoFactory daoFactory = new DaoFactory();
    private static final String CREATE_USER = "INSERT INTO USER(FIRSTNAME, LASTNAME, USERNAME, EMAIL, PASSWORD, ROLE, " +
            "BALANCE) VALUES(?, ?, ?, ?, ?, ?, ?);";
    private static final String GET_USER_BY_USERNAME = "SELECT * FROM USER WHERE USERNAME = ?;";
    private static final String GET_USER_BY_ID = "SELECT * FROM USER WHERE ID = ?;";
    private static final String CHANGE_PASSWORD = "UPDATE USER SET PASSWORD= ? WHERE ID = ?;";
    private static final String FIND_ALL_USERS = "SELECT * FROM USER;";
    private static final String UPDATE_USER = "UPDATE USER SET FIRSTNAME = ?, LASTNAME = ?, USERNAME = ?, EMAIL = ?," +
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
                user = parseResultSet(resultSet);
            }
        } catch (Exception e) {
            LOGGER.warn(Constants.STATEMENT_CREATE_ERROR, e);
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
            ResultSet resultSet = statement.executeQuery(FIND_ALL_USERS);
            while (resultSet.next()) {
                if (users == null) {
                    users = new ArrayList<>();
                }
                User user = parseResultSet(resultSet);
                users.add(user);
            }
        } catch (Exception e) {
            LOGGER.warn(Constants.STATEMENT_CREATE_ERROR, e);
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
                user = parseResultSet(resultSet);
            }
        } catch (Exception e) {
            LOGGER.warn(Constants.STATEMENT_CREATE_ERROR, e);
            throw new DAOException(e);
        } finally {
            pool.returnConnection(connection);
        }
        return user;
    }

    @Override
    public int create(User entity) throws DAOException {
        LOGGER.info("UserDao.createUser()");
        Connection connection = daoFactory.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(CREATE_USER)) {
            statementForCreate(statement, entity);
            return statement.executeUpdate();
        } catch (Exception e) {
            LOGGER.warn(Constants.STATEMENT_CREATE_ERROR, e);
            throw new DAOException(e);
        } finally {
            pool.returnConnection(connection);
        }
    }

    @Override
    public int update(User entity) throws DAOException {
        LOGGER.info("UserDao.updateUser()");
        Connection connection = daoFactory.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_USER)) {
            statementForCreate(statement, entity);
            statement.setInt(8, entity.getId());
            return statement.executeUpdate();
        } catch (Exception e) {
            LOGGER.warn(Constants.STATEMENT_CREATE_ERROR, e);
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
            LOGGER.warn(Constants.STATEMENT_CREATE_ERROR, e);
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
                User user = parseResultSet(resultSet);
                users.add(user);
            }
        } catch (Exception e) {
            LOGGER.warn(Constants.STATEMENT_CREATE_ERROR, e);
            throw new DAOException(e);
        } finally {
            pool.returnConnection(connection);
        }
        return users;
    }

    private void statementForCreate(PreparedStatement statement, User entity) throws DAOException {
        try {
            statement.setString(1, entity.getFirstname());
            statement.setString(2, entity.getLastname());
            statement.setString(3, entity.getUsername());
            statement.setString(4, entity.getEmail());
            statement.setString(5, entity.getPassword());
            statement.setString(6, String.valueOf(entity.getRole()));
            statement.setInt(7, entity.getBalance());
        } catch (Exception e) {
            LOGGER.error("Preparing statement for User error", e);
            throw new DAOException(e);
        }
    }

    private User parseResultSet(ResultSet resultSet) throws DAOException {
        User user = new User();
        try {
            user.setId(resultSet.getInt(Constants.ID_COL));
            user.setFirstname(resultSet.getString(Constants.FIRSTNAME_COL));
            user.setLastname(resultSet.getString(Constants.LASTNAME_COL));
            user.setUsername(resultSet.getString(Constants.USERNAME_COL));
            user.setEmail(resultSet.getString(Constants.EMAIL_COL));
            user.setPassword(resultSet.getString(Constants.PASSWORD_COL));
            user.setRole(Role.valueOf(resultSet.getString(Constants.ROLE_COL)));
            user.setBalance(resultSet.getInt(Constants.BALANCE_COL));
        } catch (Exception e) {
            LOGGER.error("Parsing resultSet to User error", e);
            throw new DAOException(e);
        }
        return user;
    }
}
