package kz.javalab.va.service.admin;

import kz.javalab.va.dao.DaoException;
import kz.javalab.va.dao.impl.OrderDao;
import kz.javalab.va.entity.order.Order;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@ComponentScan
public class AdminOrderService extends AdminService<Order> {
    private final static Logger logger = Logger.getRootLogger();

    @Autowired
    private OrderDao orderDao;

    public AdminOrderService(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    public AdminOrderService() {
    }

    public List<Order> getOrders() {
        return getList(orderDao);
    }

    public void updateOrder(Order orderModel) {
        try {
            orderDao.update(orderModel);
        } catch (DaoException e) {
            e.printStackTrace();
            logger.error("Error during order update", e);
        }
    }

    public void createOrder(Order orderModel) {
        try {
            orderDao.create(orderModel);
        } catch (DaoException e) {
            e.printStackTrace();
            logger.error("Error during order creation", e);
        }
    }
}
