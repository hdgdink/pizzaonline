package kz.javalab.va.action.admin;

import kz.javalab.va.action.Action;
import kz.javalab.va.action.ActionException;
import kz.javalab.va.action.ActionResult;
import kz.javalab.va.connection.pool.ConnectionPoolException;
import kz.javalab.va.dao.DAOException;
import kz.javalab.va.dao.impl.FoodDao;
import kz.javalab.va.entity.Food;
import kz.javalab.va.util.AttributeSetter;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class EditProductAction implements Action {
    private static final Logger LOGGER = Logger.getLogger(EditProductAction.class);
    private FoodDao dao;
    private Food food = null;
    private HttpServletRequest req;
    private HttpSession session;

    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {
        req = request;
        session = request.getSession();
        updateProduct();
        AttributeSetter setter = new AttributeSetter();
        setter.setAttributes(session);
        String referer = request.getHeader("referer");
        referer = referer.substring(referer.lastIndexOf("/") + 1, referer.length());
        return new ActionResult(ActionResult.METHOD.REDIRECT, referer);
    }


    private void updateProduct() {
        Integer id = Integer.parseInt(req.getParameter("id"));
        Integer typeId = Integer.parseInt(req.getParameter("type_id"));
        String nameRu = req.getParameter("nameRu");
        String nameEn = req.getParameter("nameEn");
        String discriptionRu = req.getParameter("composRu");
        String dicriptionEn = req.getParameter("composEn");
        Integer price = Integer.parseInt(req.getParameter("price"));
        String img = req.getParameter("imgPath");
        Boolean active = Boolean.parseBoolean(req.getParameter("active"));

        try {
            food = foodDao().getById(id);
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
            LOGGER.error("Error while update", e);
            e.printStackTrace();
        }
    }


    private FoodDao foodDao() throws DAOException {
        if (dao == null) {
            try {
                dao = new FoodDao();
            } catch (ConnectionPoolException e) {
                e.printStackTrace();
            }
        }
        return dao;
    }
}

