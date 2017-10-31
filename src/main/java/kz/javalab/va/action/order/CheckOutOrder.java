package kz.javalab.va.action.order;

import kz.javalab.va.action.Action;
import kz.javalab.va.action.ActionException;
import kz.javalab.va.action.ActionResult;
import kz.javalab.va.action.account.LoginAction;
import kz.javalab.va.connection.pool.ConnectionPoolException;
import kz.javalab.va.dao.DAOException;
import kz.javalab.va.dao.impl.OrderDao;
import kz.javalab.va.dao.impl.UserDao;
import kz.javalab.va.entity.order.Order;
import kz.javalab.va.entity.order.Status;
import kz.javalab.va.entity.user.User;
import kz.javalab.va.util.Constants;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CheckOutOrder implements Action {
    private static final Logger LOGGER = Logger.getLogger(LoginAction.class);

    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {
        HttpSession session = request.getSession();
        String address = request.getParameter(Constants.ATTRIBUTE_ADDRESS);
        String phone = request.getParameter(Constants.ATTRIBUTE_PHONE);
        Order order = (Order) session.getAttribute(Constants.ATTRIBUTE_ORDER);
        User user = (User) session.getAttribute(Constants.ATTRIBUTE_USER);
        if (user != null) {
            int balance = user.getBalance();
            int sumOrder = order.getSumOfOrder();
            if (balance >= sumOrder) {
                balance = balance - sumOrder;
                user.setBalance(balance);
                order.setPhone(phone);
                order.setStatus(Status.PAID_FOR);
                order.setAddress(address);
                try {
                    OrderDao orderDao = new OrderDao();
                    UserDao userDao = new UserDao();
                    orderDao.update(order);
                    LOGGER.info("Order is success");
                    userDao.update(user);
                    LOGGER.info("Balance of user is changed");
                    session.removeAttribute(Constants.ATTRIBUTE_ORDER_DETAILS);
                    session.removeAttribute(Constants.ATTRIBUTE_ORDER);
                    session.removeAttribute(Constants.ATTRIBUTE_QNT);
                    session.removeAttribute(Constants.ATTRIBUTE_SIZE);
                    session.removeAttribute(Constants.ATTRIBUTE_FINALPRICE);
                } catch (DAOException | ConnectionPoolException e) {
                    LOGGER.error("Ordering Error", e);
                    throw new ActionException(e);
                }
            } else session.setAttribute(Constants.ATTRIBUTE_ERROR, Constants.BALANCE_IS_LOW_ERROR);
        } else session.setAttribute(Constants.ATTRIBUTE_ERROR, Constants.ACCOUNT_NOT_FOUND_ERROR);
        session.setAttribute(Constants.ATTRIBUTE_SUCCESSFULLY_ORDERED, Constants.ATTRIBUTE_SUCCESSFULLY_ORDERED_MESSAGE);
        String referer = request.getHeader(Constants.PAGE_REFERER);
        referer = referer.substring(referer.lastIndexOf("/") + 1, referer.length());
        return new ActionResult(ActionResult.METHOD.REDIRECT, referer);
    }
}
