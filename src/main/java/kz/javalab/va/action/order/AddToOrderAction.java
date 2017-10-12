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

/**
 * Created by HdgDink} on 05.10.2017.
 */
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
            sizeDao = new SizeDao();
            typeDao = new TypeDao();
        } catch (ConnectionPoolException e) {
            e.printStackTrace();
        }
        //берем из сессии ИД еды, выбранное количество, выбранный размер
        Integer id = Integer.parseInt(request.getParameter("food"));
        Integer count = Integer.parseInt(request.getParameter("count"));
        int sizeValue = Integer.parseInt(request.getParameter("size"));
///создаем текущие детали заказа
        currentOrder = new OrderDetails();
        try {
            //присваеваем текущей выбранной еде значения из бд выбирая по ИД
            currentFood = foodDao.getById(id);
            //присваеваем текущему выбранному размеру значения из бд выбирая по значению
            selectedSize = sizeDao.getByValue(sizeValue);
        } catch (DAOException e) {
            e.printStackTrace();
        }
        //устанавливаем конечную цену продукта в зависимости от размера и количества
        currentFood.setPrice((currentFood.getPrice() * sizeValue) * count);
        //счетчик окончательной цены заказа
        finalPrice = (Integer) session.getAttribute("finalPrice");
        if (finalPrice == null) finalPrice = 0;
        System.out.println("final price: " + finalPrice);
        System.out.println("curent food: " + currentFood);
        finalPrice = finalPrice + currentFood.getPrice();

        System.out.println("final price is :" + finalPrice);
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
                    orderDao = new OrderDao();
                    orderDao.create(order);
                    System.out.println("User id=" + user.getId());
                    order.setId(orderDao.getByUserId(user.getId()));
                    session.setAttribute("order", order);
                } catch (ConnectionPoolException e) {
                    e.printStackTrace();
                } catch (DAOException e) {
                    e.printStackTrace();
                }
            } else finalPrice = order.getSumOfOrder() + currentFood.getPrice();

            try {
                //обновляем общую сумму заказа после добавления нового продукта
                order.setSumOfOrder(finalPrice);
                orderDao.update(order);
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

                List<OrderDetails> orderDetailsList = orderDetailsDao.getAllByOrderId(order.getId());
                System.out.println(orderDetailsList.size());
                session.setAttribute("order_details", orderDetailsList);
            } catch (DAOException e) {
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
