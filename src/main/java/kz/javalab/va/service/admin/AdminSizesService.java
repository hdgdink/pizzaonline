package kz.javalab.va.service.admin;

import kz.javalab.va.dao.DaoException;
import kz.javalab.va.dao.impl.SizeDao;
import kz.javalab.va.entity.Size;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@ComponentScan
public class AdminSizesService extends AdminService<Size> {
    private final static Logger logger = Logger.getRootLogger();
    private List<Size> activeSizes = new ArrayList<>();
    @Autowired
    private SizeDao sizeDao;

    public AdminSizesService(SizeDao sizeDao) {
        this.sizeDao = sizeDao;
    }

    public AdminSizesService() {
    }

    public List<Size> getSizes() {
        return getList(sizeDao);
    }

    public List<Size> editSize(Size sizeModel) {
        try {
            sizeDao.update(sizeModel);
            activeSizes = sizeDao.getAllByActive(true);
        } catch (DaoException e) {
            e.printStackTrace();
            logger.error("Error during size edit", e);
        }


        return activeSizes;
    }

    public List<Size> createSize(Size sizeModel) {
        try {
            sizeDao.create(sizeModel);
            activeSizes=sizeDao.getAllByActive(true);
        } catch (DaoException e) {
            e.printStackTrace();
            logger.error("Error during size creation", e);
        }
        return activeSizes;
    }
}
