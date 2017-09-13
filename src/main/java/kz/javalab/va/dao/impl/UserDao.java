package kz.javalab.va.dao.impl;

import kz.javalab.va.connection.pool.ConnectionPool;
import kz.javalab.va.dao.AbstractDao;
import kz.javalab.va.dao.DaoException;
import kz.javalab.va.entity.user.Role;
import kz.javalab.va.entity.user.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class UserDao extends AbstractDao<User, Integer> {

    public UserDao(ConnectionPool connectionPool) {
        super(connectionPool);
    }

    @Override
    public String getSelectQuery() {
        return "SELECT id, firstname, lastname, username, email, password, balance, role FROM USER";
    }

    @Override
    public String getCreateQuery() {
        return "INSERT INTO USER (firstname, lastname, username, email, password, balance, role)" + "VALUES (?, ?, ?, ?, ?,?,?);";
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE USER SET firsname= ?, lastname= ?, username= ?, password= ?, balance= ?, role= ? WHERE id= ?;";
    }

    @Override
    public String getDeleteQuery() {
        return "DELETE FROM USER WHERE id= ?;";
    }

    @Override
    protected List<User> parseResultSet(ResultSet rs) throws DaoException {
        LinkedList<User> users = new LinkedList<User>();
        User user = new User();
        try {
            while (rs.next()) {
                user.setId(rs.getInt("id"));
                user.setFirstname(rs.getString("firstname"));
                user.setLastname(rs.getString("lastname"));
                user.setUsername(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setBalance(rs.getInt("balance"));
                user.setRole(Role.valueOf(rs.getString("role")));
                users.add(user);
            }
        } catch (Exception e) {
            throw new DaoException("ERROR while parsing resultSet to user", e);
        }
        return users;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, User user) throws DaoException {
        try {
            statement.setString(1, user.getFirstname());
            statement.setString(2, user.getLastname());
            statement.setString(3, user.getUsername());
            statement.setString(4, user.getEmail());
            statement.setString(5, user.getPassword());
            statement.setString(6, String.valueOf(user.getBalance()));
            statement.setString(7, String.valueOf(user.getRole()));
        } catch (Exception e) {
            throw new DaoException("ERROR of preparing statement for insert user", e);
        }
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, User user) throws DaoException {

        try {
            statement.setString(1, user.getFirstname());
            statement.setString(2, user.getLastname());
            statement.setString(3, user.getUsername());
            statement.setString(4, user.getEmail());
            statement.setString(5, user.getPassword());
            statement.setString(6, String.valueOf(user.getBalance()));
            statement.setString(7, String.valueOf(user.getRole()));
            statement.setString(6, String.valueOf(user.getId()));
        } catch (Exception e) {
            throw new DaoException("ERROR of preparing statement for update user", e);
        }

    }

    @Override
    public User create() throws DaoException {
        User user = new User();
        return persist(user);
    }
}
