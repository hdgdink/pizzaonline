package kz.javalab.va.action.admin;

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


public class EditOrderDetailsAction implements kz.javalab.va.action.Action {
    private static final Logger LOGGER = Logger.getLogger(EditProductAction.class);
    private OrderDetailsDao dao;
    private OrderDetails orderDetails = null;
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
            LOGGER.error("Error at delete OrderDetails()");
            throw new ActionException("Error at delete OrderDetails()", e);
        }
    }

    private void updateOrderDetails() {
        Integer id = Integer.parseInt(req.getParameter(Constants.ATTRIBUTE_ID));
        Integer foodId = Integer.parseInt(req.getParameter(Constants.ATTRIBUTE_PRODUCT_ID));
        String foodNameRu = req.getParameter(Constants.ATTRIBUTE_NAME_RU);
        String foodNameEn = req.getParameter(Constants.ATTRIBUTE_NAME_EN);
        String sizeName = req.getParameter(Constants.ATTRIBUTE_SIZE_NAME);
        Integer typeId = Integer.parseInt(req.getParameter(Constants.ATTRIBUTE_TYPE_ID));
        String typeName = req.getParameter(Constants.ATTRIBUTE_TYPE_NAME);
        Integer quantity = Integer.parseInt(req.getParameter(Constants.ATTRIBUTE_QNT));
        Integer orderId = Integer.parseInt(req.getParameter(Constants.ATTRIBUTE_ORDER_ID));
        Integer price = Integer.parseInt(req.getParameter(Constants.ATTRIBUTE_PRICE));
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


