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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class ShowProductsPageAction implements Action {
    private static final String TYPES = "types";
    private static final String PRODUCT_LIST = "products_list";
    private static final String ADMIN_PRODUCTS = "admin_products";

    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {
        HttpSession session = request.getSession();
        try {
            List<Food> productsList = new FoodDao().getAll();
            List<Type> types = new TypeDao().getAll();
            session.setAttribute(TYPES, types);
            session.setAttribute(PRODUCT_LIST, productsList);
        } catch (DAOException e) {
            e.printStackTrace();
        } catch (ConnectionPoolException e) {
            e.printStackTrace();
        }
        return new ActionResult(ActionResult.METHOD.FORWARD, ADMIN_PRODUCTS);
    }
}

