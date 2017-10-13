package kz.javalab.va.action.order;

import kz.javalab.va.action.Action;
import kz.javalab.va.action.ActionException;
import kz.javalab.va.action.ActionResult;
import kz.javalab.va.action.account.LoginAction;
import kz.javalab.va.connection.pool.ConnectionPoolException;
import kz.javalab.va.dao.DAOException;
import kz.javalab.va.dao.impl.*;
import kz.javalab.va.entity.order.Order;
import kz.javalab.va.entity.order.Status;
import kz.javalab.va.entity.user.User;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CheckOutOrder implements Action {
    private static final Logger LOGGER = Logger.getLogger(LoginAction.class);
    private OrderDao orderDao = null;
    private UserDao userDao = null;
    private User user;
    private Order order = null;

    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {
        HttpSession session = request.getSession();
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");
        order = (Order) session.getAttribute("order");
        user = (User) session.getAttribute("user");
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
                    session.removeAttribute("order_details");
                    session.removeAttribute("order");
                    session.removeAttribute("quantity");
                    session.removeAttribute("size");
                    session.removeAttribute("finalPrice");
                } catch (DAOException e) {
                    e.printStackTrace();
                } catch (ConnectionPoolException e) {
                    e.printStackTrace();
                }
            } else session.setAttribute("UserError", "account.BalanceIslow");
        } else session.setAttribute("UserError", "account.UserNotFound");


        String referer = request.getHeader("referer");
        referer = referer.substring(referer.lastIndexOf("/") + 1, referer.length());
        return new ActionResult(ActionResult.METHOD.REDIRECT, referer);
    }
}
