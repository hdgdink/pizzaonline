package kz.javalab.va.dao.impl;

import kz.javalab.va.dao.DaoException;

import kz.javalab.va.entity.Entity;

import java.util.List;

public interface Dao<T extends Entity>{

    public List<T> getAll() throws DaoException;
}
