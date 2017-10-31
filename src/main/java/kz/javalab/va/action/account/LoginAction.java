package kz.javalab.va.action.account;

import kz.javalab.va.action.Action;
import kz.javalab.va.action.ActionException;
import kz.javalab.va.action.ActionResult;
import kz.javalab.va.connection.pool.ConnectionPoolException;
import kz.javalab.va.dao.DAOException;
import kz.javalab.va.dao.impl.OrderDao;
import kz.javalab.va.dao.impl.OrderDetailsDao;
import kz.javalab.va.dao.impl.UserDao;
import kz.javalab.va.entity.OrderDetails;
import kz.javalab.va.entity.order.Order;
import kz.javalab.va.entity.order.Status;
import kz.javalab.va.entity.user.Role;
import kz.javalab.va.entity.user.User;
import kz.javalab.va.util.Constants;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;


public class LoginAction implements Action {
    private static final Logger LOGGER = Logger.getLogger(LoginAction.class);

    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {
        ActionResult result;
        HttpSession session = request.getSession();
        UserDao userDao;
        OrderDao orderDao;
        Order order = null;
        OrderDetailsDao orderDetailsDao;
        List<OrderDetails> orderDetails;
        try {
            orderDao = new OrderDao();
            userDao = new UserDao();
            orderDetailsDao = new OrderDetailsDao();
            orderDetails = new ArrayList<>();
        } catch (ConnectionPoolException e) {
            LOGGER.error("Error of connect to database.", e);
            throw new ActionException(e);
        }

        String username = request.getParameter(Constants.ATTRIBUTE_USERNAME);
        String password = request.getParameter(Constants.ATTRIBUTE_PASSWORD);
        User user;
        try {
            user = userDao.getByUsername(username);
        } catch (DAOException e) {
            LOGGER.warn("Request cannot be executed.", e);
            throw new ActionException(e);
        }
        if (user == null) {
            session.setAttribute(Constants.ATTRIBUTE_ERROR, Constants.ACCOUNT_NOT_FOUND_ERROR);
            LOGGER.debug("Wrong username.");
            result = new ActionResult(ActionResult.METHOD.FORWARD, Constants.PAGE_PIZZA_UNLOG);
            return result;
        }
        if (!password.equals(user.getPassword())) {
            session.setAttribute(Constants.ATTRIBUTE_ERROR, Constants.ACCOUNT_IS_BAD_ERROR);
            LOGGER.debug("Wrong password. " + password);
            result = new ActionResult(ActionResult.METHOD.FORWARD, Constants.PAGE_PIZZA_UNLOG);
            return result;
        }
        if (user.getRole().equals(Role.ADMIN))
            result = new ActionResult(ActionResult.METHOD.REDIRECT, Constants.ACTION_CABINET);
        else if (user.getRole().equals(Role.UNREGISTERED_USER))
            result = new ActionResult(ActionResult.METHOD.FORWARD, Constants.PAGE_PIZZA_UNLOG);
        else {
            try {
                Integer orderId = orderDao.getByUserId(user.getId());
                if (orderId != 0) {
                    order = orderDao.getById(orderId);
                    orderDetails = orderDetailsDao.getAllByOrderId(order.getId());
                }
                if (order != null && order.getStatus().equals(Status.UNPAID)) {
                    session.setAttribute(Constants.ATTRIBUTE_ORDER_DETAILS, orderDetails);
                    session.setAttribute(Constants.ATTRIBUTE_ORDER, order);
                }
            } catch (Exception e) {
                LOGGER.error("Error at LoginAction while performing last unpaid order", e);
                throw new ActionException(e);
            }
            result = new ActionResult(ActionResult.METHOD.REDIRECT, Constants.PAGE_PIZZA_LOGGED);
        }
        session.setAttribute(Constants.ATTRIBUTE_USER, user);
        session.setAttribute(Constants.ATTRIBUTE_ID, user.getId());
        session.removeAttribute(Constants.ATTRIBUTE_EMAIL);
        session.removeAttribute(Constants.ATTRIBUTE_ERROR);
        LOGGER.debug("User " + user.getEmail() + " has been logged.");
        return result;
    }
}