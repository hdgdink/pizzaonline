package kz.javalab.va.action.admin;

import kz.javalab.va.action.Action;
import kz.javalab.va.action.ActionException;
import kz.javalab.va.action.ActionResult;
import kz.javalab.va.connection.pool.ConnectionPoolException;
import kz.javalab.va.dao.DAOException;
import kz.javalab.va.dao.impl.FoodDao;
import kz.javalab.va.entity.Food;
import kz.javalab.va.util.AttributeSetter;
import kz.javalab.va.util.Constants;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class EditProductAction implements Action {
    private static final Logger LOGGER = Logger.getLogger(EditProductAction.class);
    private FoodDao dao;
    private HttpServletRequest req;

    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {
        req = request;
        HttpSession session = request.getSession();
        updateProduct();
        AttributeSetter setter = new AttributeSetter();
        setter.setAttributes(session);
        String referer = request.getHeader(Constants.PAGE_REFERER);
        referer = referer.substring(referer.lastIndexOf("/") + 1, referer.length());
        return new ActionResult(ActionResult.METHOD.REDIRECT, referer);
    }

    private void updateProduct() throws ActionException {
        Integer id = Integer.parseInt(req.getParameter(Constants.ATTRIBUTE_ID));
        Integer typeId = Integer.parseInt(req.getParameter(Constants.ATTRIBUTE_TYPE_ID));
        String nameRu = req.getParameter(Constants.ATTRIBUTE_NAME_RU);
        String nameEn = req.getParameter(Constants.ATTRIBUTE_NAME_EN);
        String discriptionRu = req.getParameter(Constants.ATTRIBUTE_COMPOS_RU);
        String dicriptionEn = req.getParameter(Constants.ATTRIBUTE_COMPOS_EN);
        Integer price = Integer.parseInt(req.getParameter(Constants.ATTRIBUTE_PRICE));
        String img = req.getParameter(Constants.ATTRIBUTE_IMG_PATH);
        Boolean active = Boolean.parseBoolean(req.getParameter(Constants.ATTRIBUTE_ACTIVE));
        try {
            Food food = foodDao().getById(id);
            food.setPrice(price);
            food.setImg(img);
            food.setDiscriptionEn(dicriptionEn);
            food.setDiscriptionRu(discriptionRu);
            food.setNameEn(nameEn);
            food.setNameRu(nameRu);
            food.setTypeId(typeId);
            food.setActive(active);
            foodDao().update(food);
            LOGGER.info("Product was update " + food.getId());
        } catch (DAOException e) {
            LOGGER.error("Error while update product", e);
            throw new ActionException(e);
        }
    }

    private FoodDao foodDao() throws DAOException {
        if (dao == null) {
            try {
                dao = new FoodDao();
            } catch (ConnectionPoolException e) {
                LOGGER.error("Error of initialization OrderDao", e);
            }
        }
        return dao;
    }
}

