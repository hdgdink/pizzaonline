package kz.javalab.va.service.order;

import kz.javalab.va.dao.DaoException;
import kz.javalab.va.dao.impl.OrderDao;
import kz.javalab.va.dao.impl.UserDao;
import kz.javalab.va.entity.order.Order;
import kz.javalab.va.entity.order.Status;
import kz.javalab.va.entity.user.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
@ComponentScan
public class CheckoutService {
    private static final Logger logger = Logger.getRootLogger();
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private UserDao userDao;

    public CheckoutService(OrderDao orderDao, UserDao userDao) {
        this.orderDao = orderDao;
        this.userDao = userDao;
    }

    public CheckoutService() {
    }

    public void checkOutOrder(HttpSession session, String phone, String address) {
        Order order = (Order) session.getAttribute("order");
        User user = (User) session.getAttribute("user");
        int balance = user.getBalance();
        int sumOrder = order.getSumOfOrder();

        if (balance >= sumOrder) {
            balance = balance - sumOrder;
            user.setBalance(balance);
            order.setPhone(phone);
            order.setStatus(Status.PAID_FOR);
            order.setAddress(address);

            try {
                orderDao.update(order);
                logger.info("Order is success");
                userDao.update(user);
                logger.info("Balance of user is changed");
                removeSessionAttributes(session);
            } catch (DaoException e) {
                logger.error("Ordering Error", e);
            }

            session.setAttribute("succesfullyOrdered", "default.ordered");
        } else {
            session.setAttribute("error", "account.BalanceIsLow");
        }
    }

    private void removeSessionAttributes(HttpSession session) {
        session.removeAttribute("order_details");
        session.removeAttribute("order");
        session.removeAttribute("quantity");
        session.removeAttribute("size");
        session.removeAttribute("finalPrice");
    }
}