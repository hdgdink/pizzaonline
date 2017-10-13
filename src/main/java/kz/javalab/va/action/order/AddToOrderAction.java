package kz.javalab.va.action.order;

import kz.javalab.va.action.Action;
import kz.javalab.va.action.ActionException;
import kz.javalab.va.action.ActionResult;
import kz.javalab.va.action.account.LoginAction;
import kz.javalab.va.connection.pool.ConnectionPoolException;
import kz.javalab.va.dao.DAOException;
import kz.javalab.va.dao.impl.*;
import kz.javalab.va.entity.Food;
import kz.javalab.va.entity.order.Order;
import kz.javalab.va.entity.OrderDetails;
import kz.javalab.va.entity.Size;
import kz.javalab.va.entity.order.Status;
import kz.javalab.va.entity.user.User;
import org.apache.log4j.Logger;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class AddToOrderAction implements Action {
    private static final Logger LOGGER = Logger.getLogger(LoginAction.class);
    private FoodDao foodDao = null;
    private OrderDetailsDao orderDetailsDao = null;
    private SizeDao sizeDao = null;
    private OrderDao orderDao = null;
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
        try {
            foodDao = new FoodDao();
            orderDetailsDao = new OrderDetailsDao();
            orderDao = new OrderDao();
            sizeDao = new SizeDao();
            typeDao = new TypeDao();
        } catch (ConnectionPoolException e) {
            e.printStackTrace();
        }
        Integer id = Integer.parseInt(request.getParameter("food"));
        Integer count = Integer.parseInt(request.getParameter("count"));
        int sizeValue = Integer.parseInt(request.getParameter("size"));
        currentOrder = new OrderDetails();
        try {
            currentFood = foodDao.getById(id);
            LOGGER.info("Current product is getted from db with ID:" + currentFood.getId());
            selectedSize = sizeDao.getByValue(sizeValue);
            LOGGER.info("Selected Size is:" + selectedSize.getName());
        } catch (DAOException e) {
            LOGGER.error("Error while getting current product", e);
            e.printStackTrace();
        }
        currentFood.setPrice((currentFood.getPrice() * sizeValue) * count);
        LOGGER.info("Price of selected product is:" + currentFood.getPrice());
        finalPrice = (Integer) session.getAttribute("finalPrice");
        if (finalPrice == null) finalPrice = 0;
        finalPrice = finalPrice + currentFood.getPrice();
        user = (User) session.getAttribute("user");
        if (user != null) {
            order = (Order) session.getAttribute("order");
            if (order == null) {
                order = new Order();
                order.setAddress((String) request.getAttribute("address"));
                order.setUserId(user.getId());
                order.setSumOfOrder(finalPrice);
                order.setPhone((String) request.getAttribute("phone"));
                order.setStatus(Status.UNPAID);
                try {
                    orderDao.create(order);
                    LOGGER.info("New order was created");
                    order.setId(orderDao.getByUserId(user.getId()));
                    session.setAttribute("order", order);
                } catch (DAOException e) {
                    LOGGER.error("Error while order creating", e);
                    e.printStackTrace();
                }
            } else finalPrice = order.getSumOfOrder() + currentFood.getPrice();

            try {
                order.setSumOfOrder(finalPrice);
                orderDao.update(order);
                LOGGER.info("Order was updated");
                session.setAttribute("finalPrice", finalPrice);
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
                List<OrderDetails> orderDetailsList = orderDetailsDao.getAllByOrderId(order.getId());
                session.setAttribute("order_details", orderDetailsList);
            } catch (DAOException e) {
                LOGGER.error("Error of creating", e);
                e.printStackTrace();
            } catch (ConnectionPoolException e) {
                e.printStackTrace();
            }
        } else {
            session.setAttribute("UserError", "account.UserIsNotLoged");
        }
        String referer = request.getHeader("referer");
        referer = referer.substring(referer.lastIndexOf("/") + 1, referer.length());
        return new ActionResult(ActionResult.METHOD.REDIRECT, referer);
    }
}
