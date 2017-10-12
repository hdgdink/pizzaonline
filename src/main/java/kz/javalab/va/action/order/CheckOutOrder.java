package kz.javalab.va.action.order;

import kz.javalab.va.action.Action;
import kz.javalab.va.action.ActionException;
import kz.javalab.va.action.ActionResult;
import kz.javalab.va.action.account.LoginAction;
import kz.javalab.va.connection.pool.ConnectionPoolException;
import kz.javalab.va.dao.DAOException;
import kz.javalab.va.dao.impl.*;
import kz.javalab.va.entity.Food;
import kz.javalab.va.entity.OrderDetails;
import kz.javalab.va.entity.Size;
import kz.javalab.va.entity.order.Order;
import kz.javalab.va.entity.order.Status;
import kz.javalab.va.entity.user.User;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by HdgDink} on 01.10.2017.
 */
public class CheckOutOrder implements Action {
    private static final Logger LOGGER = Logger.getLogger(LoginAction.class);
    private FoodDao foodDao = null;
    private OrderDetailsDao orderDetailsDao = null;
    private SizeDao sizeDao = null;
    private OrderDao orderDao = null;
    private UserDao userDao = null;
    private OrderDetails currentOrder = null;
    private TypeDao typeDao = null;
    private User user;
    private Order order = null;
    private Size selectedSize = null;
    private Food currentFood = null;
    private Integer finalPrice = 0;

    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {
        HttpSession session = request.getSession();

        String address = request.getParameter("address");
        String phone = request.getParameter("phone");
        order = (Order) session.getAttribute("order");

        System.out.println("ORder from session: " + session.getAttribute("order"));

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
                System.out.println("ORDER STATUS: " + order.getStatus());
                System.out.println("USER BALANCE: " + user.getBalance());
                try {
                    orderDao = new OrderDao();
                    userDao = new UserDao();
                    orderDao.update(order);
                    userDao.update(user);
                    session.removeAttribute("order_details");
                    session.removeAttribute("order");
                    session.removeAttribute("quantity");
                    session.removeAttribute("size");
                    session.removeAttribute("finalPrice");
                    System.out.println("Success");
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
