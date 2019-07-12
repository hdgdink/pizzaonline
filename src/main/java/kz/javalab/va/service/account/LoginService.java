package kz.javalab.va.service.account;

import kz.javalab.va.dao.DaoException;
import kz.javalab.va.dao.impl.OrderDao;
import kz.javalab.va.dao.impl.OrderDetailsDao;
import kz.javalab.va.dao.impl.UserDao;
import kz.javalab.va.entity.OrderDetails;
import kz.javalab.va.entity.order.Order;
import kz.javalab.va.entity.order.Status;
import kz.javalab.va.entity.user.Role;
import kz.javalab.va.entity.user.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@ComponentScan
public class LoginService {
    private static final Logger logger = Logger.getRootLogger();

    @Autowired
    private OrderDetailsDao orderDetailsDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private OrderDao orderDao;

    public LoginService(OrderDetailsDao orderDetailsDao, UserDao userDao, OrderDao orderDao) {
        this.orderDetailsDao = orderDetailsDao;
        this.userDao = userDao;
        this.orderDao = orderDao;
    }

    public LoginService() {
    }

    public User getUser(User model) {
        User user = null;

        try {
            user = userDao.getByUsername(model.getUsername());
        } catch (DaoException e) {
            e.printStackTrace();
        }

        return user;
    }

    public Map<String, Object> getOrderDetails(User user) {
        Integer orderId;
        Order order = null;
        List<OrderDetails> orderDetails = null;
        Map<String, Object> details = new HashMap<>();

        orderId = orderDao.getLastOrder(user.getId());

        if (orderId != 0) {
            order = orderDao.getById(orderId);
            orderDetails = orderDetailsDao.getAllByOrderId(order.getId());
        }

        if (order != null && order.getStatus().equals(Status.UNPAID)) {
            details.put("order_details", orderDetails);
            details.put("order", order);
        }

        return details;
    }

    public boolean adminCheck(User user) {
        return user.getRole().equals(Role.ADMIN);
    }

    public boolean banCheck(User user) {
        return user.getRole().equals(Role.UNREGISTERED_USER);
    }
}
