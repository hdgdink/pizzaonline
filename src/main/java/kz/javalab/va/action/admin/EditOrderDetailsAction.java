package kz.javalab.va.action.admin;

import kz.javalab.va.action.Action;
import kz.javalab.va.action.ActionException;
import kz.javalab.va.action.ActionResult;
import kz.javalab.va.connection.pool.ConnectionPoolException;
import kz.javalab.va.dao.DAOException;
import kz.javalab.va.dao.impl.OrderDetailsDao;
import kz.javalab.va.entity.OrderDetails;
import kz.javalab.va.util.Constants;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class EditOrderDetailsAction implements Action {
    private static final Logger LOGGER = Logger.getLogger(EditProductAction.class);
    private OrderDetailsDao dao;
    private HttpServletRequest req;

    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {
        req = request;
        if (request.getParameter(Constants.ATTRIBUTE_DELETE) != null) {
            deleteOrderDetails();
        }
        if (request.getParameter(Constants.ATTRIBUTE_UPDATE) != null) {
            updateOrderDetails();
        }
        return new ActionResult(ActionResult.METHOD.REDIRECT, Constants.ATTRIBUTE_ORDER_DETAILS);
    }

    private void deleteOrderDetails() throws ActionException {
        Integer id = Integer.valueOf(req.getParameter(Constants.ATTRIBUTE_DELETE));
        try {
            orderDetailsDao().delete(id);
        } catch (DAOException e) {
            LOGGER.error("Error at delete OrderDetails", e);
            throw new ActionException(e);
        }
    }

    private void updateOrderDetails() throws ActionException {
        Integer id = Integer.parseInt(req.getParameter(Constants.ATTRIBUTE_ID));
        try {
            OrderDetails orderDetails = orderDetailsDao().getById(id);
            CreateEntityAdmin.setOrderDetails(orderDetails);
            orderDetailsDao().update(orderDetails);
        } catch (DAOException e) {
            LOGGER.error("Error at update OrderDetails", e);
            throw new ActionException(e);
        }
    }

    private OrderDetailsDao orderDetailsDao() throws DAOException {
        if (dao == null) {
            try {
                dao = new OrderDetailsDao();
            } catch (ConnectionPoolException e) {
                LOGGER.error("Error of initialization OrderDetailsDao", e);
            }
        }
        return dao;
    }
}


