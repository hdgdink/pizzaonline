package kz.javalab.va.action.admin;

import kz.javalab.va.action.ActionException;
import kz.javalab.va.action.ActionResult;
import kz.javalab.va.connection.pool.ConnectionPoolException;
import kz.javalab.va.dao.DAOException;
import kz.javalab.va.dao.impl.OrderDetailsDao;
import kz.javalab.va.entity.OrderDetails;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class EditOrderDetailsAction implements kz.javalab.va.action.Action {
    private static final Logger LOGGER = Logger.getLogger(EditProductAction.class);
    private OrderDetailsDao dao;
    private OrderDetails orderDetails = null;
    private HttpServletRequest req;

    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {
        req = request;
        if (request.getParameter("delete") != null) {
            deleteOrderDetails();
        }
        if (request.getParameter("update") != null) {
            updateOrderDetails();
        }
        return new ActionResult(ActionResult.METHOD.REDIRECT, "order_details");
    }

    private void deleteOrderDetails() throws ActionException {
        Integer id = Integer.valueOf(req.getParameter("delete"));
        try {
            orderDetailsDao().delete(id);
        } catch (DAOException e) {
            LOGGER.error("Error at delete OrderDetails()");
            throw new ActionException("Error at delete OrderDetails()", e);
        }
    }

    private void updateOrderDetails() {
        Integer id = Integer.parseInt(req.getParameter("id"));
        Integer foodId = Integer.parseInt(req.getParameter("productId"));
        String foodNameRu = req.getParameter("nameRu");
        String foodNameEn = req.getParameter("nameEn");
        String sizeName = req.getParameter("sizeName");
        Integer typeId = Integer.parseInt(req.getParameter("typeId"));
        String typeName = req.getParameter("typeName");
        Integer quantity = Integer.parseInt(req.getParameter("quantity"));
        Integer orderId = Integer.parseInt(req.getParameter("orderId"));
        Integer price = Integer.parseInt(req.getParameter("price"));
        try {
            orderDetails = orderDetailsDao().getById(id);
            orderDetails.setFoodNameRu(foodNameRu);
            orderDetails.setFoodNameEn(foodNameEn);
            orderDetails.setFoodId(foodId);
            orderDetails.setTypeName(typeName);
            orderDetails.setFinalPrice(price);
            orderDetails.setSizeName(sizeName);
            orderDetails.setTypeId(typeId);
            orderDetails.setTypeName(typeName);
            orderDetails.setQuantity(quantity);
            orderDetails.setOrderId(orderId);
            orderDetailsDao().update(orderDetails);
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }

    private OrderDetailsDao orderDetailsDao() throws DAOException {
        if (dao == null) {
            try {
                dao = new OrderDetailsDao();
            } catch (ConnectionPoolException e) {
                e.printStackTrace();
            }
        }
        return dao;
    }
}


