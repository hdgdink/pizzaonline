package kz.javalab.va.service.admin;

import kz.javalab.va.dao.DaoException;
import kz.javalab.va.dao.impl.Dao;
import kz.javalab.va.entity.Entity;

import java.util.ArrayList;
import java.util.List;

public abstract class AdminService<T extends Entity> {
    private Dao<T> dao ;

    public List<T> getList(Dao dao) {
        List<T> list = new ArrayList<>();
        this.dao = dao;

        try {
            list=dao.getAll();
        } catch (DaoException e) {
            e.printStackTrace();
        }

        return list;
    }


}
