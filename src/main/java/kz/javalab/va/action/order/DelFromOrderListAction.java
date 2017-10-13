package kz.javalab.va.action.order;

import kz.javalab.va.action.Action;
import kz.javalab.va.action.ActionException;
import kz.javalab.va.action.ActionResult;
import kz.javalab.va.action.account.LoginAction;
import kz.javalab.va.connection.pool.ConnectionPoolException;
import kz.javalab.va.dao.DAOException;
import kz.javalab.va.dao.impl.*;
import kz.javalab.va.entity.order.Order;
import kz.javalab.va.entity.OrderDetails;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class DelFromOrderListAction implements Action {
    private static final Logger LOGGER = Logger.getLogger(LoginAction.class);
    private OrderDetailsDao orderDetailsDao = null;
    private OrderDao orderDao = null;
    private Order order;
    private Integer finalPrice = 0;

    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {
        HttpSession session = request.getSession();
        try {
            orderDao = new OrderDao();
            orderDetailsDao = new OrderDetailsDao();
        } catch (ConnectionPoolException e) {
            e.printStackTrace();
        }
        Integer id = Integer.parseInt(request.getParameter("order_detail_id"));
        Integer productPrice = Integer.parseInt(request.getParameter("order_detail_final_price"));
        order = (Order) session.getAttribute("order");

        try {
            orderDetailsDao.delete(id);
            LOGGER.info("Order details is deleted");
            finalPrice = order.getSumOfOrder() - productPrice;
            order.setSumOfOrder(finalPrice);
            orderDao.update(order);
            LOGGER.info("Final price of order was changed");
            List<OrderDetails> orderDetailsList = orderDetailsDao.getAllByOrderId(order.getId());
            session.setAttribute("order", order);
            session.setAttribute("order_details", orderDetailsList);
        } catch (DAOException e) {
            e.printStackTrace();
        } catch (ConnectionPoolException e) {
            e.printStackTrace();
        }

        String referer = request.getHeader("referer");
        referer = referer.substring(referer.lastIndexOf("/") + 1, referer.length());
        return new ActionResult(ActionResult.METHOD.REDIRECT, referer);
    }
}
