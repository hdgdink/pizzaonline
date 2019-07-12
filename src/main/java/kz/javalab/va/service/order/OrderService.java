package kz.javalab.va.service.order;

import kz.javalab.va.dao.DaoException;
import kz.javalab.va.dao.impl.FoodDao;
import kz.javalab.va.dao.impl.OrderDao;
import kz.javalab.va.dao.impl.OrderDetailsDao;
import kz.javalab.va.dao.impl.SizeDao;
import kz.javalab.va.entity.Food;
import kz.javalab.va.entity.OrderDetails;
import kz.javalab.va.entity.Size;
import kz.javalab.va.entity.order.Order;
import kz.javalab.va.entity.order.Status;
import kz.javalab.va.entity.user.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
@ComponentScan
public class OrderService {
    private static final Logger logger = Logger.getRootLogger();

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private OrderDetailsDao orderDetailsDao;

    @Autowired
    private SizeDao sizeDao;

    @Autowired
    private FoodDao foodDao;

    public OrderService(OrderDao orderDao, OrderDetailsDao orderDetailsDao, SizeDao sizeDao, FoodDao foodDao) {
        this.orderDao = orderDao;
        this.orderDetailsDao = orderDetailsDao;
        this.sizeDao = sizeDao;
        this.foodDao = foodDao;
    }

    public OrderService() {
    }

    public void addToList(int count, Integer sizeVal, Integer foodId, HttpSession session, User loggedUser) {
        Food selectedProduct = foodDao.getById(foodId);
        Size selectedSize = sizeDao.getByValue(sizeVal);
        List<OrderDetails> details;

        Order order = (Order) session.getAttribute("order");
        Integer finalOrderPrice = (Integer) session.getAttribute("finalPrice");

        if (finalOrderPrice == null) {
            finalOrderPrice = 0;
        }

        selectedProduct.setPrice((selectedProduct.getPrice() * sizeVal) * count);
        finalOrderPrice = finalOrderPrice + selectedProduct.getPrice();

        if (order == null) {
            order = createNewOrder(loggedUser.getId(), finalOrderPrice);
            session.setAttribute("order", order);
        } else {
            finalOrderPrice = order.getSumOfOrder() + selectedProduct.getPrice();
            order = updateOrder(finalOrderPrice, order);
        }

        logger.debug("Final order price is : " + finalOrderPrice);
        createNewOrderDetails(count, selectedProduct, order, selectedSize);
        details = orderDetailsDao.getAllByOrderId(order.getId());

        session.setAttribute("finalPrice", finalOrderPrice);
        session.setAttribute("order_details", details);
    }

    public void removeEntryFromOrderList(int id, int productPrice, HttpSession session) {
        Integer finalPrice;
        Order order = (Order) session.getAttribute("order");

        try {
            orderDetailsDao.delete(id);
            logger.info("Order details is deleted");

            finalPrice = order.getSumOfOrder() - productPrice;
            order.setSumOfOrder(finalPrice);

            orderDao.update(order);
            logger.info("Final price of order was changed");
        } catch (DaoException e) {
            e.printStackTrace();
        }

        List<OrderDetails> orderDetailsList = orderDetailsDao.getAllByOrderId(order.getId());
        session.setAttribute("order", order);
        session.setAttribute("order_details", orderDetailsList);
    }

    private void createNewOrderDetails(int count, Food selProduct, Order order, Size size) {
        OrderDetails orderDetails = new OrderDetails();

        orderDetails.setFinalPrice(selProduct.getPrice());
        orderDetails.setFoodId(selProduct.getId());
        orderDetails.setFoodNameEn(selProduct.getNameEn());
        orderDetails.setFoodNameRu(selProduct.getNameRu());
        orderDetails.setTypeId(selProduct.getTypeId());
        orderDetails.setOrderId(order.getId());
        orderDetails.setSizeName(size.getName());
        orderDetails.setQuantity(count);

        try {
            orderDetailsDao.create(orderDetails);
        } catch (DaoException e) {
            e.printStackTrace();
            logger.error("Error during order details creation");
        }
    }

    private Order updateOrder(int finalOrderPrice, Order order) {
        try {
            order.setSumOfOrder(finalOrderPrice);
            orderDao.update(order);
        } catch (DaoException e) {
            e.printStackTrace();
            logger.error("Error during order update", e);
        }

        return order;
    }

    private Order createNewOrder(int userId, int finalOrderPrice) {
        Order order = new Order();

        try {
            order.setStatus(Status.UNPAID);
            order.setUserId(userId);
            order.setSumOfOrder(finalOrderPrice);
            orderDao.create(order);
        } catch (DaoException e) {
            e.printStackTrace();
            logger.error("Error during order creation", e);
        }

        Integer orderId = orderDao.getLastOrder(userId);
        order = orderDao.getById(orderId);

        return order;
    }
}