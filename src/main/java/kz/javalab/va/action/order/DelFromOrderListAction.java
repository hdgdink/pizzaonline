package kz.javalab.va.action.order;

import kz.javalab.va.action.Action;
import kz.javalab.va.action.ActionException;
import kz.javalab.va.action.ActionResult;
import kz.javalab.va.action.account.LoginAction;
import kz.javalab.va.connection.pool.ConnectionPoolException;
import kz.javalab.va.dao.DAOException;
import kz.javalab.va.dao.impl.OrderDao;
import kz.javalab.va.dao.impl.OrderDetailsDao;
import kz.javalab.va.entity.OrderDetails;
import kz.javalab.va.entity.order.Order;
import kz.javalab.va.util.Constants;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class DelFromOrderListAction implements Action {
    private static final Logger LOGGER = Logger.getLogger(LoginAction.class);


    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {
        HttpSession session = request.getSession();
        Integer finalPrice;
        OrderDetailsDao orderDetailsDao;
        OrderDao orderDao;
        try {
            orderDao = new OrderDao();
            orderDetailsDao = new OrderDetailsDao();
        } catch (ConnectionPoolException e) {
            LOGGER.error(Constants.DAO_INIT_ERROR, e);
            throw new ActionException(e);
        }
        Integer id = Integer.parseInt(request.getParameter(Constants.ATTRIBUTE_ORDER_DETAILS_ID));
        Integer productPrice = Integer.parseInt(request.getParameter(Constants.ATTRIBUTE_ORDER_DETAILS_FINAL_PRICE));
        Order order = (Order) session.getAttribute(Constants.ATTRIBUTE_ORDER);
        try {
            orderDetailsDao.delete(id);
            LOGGER.info("Order details is deleted");
            finalPrice = order.getSumOfOrder() - productPrice;
            order.setSumOfOrder(finalPrice);
            orderDao.update(order);
            LOGGER.info("Final price of order was changed");
            List<OrderDetails> orderDetailsList = orderDetailsDao.getAllByOrderId(order.getId());
            session.setAttribute(Constants.ATTRIBUTE_ORDER, order);
            session.setAttribute(Constants.ATTRIBUTE_ORDER_DETAILS, orderDetailsList);
        } catch (DAOException | ConnectionPoolException e) {
            LOGGER.error("Error of updating Order", e);
            throw new ActionException(e);
        }
        String referer = request.getHeader(Constants.PAGE_REFERER);
        referer = referer.substring(referer.lastIndexOf("/") + 1, referer.length());
        return new ActionResult(ActionResult.METHOD.REDIRECT, referer);
    }
}
