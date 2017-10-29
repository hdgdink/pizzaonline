package kz.javalab.va.action.admin;

import kz.javalab.va.action.Action;
import kz.javalab.va.action.ActionException;
import kz.javalab.va.action.ActionResult;
import kz.javalab.va.connection.pool.ConnectionPoolException;
import kz.javalab.va.dao.DAOException;
import kz.javalab.va.dao.impl.OrderDao;
import kz.javalab.va.entity.order.Order;
import kz.javalab.va.entity.order.Status;
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
        try {
            orderDao = new OrderDao();
        } catch (ConnectionPoolException e) {
            LOGGER.error("Error of initialization OrderDao", e);
            throw new ActionException(e);
        }
        HttpSession session = request.getSession();
        Integer id = Integer.parseInt(request.getParameter(Constants.ATTRIBUTE_ID));
        Integer sumOfOrder = Integer.parseInt(request.getParameter(Constants.ATTRIBUTE_ORDER_SUM));
        Integer userId = Integer.parseInt(request.getParameter(Constants.ATTRIBUTE_USER_ID));
        String address = request.getParameter(Constants.ATTRIBUTE_ADDRESS);
        String phone = request.getParameter(Constants.ATTRIBUTE_PHONE);
        Status status = Status.valueOf(request.getParameter(Constants.ATTRIBUTE_STATUS));
        try {
            Order order = orderDao.getById(id);
            order.setSumOfOrder(sumOfOrder);
            order.setUserId(userId);
            order.setAddress(address);
            order.setPhone(phone);
            order.setStatus(status);
            orderDao.update(order);
            LOGGER.info("Order with Id:" + order.getId() + "was updated");
        } catch (DAOException e) {
            LOGGER.error("Order error", e);
            throw new ActionException(e);
        }
        AttributeSetter setter = new AttributeSetter();
        setter.setAttributes(session);
        return new ActionResult(ActionResult.METHOD.REDIRECT, Constants.ACTION_ORDERS);
    }
}

