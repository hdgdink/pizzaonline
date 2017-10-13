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
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CheckOutOrder implements Action {
    private static final Logger LOGGER = Logger.getLogger(LoginAction.class);
    private static final String ADDRESS = "address";
    private static final String PHONE = "phone";
    private static final String ORDER = "order";
    private static final String USER = "user";
    private static final String ORDER_DETAILS = "order_details";
    private static final String QNT = "quantity";
    private static final String SIZE = "size";
    private static final String FINAL_PRICE = "finalPrice";
    private static final String USER_ERROR = "UserError";
    private static final String ACCOUNT = "account";
    private static final String USER_NOT_FOUND = ".UserNotFound";
    private static final String BALANCE_IS_LOW = ".BalanceIslow";
    private static final String REFERER = "referer";
    private OrderDao orderDao = null;
    private UserDao userDao = null;
    private User user;
    private Order order = null;

    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {
        HttpSession session = request.getSession();
        String address = request.getParameter(ADDRESS);
        String phone = request.getParameter(PHONE);
        order = (Order) session.getAttribute(ORDER);
        user = (User) session.getAttribute(USER);
        if (user != null) {
            int balance = user.getBalance();
            int sumOrder = order.getSumOfOrder();
            if (balance > sumOrder) {
                balance = balance - sumOrder;
                user.setBalance(balance);
                order.setPhone(phone);
                order.setStatus(Status.PAID_FOR);
                order.setAddress(address);
                try {
                    orderDao = new OrderDao();
                    userDao = new UserDao();
                    orderDao.update(order);
                    LOGGER.info("Order is success");
                    userDao.update(user);
                    LOGGER.info("Balance of user is changed");
                    session.removeAttribute(ORDER_DETAILS);
                    session.removeAttribute(ORDER);
                    session.removeAttribute(QNT);
                    session.removeAttribute(SIZE);
                    session.removeAttribute(FINAL_PRICE);
                } catch (DAOException e) {
                    e.printStackTrace();
                } catch (ConnectionPoolException e) {
                    e.printStackTrace();
                }
            } else session.setAttribute(USER_ERROR, ACCOUNT + BALANCE_IS_LOW);
        } else session.setAttribute(USER_ERROR, ACCOUNT + USER_NOT_FOUND);


        String referer = request.getHeader(REFERER);
        referer = referer.substring(referer.lastIndexOf("/") + 1, referer.length());
        return new ActionResult(ActionResult.METHOD.REDIRECT, referer);
    }
}
