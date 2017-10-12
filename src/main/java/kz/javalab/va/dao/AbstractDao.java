package kz.javalab.va.dao;

import kz.javalab.va.connection.pool.ConnectionPoolException;
import kz.javalab.va.entity.Entity;

import java.util.List;

public abstract class AbstractDao<K, T extends Entity> {

    public abstract List<T> getAll() throws DAOException, ConnectionPoolException;

    public abstract T getById(K id) throws DAOException;

    public abstract void  delete(K id) throws DAOException;

    public abstract  void delete(T entity) throws DAOException;

    public abstract int create(T entity) throws DAOException;

    public abstract int update(T entity) throws DAOException;

}



