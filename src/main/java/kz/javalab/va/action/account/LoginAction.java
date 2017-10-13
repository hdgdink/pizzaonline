package kz.javalab.va.action.account;

import com.sun.org.apache.xpath.internal.SourceTree;
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
    private ActionResult result;
    private UserDao dao;
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final String USER = "user";
    private static final String ID = "id";
    private static final String FINALPRICE = "finalPrice";
    private static final ActionResult LOGIN_FAILED = new ActionResult(ActionResult.METHOD.REDIRECT, "pizza");
    private static final ActionResult ADMIN_LOGGED = new ActionResult(ActionResult.METHOD.REDIRECT, "cabinet");
    private static final ActionResult CLIENT_LOGGED = new ActionResult(ActionResult.METHOD.REDIRECT, "pizza_loged");
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
            //order = new Order();
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
            session.setAttribute("logInError", "account.notFound");
            LOGGER.debug("Wrong username.");
            result = new ActionResult(ActionResult.METHOD.FORWARD, "pizza_unreg");
            return result;
        }
        if (!password.equals(user.getPassword())) {
            session.setAttribute("logInError", "account.isBad");
            LOGGER.debug("Wrong password. " + password);
            result = new ActionResult(ActionResult.METHOD.FORWARD, "pizza_unreg");
            return result;
        }
        if (user.getRole().equals(Role.ADMIN))
            result = new ActionResult(ActionResult.METHOD.REDIRECT, "cabinet");
        else if (user.getRole().equals(Role.UNREGISTERED_USER))
            result = new ActionResult(ActionResult.METHOD.FORWARD, "pizza_unreg");
        else {
            try {
                int orderId = orderDao.getByUserId(user.getId());
                if (orderId != 0) {
                    order = orderDao.getById(orderId);
                    orderDetails = orderDetailsDao.getAllByOrderId(order.getId());
                    if (order.getStatus().equals(Status.UNPAID)) {
                        session.setAttribute("order_details", orderDetails);
                        session.setAttribute("order", order);
                    }
                }
            } catch (DAOException e) {
                e.printStackTrace();
            } catch (ConnectionPoolException e) {
                e.printStackTrace();
            }

            result = new ActionResult(ActionResult.METHOD.REDIRECT, "pizza_loged");
        }

        session.setAttribute(USER, user);
        session.setAttribute(ID, user.getId());
        session.setAttribute(FINALPRICE, finalPrice);
        session.removeAttribute("email");
        session.removeAttribute("signInError");
        session.removeAttribute("error");
        session.removeAttribute("link");
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