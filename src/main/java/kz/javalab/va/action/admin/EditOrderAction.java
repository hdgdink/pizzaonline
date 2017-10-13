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
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class EditOrderAction implements Action {
    private static final Logger LOGGER = Logger.getLogger(EditTypeAction.class);
    private OrderDao dao;
    private Order order = null;

    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {
        HttpSession session = request.getSession();
        Integer id = Integer.parseInt(request.getParameter("id"));
        Integer sumOfOrder = Integer.parseInt(request.getParameter("sumOfOrder"));
        Integer userId = Integer.parseInt(request.getParameter("id"));
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");
        Status status = Status.valueOf(request.getParameter("status"));
        try {
            order = orderDao().getById(id);
            order.setSumOfOrder(sumOfOrder);
            order.setUserId(userId);
            order.setAddress(address);
            order.setPhone(phone);
            order.setStatus(status);
            orderDao().update(order);
            LOGGER.info("Order with Id:" + order.getId() + "was updated");
        } catch (DAOException e) {
            LOGGER.error("Order error", e);
            e.printStackTrace();
        }
        AttributeSetter setter = new AttributeSetter();
        setter.setAttributes(session);
        return new ActionResult(ActionResult.METHOD.REDIRECT, "orders");
    }

    private OrderDao orderDao() throws DAOException {
        if (dao == null) {
            try {
                dao = new OrderDao();
            } catch (ConnectionPoolException e) {
                e.printStackTrace();
            }
        }
        return dao;
    }
}

