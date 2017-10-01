package kz.javalab.va.dao.impl;

import kz.javalab.va.connection.pool.ConnectionPoolException;
import kz.javalab.va.dao.AbstractDao;
import kz.javalab.va.dao.DAOException;
import kz.javalab.va.entity.user.User;
import org.apache.log4j.Logger;

import java.util.List;

public class OrderDetailsDao extends AbstractDao<Integer, User>  {
    private static final Logger LOGGER = Logger.getLogger(OrderDetailsDao.class);

    @Override
    public List<User> getAll() throws DAOException, ConnectionPoolException {
        return null;
    }

    @Override
    public User getById(Integer id) throws DAOException {
        return null;
    }

    @Override
    public boolean delete(Integer id) throws DAOException {
        return false;
    }

    @Override
    public boolean delete(User entity) throws DAOException {
        return false;
    }

    @Override
    public int create(User entity) throws DAOException {
        return 0;
    }

    @Override
    public int update(User entity) throws DAOException {
        return 0;
    }
}
