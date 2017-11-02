package kz.javalab.va.action.admin;

import kz.javalab.va.action.Action;
import kz.javalab.va.action.ActionException;
import kz.javalab.va.action.ActionResult;
import kz.javalab.va.connection.pool.ConnectionPoolException;
import kz.javalab.va.dao.DAOException;
import kz.javalab.va.dao.impl.OrderDao;
import kz.javalab.va.entity.order.Order;
import kz.javalab.va.util.AttributeSetter;
import kz.javalab.va.util.Constants;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class EditOrderAction implements Action {
    private static final Logger LOGGER = Logger.getLogger(EditTypeAction.class);

    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {
        OrderDao orderDao;
        HttpSession session = request.getSession();
        Integer id = Integer.parseInt(request.getParameter(Constants.ATTRIBUTE_ID));
        try {
            orderDao = new OrderDao();
            Order order = orderDao.getById(id);
            CreateEntityAdmin.setOrder(order);
            orderDao.update(order);
            LOGGER.info("Order with Id:" + order.getId() + "was updated");
        } catch (ConnectionPoolException e) {
            LOGGER.error("Error of initialization OrderDao", e);
            throw new ActionException(e);
        } catch (DAOException e) {
            LOGGER.error("Order error", e);
            throw new ActionException(e);
        }
        AttributeSetter setter = new AttributeSetter();
        setter.setAttributes(session);
        return new ActionResult(ActionResult.METHOD.REDIRECT, Constants.ACTION_ORDERS);
    }
}

