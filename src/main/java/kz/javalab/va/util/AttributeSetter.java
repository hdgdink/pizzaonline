package kz.javalab.va.util;

import kz.javalab.va.connection.pool.ConnectionPoolException;
import kz.javalab.va.dao.DAOException;
import kz.javalab.va.dao.impl.FoodDao;
import kz.javalab.va.dao.impl.SizeDao;
import kz.javalab.va.entity.Food;
import kz.javalab.va.entity.Size;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Locale;

public class AttributeSetter {

    public void setAttributes(HttpSession session) {
        ServletContext context = session.getServletContext();

        try {
            List<Food> pizzaList = new FoodDao().getAllByTypeId(0);
            List<Food> subList = new FoodDao().getAllByTypeId(1);
            List<Food> bevList = new FoodDao().getAllByTypeId(2);
            List<Size> sizeList = new SizeDao().getAll();
            context.setAttribute("sizeList", sizeList);
            context.setAttribute("pizzaList", pizzaList);
            context.setAttribute("subList", subList);
            context.setAttribute("bevList", bevList);
        } catch (DAOException e) {
            e.printStackTrace();
        } catch (ConnectionPoolException e) {
            e.printStackTrace();
        }
    }

}
