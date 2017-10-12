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

/**
 * Created by HdgDink} on 12.10.2017.
 */
public class ShowProductsPageAction implements Action {


    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {
        HttpSession session = request.getSession();

        try {
            List<Food> productsList = new FoodDao().getAll();
            List<Type> types = new TypeDao().getAll();
                        session.setAttribute("types", types);
            session.setAttribute("products_list", productsList);
        } catch (DAOException e) {
            e.printStackTrace();
        } catch (ConnectionPoolException e) {
            e.printStackTrace();
        }

        return new ActionResult(ActionResult.METHOD.FORWARD, "admin_products");
    }
}

