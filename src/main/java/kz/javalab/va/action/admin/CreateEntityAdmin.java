package kz.javalab.va.action.admin;

import kz.javalab.va.action.Action;
import kz.javalab.va.action.ActionException;
import kz.javalab.va.action.ActionResult;
import kz.javalab.va.dao.impl.*;
import kz.javalab.va.entity.Food;
import kz.javalab.va.entity.OrderDetails;
import kz.javalab.va.entity.Size;
import kz.javalab.va.entity.Type;
import kz.javalab.va.entity.order.Order;
import kz.javalab.va.entity.order.Status;
import kz.javalab.va.entity.user.Role;
import kz.javalab.va.entity.user.User;
import kz.javalab.va.util.AttributeSetter;
import kz.javalab.va.util.Constants;
import kz.javalab.va.util.validator.FieldsValidator;
import kz.javalab.va.util.validator.ValidationException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CreateEntityAdmin implements Action {
    private static final Logger LOGGER = Logger.getLogger(CreateEntityAdmin.class);
    private static final ActionResult USER_ADMIN_PAGE = new ActionResult(ActionResult.METHOD.REDIRECT, Constants.ACTION_CABINET);
    private static final ActionResult PRODUCTS_ADMIN_PAGE = new ActionResult(ActionResult.METHOD.REDIRECT, Constants.ACTION_PRODUCTS);
    private static final ActionResult SIZES_ADMIN_PAGE = new ActionResult(ActionResult.METHOD.REDIRECT, Constants.ACTION_SIZES);
    private static final ActionResult TYPES_ADMIN_PAGE = new ActionResult(ActionResult.METHOD.REDIRECT, Constants.ACTION_TYPES);
    private static final ActionResult ORDER_DETAILS_ADMIN_PAGE = new ActionResult(ActionResult.METHOD.REDIRECT, Constants.ATTRIBUTE_ORDER_DETAILS);
    private static final ActionResult ORDER_ADMIN_PAGE = new ActionResult(ActionResult.METHOD.REDIRECT, Constants.ACTION_ORDERS);
    private HttpSession session;
    private String entityName;
    private ActionResult result;
    private static HttpServletRequest req;

    public CreateEntityAdmin(String entityName) {
        this.entityName = entityName;
    }

    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {
        req = request;
        session = request.getSession();
        if (entityName.equals(Constants.CREATE_STRING_USER)) createUser();
        if (entityName.equals(Constants.CREATE_STRING_PRODUCT)) createProduct();
        if (entityName.equals(Constants.CREATE_STRING_SIZE)) createSize();
        if (entityName.equals(Constants.CREATE_STRING_TYPE)) createType();
        if (entityName.equals(Constants.CREATE_STRING_ORDER_DETAILS)) createOrderDetails();
        if (entityName.equals(Constants.CREATE_STRING_ORDER)) createOrder();
        return result;
    }

    private void createOrder() throws ActionException {
        Order order = new Order();
        setOrder(order, req);
        try {
            OrderDao orderDao = new OrderDao();
            orderDao.create(order);
            AttributeSetter setter = new AttributeSetter();
            setter.setAttributes(session);
        } catch (Exception e) {
            LOGGER.error("Error at create Order()", e);
            throw new ActionException("Error at createOrder()", e);
        }
        result = ORDER_ADMIN_PAGE;
    }

    private void createOrderDetails() throws ActionException {
        OrderDetails orderDetails = new OrderDetails();
        setOrderDetails(orderDetails, req);
        try {
            OrderDetailsDao orderDetailsDao = new OrderDetailsDao();
            orderDetailsDao.create(orderDetails);
        } catch (Exception e) {
            LOGGER.error("Error at create OrderDetailsDAO", e);
            throw new ActionException(e);
        }
        result = ORDER_DETAILS_ADMIN_PAGE;
    }

    private void createType() throws ActionException {
        Type type = new Type();
        setType(type, req);
        try {
            TypeDao typeDao = new TypeDao();
            typeDao.create(type);
            AttributeSetter setter = new AttributeSetter();
            setter.setAttributes(session);
        } catch (Exception e) {
            LOGGER.error("Error at create TypeDAO", e);
            throw new ActionException(e);
        }
        result = TYPES_ADMIN_PAGE;
    }

    private void createSize() throws ActionException {
        Size size = new Size();
        setSize(size, req);
        try {
            SizeDao sizeDao = new SizeDao();
            sizeDao.create(size);
            AttributeSetter setter = new AttributeSetter();
            setter.setAttributes(session);
        } catch (Exception e) {
            LOGGER.error("Error at create SizeDAO", e);
            throw new ActionException(e);
        }
        result = SIZES_ADMIN_PAGE;
    }

    private void createProduct() throws ActionException {
        Food food = new Food();
        setFood(food, req);
        try {
            FoodDao foodDao = new FoodDao();
            foodDao.create(food);
            AttributeSetter setter = new AttributeSetter();
            setter.setAttributes(session);
        } catch (Exception e) {
            LOGGER.error("Error at create FoodDAO", e);
            throw new ActionException(e);
        }
        result = PRODUCTS_ADMIN_PAGE;
    }

    private void createUser() throws ActionException {
        String userName = req.getParameter(Constants.ATTRIBUTE_USERNAME);
        boolean userNameValid = false;
        try {
            userNameValid = FieldsValidator.userNameCheck(userName);
        } catch (ValidationException e) {
            LOGGER.error("Error in validation", e);
        }
        if (!userNameValid) {
            session.setAttribute(Constants.ATTRIBUTE_ERROR, Constants.USER_EXIST_ERROR);
            LOGGER.error("Username already busy");
            result = USER_ADMIN_PAGE;
        } else {
            User user = new User();
            setUser(user, userName, req);
            try {
                UserDao userDao = new UserDao();
                userDao.create(user);
            } catch (Exception e) {
                LOGGER.error("Error at create UserDAO", e);
                throw new ActionException(e);
            }
            result = USER_ADMIN_PAGE;
        }
    }

    static Food setFood(Food food, HttpServletRequest req) {
        Integer typeId = Integer.parseInt(req.getParameter(Constants.ATTRIBUTE_TYPE_ID));
        String nameRu = req.getParameter(Constants.ATTRIBUTE_NAME_RU);
        String nameEn = req.getParameter(Constants.ATTRIBUTE_NAME_EN);
        String discriptionRu = req.getParameter(Constants.ATTRIBUTE_COMPOS_RU);
        String dicriptionEn = req.getParameter(Constants.ATTRIBUTE_COMPOS_EN);
        Integer price = Integer.parseInt(req.getParameter(Constants.ATTRIBUTE_PRICE));
        String img = req.getParameter(Constants.ATTRIBUTE_IMG_PATH);
        Boolean active = Boolean.parseBoolean(req.getParameter(Constants.ATTRIBUTE_ACTIVE));
        food.setPrice(price);
        food.setImg(img);
        food.setDiscriptionEn(dicriptionEn);
        food.setDiscriptionRu(discriptionRu);
        food.setNameEn(nameEn);
        food.setNameRu(nameRu);
        food.setTypeId(typeId);
        food.setActive(active);
        return food;
    }

    static User setUser(User user, String userName, HttpServletRequest req) {
        String email = req.getParameter(Constants.ATTRIBUTE_EMAIL);
        String firstName = req.getParameter(Constants.ATTRIBUTE_FIRSTNAME);
        String lastName = req.getParameter(Constants.ATTRIBUTE_LASTNAME);
        Integer balance = Integer.parseInt(req.getParameter(Constants.ATTRIBUTE_BALANCE));
        String password = req.getParameter(Constants.ATTRIBUTE_PASSWORD);
        Role role = Role.valueOf(req.getParameter(Constants.ATTRIBUTE_ROLE));
        user.setFirstname(firstName);
        user.setLastname(lastName);
        user.setEmail(email);
        user.setUsername(userName);
        user.setPassword(password);
        user.setBalance(balance);
        user.setRole(role);
        return user;
    }

    static OrderDetails setOrderDetails(OrderDetails orderDetails, HttpServletRequest req) {
        Integer foodId = Integer.parseInt(req.getParameter(Constants.ATTRIBUTE_PRODUCT_ID));
        String foodNameRu = req.getParameter(Constants.ATTRIBUTE_NAME_RU);
        String foodNameEn = req.getParameter(Constants.ATTRIBUTE_NAME_EN);
        String sizeName = req.getParameter(Constants.ATTRIBUTE_SIZE_NAME);
        Integer typeId = Integer.parseInt(req.getParameter(Constants.ATTRIBUTE_TYPE_ID));
        String typeName = req.getParameter(Constants.ATTRIBUTE_TYPE_NAME);
        Integer quantity = Integer.parseInt(req.getParameter(Constants.ATTRIBUTE_COUNT));
        Integer orderId = Integer.parseInt(req.getParameter(Constants.ATTRIBUTE_ORDER_ID));
        Integer price = Integer.parseInt(req.getParameter(Constants.ATTRIBUTE_PRICE));
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
        return orderDetails;
    }

    static Order setOrder(Order order, HttpServletRequest req) {
        Integer sumOfOrder = Integer.parseInt(req.getParameter(Constants.ATTRIBUTE_ORDER_SUM));
        Integer userId = Integer.parseInt(req.getParameter(Constants.ATTRIBUTE_USER_ID));
        String address = req.getParameter(Constants.ATTRIBUTE_ADDRESS);
        String phone = req.getParameter(Constants.ATTRIBUTE_PHONE);
        Status status = Status.valueOf(req.getParameter(Constants.ATTRIBUTE_STATUS));
        order.setSumOfOrder(sumOfOrder);
        order.setUserId(userId);
        order.setAddress(address);
        order.setPhone(phone);
        order.setStatus(status);
        return order;
    }

    static Type setType(Type type, HttpServletRequest req) {
        String value = req.getParameter(Constants.ATTRIBUTE_TYPE);
        Boolean active = Boolean.parseBoolean(req.getParameter(Constants.ATTRIBUTE_ACTIVE));
        type.setType(value);
        type.setActive(active);
        return type;
    }

    static Size setSize(Size size, HttpServletRequest req) {
        Integer value = Integer.parseInt(req.getParameter(Constants.ATTRIBUTE_VAL));
        String name = req.getParameter(Constants.ATTRIBUTE_NAME);
        Boolean active = Boolean.parseBoolean(req.getParameter(Constants.ATTRIBUTE_ACTIVE));
        size.setSize(value);
        size.setName(name);
        size.setActive(active);
        return size;
    }


}
