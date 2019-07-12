package kz.javalab.va.service.admin;

import kz.javalab.va.dao.DaoException;
import kz.javalab.va.dao.impl.TypeDao;
import kz.javalab.va.entity.Type;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@ComponentScan
public class AdminTypesService extends AdminService<Type> {
    private final static Logger logger = Logger.getRootLogger();

    @Autowired
    private TypeDao typeDao;

    public AdminTypesService(TypeDao typeDao) {
        this.typeDao = typeDao;
    }

    public AdminTypesService() {
    }

    public List<Type> getTypes() {
        return getList(typeDao);
    }

    public void editSize(Type typeModel) {
        try {
            typeDao.update(typeModel);
        } catch (DaoException e) {
            e.printStackTrace();
            logger.error("Error during type edit", e);
        }
    }

    public void createSize(Type typeModel) {
        try {
            typeDao.create(typeModel);
        } catch (DaoException e) {
            e.printStackTrace();
            logger.error("Error during type creation", e);
        }
    }
}
