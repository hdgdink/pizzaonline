package kz.javalab.va.action.admin;

import kz.javalab.va.action.Action;
import kz.javalab.va.action.ActionException;
import kz.javalab.va.action.ActionResult;
import kz.javalab.va.connection.pool.ConnectionPoolException;
import kz.javalab.va.dao.DAOException;
import kz.javalab.va.dao.impl.OrderDao;
import kz.javalab.va.entity.order.Order;
import kz.javalab.va.util.Constants;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class ShowOrderPageAction implements Action {
    private static final Logger LOGGER = Logger.getLogger(ShowOrderPageAction.class);

    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {
        HttpSession session = request.getSession();
        try {
            List<Order> orderList = new OrderDao().getAll();
            session.setAttribute(Constants.ATTRIBUTE_ALL_ORDERS_LIST, orderList);
        } catch (DAOException | ConnectionPoolException e) {
            LOGGER.error("Error of creating OrderList in OrderDao", e);
            throw new ActionException(e);
        }
        return new ActionResult(ActionResult.METHOD.FORWARD, Constants.ACTION_ADMIN_ORDERS);
    }
}
