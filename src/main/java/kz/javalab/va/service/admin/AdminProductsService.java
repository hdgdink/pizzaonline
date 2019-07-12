package kz.javalab.va.service.admin;

import kz.javalab.va.dao.DaoException;
import kz.javalab.va.dao.impl.FoodDao;
import kz.javalab.va.entity.Food;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@ComponentScan
public class AdminProductsService extends AdminService<Food> {
    private final static Logger logger = Logger.getRootLogger();
    @Autowired
    private FoodDao foodDao;

    public AdminProductsService(FoodDao foodDao) {
        this.foodDao = foodDao;
    }

    public AdminProductsService() {
    }

    public List<Food> getProductList() {
        return getList(foodDao);
    }

    public void updateProduct(Food foodModel) {
        try {
            foodDao.update(foodModel);
        } catch (DaoException e) {
            logger.error("Error during product updating");
            e.printStackTrace();
        }
    }

    public void createProduct(Food foodModel) {
        try {
            foodDao.create(foodModel);
        } catch (DaoException e) {
            logger.error("Error during product creation");
            e.printStackTrace();
        }
    }
}
