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
import kz.javalab.va.util.validator.FieldsValidator;
import kz.javalab.va.util.validator.ValidationException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CreateEntityAdmin implements Action {
    private static final Logger LOGGER = Logger.getLogger(CreateEntityAdmin.class);
    private static final ActionResult USER_ADMIN_PAGE = new ActionResult(ActionResult.METHOD.REDIRECT, "cabinet");
    private static final ActionResult PRODUCTS_ADMIN_PAGE = new ActionResult(ActionResult.METHOD.REDIRECT, "products");
    private static final ActionResult SIZES_ADMIN_PAGE = new ActionResult(ActionResult.METHOD.REDIRECT, "sizes");
    private static final ActionResult TYPES_ADMIN_PAGE = new ActionResult(ActionResult.METHOD.REDIRECT, "types");
    private static final ActionResult ORDER_DETAILS_ADMIN_PAGE = new ActionResult(ActionResult.METHOD.REDIRECT, "order_details");
    private static final ActionResult ORDER_ADMIN_PAGE = new ActionResult(ActionResult.METHOD.REDIRECT, "orders");
    private static final String ERROR_MESSAGE_ATTRIBUTE = "RegisterErrorMessageKey";
    private static final String USER_EXIST = "user_exist";
    private static final String USER = "user";
    private static final String PRODUCT = "product";
    private static final String SIZE = "size";
    private static final String TYPE = "type";
    private static final String ORDER_DETAILS = "orderDetails";
    private static final String ORDER = "order";
    private static final String ORDER_SUM = "sumOfOrder";
    private static final String USER_ID = "userId";
    private static final String ADDRESS = "address";
    private static final String PHONE = "phone";
    private static final String STATUS = "status";
    private static final String PRODUCT_ID = "productId";
    private static final String NAME_RU = "nameRu";
    private static final String NAME_EN = "nameEn";
    private static final String SIZE_NAME = "sizeName";
    private static final String TYPE_ID = "type_Id";
    private static final String TYPE_NAME = "typeName";
    private static final String COUNT = "count";
    private static final String ORDER_ID = "order_Id";
    private static final String PRICE = "price";
    private static final String NAME = "name";
    private static final String ACTIVE = "active";
    private static final String COMPOS_RU = "composRu";
    private static final String COMPOS_EN = "composEn";
    private static final String IMG_PATH = "imgPath";
    private static final String USERNAME = "user_name";
    private static final String EMAIL = "email";
    private static final String FIRSTNAME = "firstname";
    private static final String LASTNAME = "lastname";
    private static final String BALANCE = "balance";
    private static final String PASSWORD = "password";
    private static final String ROLE = "role";
    private static final String VAL = "value";


    private HttpSession session;
    private String entityName;
    private ActionResult result;
    private HttpServletRequest req;

    public CreateEntityAdmin(String entityName) {
        this.entityName = entityName;
    }

    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {
        req = request;
        session = request.getSession();
        if (entityName.equals(USER)) createUser();
        if (entityName.equals(PRODUCT)) createProduct();
        if (entityName.equals(SIZE)) createSize();
        if (entityName.equals(TYPE)) createType();
        if (entityName.equals(ORDER_DETAILS)) createOrderDetails();
        if (entityName.equals(ORDER)) createOrder();
        return result;
    }

    private void createOrder() throws ActionException {
        Integer sumOfOrder = Integer.parseInt(req.getParameter(ORDER_SUM));
        Integer userId = Integer.parseInt(req.getParameter(USER_ID));
        String address = req.getParameter(ADDRESS);
        String phone = req.getParameter(PHONE);
        Status status = Status.valueOf(req.getParameter(STATUS));

        Order order = new Order();
        order.setSumOfOrder(sumOfOrder);
        order.setUserId(userId);
        order.setAddress(address);
        order.setPhone(phone);
        order.setStatus(status);

        try {
            OrderDao orderDao = new OrderDao();
            orderDao.create(order);
            LOGGER.info("New order was created");
            AttributeSetter setter = new AttributeSetter();
            setter.setAttributes(session);
        } catch (Exception e) {
            LOGGER.error("Error at create Order()", e);
            throw new ActionException("Error at createOrder()", e);
        }
        result = ORDER_ADMIN_PAGE;
    }

    private void createOrderDetails() throws ActionException {
        Integer foodId = Integer.parseInt(req.getParameter(PRODUCT_ID));
        String foodNameRu = req.getParameter(NAME_RU);
        String foodNameEn = req.getParameter(NAME_EN);
        String sizeName = req.getParameter(SIZE_NAME);
        Integer typeId = Integer.parseInt(req.getParameter(TYPE_ID));
        String typeName = req.getParameter(TYPE_NAME);
        Integer quantity = Integer.parseInt(req.getParameter(COUNT));
        Integer orderId = Integer.parseInt(req.getParameter(ORDER_ID));
        Integer price = Integer.parseInt(req.getParameter(PRICE));

        OrderDetails orderDetails = new OrderDetails();
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

        try {
            OrderDetailsDao orderDetailsDao = new OrderDetailsDao();
            orderDetailsDao.create(orderDetails);
            LOGGER.info("New Order Details was created");
        } catch (Exception e) {
            LOGGER.error("Error at create Order Details()", e);
            throw new ActionException("Error at createOrderDetails()", e);
        }
        result = ORDER_DETAILS_ADMIN_PAGE;
    }

    private void createType() throws ActionException {
        String value = req.getParameter(TYPE);
        Boolean active = Boolean.parseBoolean(req.getParameter("active"));

        Type type = new Type();
        type.setType(value);
        type.setActive(active);

        try {
            TypeDao typeDao = new TypeDao();
            typeDao.create(type);
            LOGGER.info("New Type was created");
            AttributeSetter setter = new AttributeSetter();
            setter.setAttributes(session);
        } catch (Exception e) {
            LOGGER.error("Error at create Type()", e);
            throw new ActionException("Error at createType()", e);
        }
        result = TYPES_ADMIN_PAGE;
    }

    private void createSize() throws ActionException {
        Integer value = Integer.parseInt(req.getParameter(VAL));
        String name = req.getParameter(NAME);
        Boolean active = Boolean.parseBoolean(req.getParameter(ACTIVE));

        Size size = new Size();
        size.setSize(value);
        size.setName(name);
        size.setActive(active);

        try {
            SizeDao sizeDao = new SizeDao();
            sizeDao.create(size);
            LOGGER.info("New Size was created");
            AttributeSetter setter = new AttributeSetter();
            setter.setAttributes(session);
        } catch (Exception e) {
            LOGGER.error("Error at create Size()", e);
            throw new ActionException("Error at createSize()", e);
        }
        result = SIZES_ADMIN_PAGE;
    }

    private void createProduct() throws ActionException {
        Integer type = Integer.parseInt(req.getParameter(TYPE_ID));
        String nameRu = req.getParameter(NAME_RU);
        String nameEn = req.getParameter(NAME_EN);
        String discriptionRu = req.getParameter(COMPOS_RU);
        String dicriptionEn = req.getParameter(COMPOS_EN);
        Integer price = Integer.parseInt(req.getParameter(PRICE));
        String img = req.getParameter(IMG_PATH);
        Boolean active = Boolean.parseBoolean(req.getParameter(ACTIVE));

        Food food = new Food();
        food.setPrice(price);
        food.setImg(img);
        food.setDiscriptionEn(dicriptionEn);
        food.setDiscriptionRu(discriptionRu);
        food.setNameEn(nameEn);
        food.setNameRu(nameRu);
        food.setTypeId(type);
        food.setActive(active);

        try {
            FoodDao foodDao = new FoodDao();
            foodDao.create(food);
            LOGGER.info("New Product was created");
            AttributeSetter setter = new AttributeSetter();
            setter.setAttributes(session);
        } catch (Exception e) {
            LOGGER.error("Error at create Food()", e);
            throw new ActionException("Error at createFood()", e);
        }
        result = PRODUCTS_ADMIN_PAGE;
    }

    private void createUser() throws ActionException {
        String userName = req.getParameter(USERNAME);
        String email = req.getParameter(EMAIL);
        String firstName = req.getParameter(FIRSTNAME);
        String lastName = req.getParameter(LASTNAME);
        Integer balance = Integer.parseInt(req.getParameter(BALANCE));
        String password = req.getParameter(PASSWORD);
        Role role = Role.valueOf(req.getParameter(ROLE));
        boolean userNameValid = false;

        try {
            userNameValid = FieldsValidator.userNameCheck(userName);
        } catch (ValidationException e) {
            LOGGER.error("Error in validation");
            e.printStackTrace();
        }
        if (!userNameValid) {
            session.setAttribute(ERROR_MESSAGE_ATTRIBUTE, USER_EXIST);
            LOGGER.error("Username already busy");
            result = USER_ADMIN_PAGE;
        } else {
            User user = new User();
            user.setFirstname(firstName);
            user.setLastname(lastName);
            user.setEmail(email);
            user.setUsername(userName);
            user.setPassword(password);
            user.setBalance(balance);
            user.setRole(role);
            try {
                UserDao userDao = new UserDao();
                userDao.create(user);
            } catch (Exception e) {
                LOGGER.error("Error at createUser()", e);
                throw new ActionException("Error at createUser()", e);
            }
            result = USER_ADMIN_PAGE;
        }
    }
}
