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
    private static final String DELETE = "delete";
    private static final String UPDATE = "update";
    private static final String ORDER_DETAILS = "order_details";
    private static final String ID = "id";
    private static final String PRODUCT_ID = "productId";
    private static final String NAME_RU = "nameRu";
    private static final String NAME_EN = "nameEn";
    private static final String SIZE_NAME = "sizeName";
    private static final String TYPE_ID = "typeId";
    private static final String TYPE_NAME = "typeName";
    private static final String QNT = "quantity";
    private static final String IRDER_ID = "orderId";
    private static final String PRICE = "price";
    private OrderDetailsDao dao;
    private OrderDetails orderDetails = null;
    private HttpServletRequest req;

    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {
        req = request;
        if (request.getParameter(DELETE) != null) {
            deleteOrderDetails();
        }
        if (request.getParameter(UPDATE) != null) {
            updateOrderDetails();
        }
        return new ActionResult(ActionResult.METHOD.REDIRECT, ORDER_DETAILS);
    }

    private void deleteOrderDetails() throws ActionException {
        Integer id = Integer.valueOf(req.getParameter(DELETE));
        try {
            orderDetailsDao().delete(id);
        } catch (DAOException e) {
            LOGGER.error("Error at delete OrderDetails()");
            throw new ActionException("Error at delete OrderDetails()", e);
        }
    }

    private void updateOrderDetails() {
        Integer id = Integer.parseInt(req.getParameter(ID));
        Integer foodId = Integer.parseInt(req.getParameter(PRODUCT_ID));
        String foodNameRu = req.getParameter(NAME_RU);
        String foodNameEn = req.getParameter(NAME_EN);
        String sizeName = req.getParameter(SIZE_NAME);
        Integer typeId = Integer.parseInt(req.getParameter(TYPE_ID));
        String typeName = req.getParameter(TYPE_NAME);
        Integer quantity = Integer.parseInt(req.getParameter(QNT));
        Integer orderId = Integer.parseInt(req.getParameter(IRDER_ID));
        Integer price = Integer.parseInt(req.getParameter(PRICE));
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


