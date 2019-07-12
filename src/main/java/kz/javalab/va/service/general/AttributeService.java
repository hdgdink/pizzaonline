package kz.javalab.va.service.general;

import kz.javalab.va.dao.DaoException;
import kz.javalab.va.dao.impl.FoodDao;
import kz.javalab.va.dao.impl.SizeDao;
import kz.javalab.va.dao.impl.TypeDao;
import kz.javalab.va.entity.order.Status;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
@ComponentScan
public class AttributeService {
    @Autowired
    private FoodDao foodDao;
    @Autowired
    private TypeDao typeDao;
    @Autowired
    private SizeDao sizeDao;
    private static final Logger logger = Logger.getRootLogger();

    public AttributeService(FoodDao foodDao, TypeDao typeDao, SizeDao sizeDao) {
        this.foodDao = foodDao;
        this.typeDao = typeDao;
        this.sizeDao = sizeDao;
    }

    public AttributeService() {
    }


    public Map<String, List> getAttrList() {
        Map<String, List> attributes = new HashMap<>();

        try {
            attributes.put("StatusList", Arrays.asList(Status.values()));
            attributes.put("TypeList", typeDao.getAll());

            attributes.put("PizzaList", foodDao.getAllByTypeId(1));
            attributes.put("BevList", foodDao.getAllByTypeId(3));
            attributes.put("SubList", foodDao.getAllByTypeId(2));
            attributes.put("SizeList", sizeDao.getAllByActive(true));
        } catch (DaoException e) {
            e.printStackTrace();
            logger.error("Error of connection to data base", e);
        }

        return attributes;
    }
}