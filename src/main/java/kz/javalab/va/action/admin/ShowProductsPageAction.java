package kz.javalab.va.action.admin;

import kz.javalab.va.action.Action;
import kz.javalab.va.action.ActionException;
import kz.javalab.va.action.ActionResult;
import kz.javalab.va.connection.pool.ConnectionPoolException;
import kz.javalab.va.dao.DAOException;
import kz.javalab.va.dao.impl.FoodDao;
import kz.javalab.va.dao.impl.TypeDao;
import kz.javalab.va.entity.Food;
import kz.javalab.va.entity.Type;
import kz.javalab.va.util.Constants;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class ShowProductsPageAction implements Action {
    private static final Logger LOGGER = Logger.getLogger(ShowProductsPageAction.class);

    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {
        HttpSession session = request.getSession();
        try {
            List<Food> productsList = new FoodDao().getAll();
            List<Type> types = new TypeDao().getAll();
            session.setAttribute(Constants.ATTRIBUTE_TYPE_LIST, types);
            session.setAttribute(Constants.ATTRIBUTE_PRODUCT_LIST, productsList);
        } catch (DAOException | ConnectionPoolException e) {
            LOGGER.error("Error of creating ProductList", e);
            throw new ActionException(e);
        }
        return new ActionResult(ActionResult.METHOD.FORWARD, Constants.ACTION_ADMIN_PRODUCTS);
    }
}

