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
    private static final String ID = "id";
    private static final String SUM_OF_ORDER = "sumOfOrder";
    private static final String USER_ID = "userId";
    private static final String ADDRESS = "address";
    private static final String PHONE = "phone";
    private static final String STATUS = "status";
    private static final String ORDERS = "orders";
    private OrderDao dao;
    private Order order = null;

    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {
        HttpSession session = request.getSession();
        Integer id = Integer.parseInt(request.getParameter(ID));
        Integer sumOfOrder = Integer.parseInt(request.getParameter(SUM_OF_ORDER));
        Integer userId = Integer.parseInt(request.getParameter(USER_ID));
        String address = request.getParameter(ADDRESS);
        String phone = request.getParameter(PHONE);
        Status status = Status.valueOf(request.getParameter(STATUS));
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
        return new ActionResult(ActionResult.METHOD.REDIRECT, ORDERS);
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

