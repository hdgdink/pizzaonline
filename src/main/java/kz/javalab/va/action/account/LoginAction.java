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
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;


public class LoginAction implements Action {
    private static final Logger LOGGER = Logger.getLogger(LoginAction.class);
    private static final String PIZZA_UNREG = "pizza_unreg";
    private static final String CABINET = "cabinet";
    private static final String LOGIN_ERROR = "logInError";
    private static final String ACCOUNT = "account";
    private static final String NOT_FOUND = ".notFound";
    private static final String IS_BAD = ".isBad";
    private static final String ORDER_DETAILS = "order_details";
    private static final String ORDER = "order";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final String USER = "user";
    private static final String ID = "id";
    private static final String FINALPRICE = "finalPrice";
    private static final String PIZZA_LOGED = "pizza_loged";
    private static final String EMAIL = "email";
    private static final String ERROR = "error";
    private ActionResult result;
    private UserDao dao;
    private OrderDao orderDao;
    OrderDetailsDao orderDetailsDao;
    private Order order = null;
    private List<OrderDetails> orderDetails = null;

    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {
        HttpSession session = request.getSession();
        try {
            orderDao = new OrderDao();
            orderDetailsDao = new OrderDetailsDao();
            orderDetails = new ArrayList<>();
        } catch (ConnectionPoolException e) {
            e.printStackTrace();
        }
        Integer finalPrice = 0;
        String username = request.getParameter(USERNAME);
        String password = request.getParameter(PASSWORD);
        User user;
        try {
            user = userDao().getByUsername(username);
        } catch (DAOException e) {
            LOGGER.warn("Request cannot be executed.");
            throw new ActionException(e);
        }
        if (user == null) {
            session.setAttribute(LOGIN_ERROR, ACCOUNT + NOT_FOUND);
            LOGGER.debug("Wrong username.");
            result = new ActionResult(ActionResult.METHOD.FORWARD, PIZZA_UNREG);
            return result;
        }
        if (!password.equals(user.getPassword())) {
            session.setAttribute(LOGIN_ERROR, ACCOUNT + IS_BAD);
            LOGGER.debug("Wrong password. " + password);
            result = new ActionResult(ActionResult.METHOD.FORWARD, PIZZA_UNREG);
            return result;
        }
        if (user.getRole().equals(Role.ADMIN))
            result = new ActionResult(ActionResult.METHOD.REDIRECT, CABINET);
        else if (user.getRole().equals(Role.UNREGISTERED_USER))
            result = new ActionResult(ActionResult.METHOD.FORWARD, PIZZA_UNREG);
        else {
            try {
                int orderId = orderDao.getByUserId(user.getId());
                if (orderId != 0) {
                    order = orderDao.getById(orderId);
                    orderDetails = orderDetailsDao.getAllByOrderId(order.getId());
                    if (order.getStatus().equals(Status.UNPAID)) {
                        session.setAttribute(ORDER_DETAILS, orderDetails);
                        session.setAttribute(ORDER, order);
                    }
                }
            } catch (DAOException e) {
                e.printStackTrace();
            } catch (ConnectionPoolException e) {
                e.printStackTrace();
            }

            result = new ActionResult(ActionResult.METHOD.REDIRECT, PIZZA_LOGED);
        }

        session.setAttribute(USER, user);
        session.setAttribute(ID, user.getId());
        session.setAttribute(FINALPRICE, finalPrice);
        session.removeAttribute(EMAIL);
        session.removeAttribute(LOGIN_ERROR);
        session.removeAttribute(ERROR);
        LOGGER.debug("User " + user.getEmail() + " has been logged.");
        return result;
    }

    /**
     * Is used to get user dao. It initializes dao during the first use.
     *
     * @return The user.
     * @throws DAOException If something fails.
     */
    private UserDao userDao() throws DAOException {
        if (dao == null) {
            try {
                dao = new UserDao();
            } catch (ConnectionPoolException e) {
                e.printStackTrace();
            }
        }
        return dao;
    }
}