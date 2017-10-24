package kz.javalab.va.util;

import kz.javalab.va.connection.pool.ConnectionPoolException;
import kz.javalab.va.dao.DAOException;
import kz.javalab.va.dao.impl.FoodDao;
import kz.javalab.va.dao.impl.SizeDao;
import kz.javalab.va.dao.impl.TypeDao;
import kz.javalab.va.entity.Food;
import kz.javalab.va.entity.Size;
import kz.javalab.va.entity.Type;
import kz.javalab.va.entity.order.Status;
import kz.javalab.va.entity.user.Role;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.util.List;

public class AttributeSetter {

    public void setAttributes(HttpSession session) {
        ServletContext context = session.getServletContext();
        context.setAttribute(Constants.ATTRIBUTE_ROLES_LIST, Role.values());
        context.setAttribute(Constants.ATTRIBUTE_STATUS_LIST, Status.values());
        try {
            List<Food> pizzaList = new FoodDao().getAllByTypeId(0);
            List<Food> subList = new FoodDao().getAllByTypeId(1);
            List<Food> bevList = new FoodDao().getAllByTypeId(2);
            List<Size> sizeList = new SizeDao().getAllByActive(true);
            List<Type> typeList = new TypeDao().getAll();
            context.setAttribute(Constants.ATTRIBUTE_TYPE_LIST, typeList);
            context.setAttribute(Constants.ATTRIBUTE_SIZE_LIST, sizeList);
            context.setAttribute(Constants.ATTRIBUTE_PIZZA_LIST, pizzaList);
            context.setAttribute(Constants.ATTRIBUTE_SUBS_LIST, subList);
            context.setAttribute(Constants.ATTRIBUTE_BEV_LIST, bevList);
        } catch (DAOException e) {
            e.printStackTrace();
        } catch (ConnectionPoolException e) {
            e.printStackTrace();
        }
    }

}
