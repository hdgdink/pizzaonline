package kz.javalab.va.action.order;

import kz.javalab.va.action.Action;
import kz.javalab.va.action.ActionException;
import kz.javalab.va.action.ActionResult;
import kz.javalab.va.connection.pool.ConnectionPoolException;
import kz.javalab.va.dao.DAOException;
import kz.javalab.va.dao.impl.*;
import kz.javalab.va.entity.Food;
import kz.javalab.va.entity.OrderDetails;
import kz.javalab.va.entity.Size;
import kz.javalab.va.entity.order.Order;
import kz.javalab.va.entity.order.Status;
import kz.javalab.va.entity.user.User;
import kz.javalab.va.util.Constants;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class AddToOrderAction implements Action {
    private static final Logger LOGGER = Logger.getLogger(AddToOrderAction.class);

    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {
        HttpSession session = request.getSession();
        FoodDao foodDao;
        Integer finalPrice;
        OrderDetailsDao orderDetailsDao;
        SizeDao sizeDao;
        OrderDao orderDao;
        TypeDao typeDao;
        try {
            foodDao = new FoodDao();
            orderDetailsDao = new OrderDetailsDao();
            orderDao = new OrderDao();
            sizeDao = new SizeDao();
            typeDao = new TypeDao();
        } catch (ConnectionPoolException e) {
            LOGGER.error(Constants.DAO_INIT_ERROR, e);
            throw new ActionException(e);
        }
        Integer id = Integer.parseInt(request.getParameter(Constants.ATTRIBUTE_FOOD));
        Integer count = Integer.parseInt(request.getParameter(Constants.ATTRIBUTE_COUNT));
        int sizeValue = Integer.parseInt(request.getParameter(Constants.ATTRIBUTE_SIZE));
        OrderDetails currentOrder = new OrderDetails();
        Size selectedSize;
        Food currentFood;
        try {
            currentFood = foodDao.getById(id);
            LOGGER.info("Current product is taked from db with ID:" + currentFood.getId());
            selectedSize = sizeDao.getByValue(sizeValue);
            LOGGER.info("Selected Size is:" + selectedSize.getName());
        } catch (DAOException e) {
            LOGGER.error("Error while getting current product", e);
            throw new ActionException(e);
        }
        currentFood.setPrice((currentFood.getPrice() * sizeValue) * count);
        LOGGER.info("Price of selected product is:" + currentFood.getPrice());
        finalPrice = (Integer) session.getAttribute(Constants.ATTRIBUTE_FINALPRICE);
        if (finalPrice == null) finalPrice = 0;
        finalPrice = finalPrice + currentFood.getPrice();
        User user = (User) session.getAttribute(Constants.ATTRIBUTE_USER);
        if (user != null) {
            Order order = (Order) session.getAttribute(Constants.ATTRIBUTE_ORDER);
            if (order == null) {
                order = new Order();
                order.setAddress((String) request.getAttribute(Constants.ATTRIBUTE_ADDRESS));
                order.setUserId(user.getId());
                order.setSumOfOrder(finalPrice);
                order.setPhone((String) request.getAttribute(Constants.ATTRIBUTE_PHONE));
                order.setStatus(Status.UNPAID);
                try {
                    orderDao.create(order);
                    LOGGER.info("New order was created");
                    order.setId(orderDao.getByUserId(user.getId()));
                    session.setAttribute(Constants.ATTRIBUTE_ORDER, order);
                } catch (DAOException e) {
                    LOGGER.error("Error while order creating", e);
                    throw new ActionException(e);
                }
            } else finalPrice = order.getSumOfOrder() + currentFood.getPrice();

            try {
                order.setSumOfOrder(finalPrice);
                orderDao.update(order);
                LOGGER.info("Order was updated");
                session.setAttribute(Constants.ATTRIBUTE_FINALPRICE, finalPrice);
                currentOrder.setOrderId(order.getId());
                currentOrder.setFoodId(currentFood.getId());
                currentOrder.setFoodNameRu(currentFood.getNameRu());
                currentOrder.setFoodNameEn(currentFood.getNameEn());
                currentOrder.setSizeName(selectedSize.getName());
                currentOrder.setFinalPrice(currentFood.getPrice());
                currentOrder.setTypeId(currentFood.getTypeId());
                currentOrder.setTypeName(typeDao.getNameById(currentFood.getTypeId()));
                currentOrder.setQuantity(count);
                orderDetailsDao.create(currentOrder);
                LOGGER.info("Order details was created");
                List<OrderDetails> orderDetailsList;
                try {
                    orderDetailsList = orderDetailsDao.getAllByOrderId(order.getId());
                } catch (ConnectionPoolException e) {
                    LOGGER.error("Error of initialization OrderDetailsDao", e);
                    throw new ActionException(e);
                }
                session.setAttribute(Constants.ATTRIBUTE_ORDER_DETAILS, orderDetailsList);
            } catch (DAOException e) {
                LOGGER.error("Error of creating Order", e);
                throw new ActionException(e);
            }
        } else {
            session.setAttribute(Constants.ATTRIBUTE_LOGIN_ERROR, Constants.USER_NOT_LOGGED_ERROR);
        }
        String referer = request.getHeader(Constants.PAGE_REFERER);
        referer = referer.substring(referer.lastIndexOf("/") + 1, referer.length());
        return new ActionResult(ActionResult.METHOD.REDIRECT, referer);
    }
}
