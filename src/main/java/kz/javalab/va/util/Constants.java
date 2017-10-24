package kz.javalab.va.util;

import java.util.Locale;
import java.util.ResourceBundle;

public class Constants {

    /* Attributes*/
    public static final String ATTRIBUTE_ROLES_LIST = "roles";
    public static final String ATTRIBUTE_STATUS_LIST = "statusList";
    public static final String ATTRIBUTE_TYPE_LIST = "typeList";
    public static final String ATTRIBUTE_SIZE_LIST = "sizeList";
    public static final String ATTRIBUTE_PIZZA_LIST = "pizzaList";
    public static final String ATTRIBUTE_BEV_LIST = "bevList";
    public static final String ATTRIBUTE_SUBS_LIST = "subList";
    public static final String ATTRIBUTE_ERROR = "error";
    public static final String ATTRIBUTE_LOGIN_ERROR = "logInError";
    public static final String ATTRIBUTE_PASSWORD_ERROR = "passwordError";
    public static final String ATTRIBUTE_OLD_PASSWORD_ERROR = "oldPasswordError";
    public static final String ATTRIBUTE_LOCALE = "locale";
    public static final String ATTRIBUTE_USER = "user";
    public static final String ATTRIBUTE_ORDER_DETAILS = "order_details";
    public static final String ATTRIBUTE_ORDER = "order";
    public static final String ATTRIBUTE_USERNAME = "username";
    public static final String ATTRIBUTE_PASSWORD = "password";
    public static final String ATTRIBUTE_ID = "id";
    public static final String ATTRIBUTE_FINALPRICE = "finalPrice";
    public static final String ATTRIBUTE_EMAIL = "email";
    public static final String ATTRIBUTE_QNT = "quantity";
    public static final String ATTRIBUTE_SIZE = "size";
    public static final String ATTRIBUTE_ALL_SIZES_ADMIN = "allSizesAdmin";
    public static final String ATTRIBUTE_FIRSTNAME = "firstname";
    public static final String ATTRIBUTE_LASTNAME = "lastname";
    public static final String ATTRIBUTE_RE_PASSWORD = "re-password";
    public static final String ATTRIBUTE_REGISTER_ERROR = "registerError";
    public static final String ATTRIBUTE_REGISTER_SUCCESS_KEY = "registerSuccess";
    public static final String ATTRIBUTE_REGISTRATION_SUCCESS_MESSAGE = "success_registration";
    public static final String ATTRIBUTE_OLD_PASSWORD = "oldPassword";
    public static final String ATTRIBUTE_NEW_PASSWORD1 = "newPassword1";
    public static final String ATTRIBUTE_NEW_PASSWORD2 = "newPassword2";
    public static final String ATTRIBUTE_CHANGE_SUCCESS_MESSAGE = "password.changePasswordSuccess";
    public static final String ATTRIBUTE_CHANGE_PASS_SUCCESS = "changePassSuccess";
    public static final String ATTRIBUTE_TYPE = "type";
    public static final String ATTRIBUTE_ORDER_SUM = "sumOfOrder";
    public static final String ATTRIBUTE_USER_ID = "userId";
    public static final String ATTRIBUTE_ADDRESS = "address";
    public static final String ATTRIBUTE_PHONE = "phone";
    public static final String ATTRIBUTE_STATUS = "status";
    public static final String ATTRIBUTE_PRODUCT_ID = "productId";
    public static final String ATTRIBUTE_NAME_RU = "nameRu";
    public static final String ATTRIBUTE_NAME_EN = "nameEn";
    public static final String ATTRIBUTE_SIZE_NAME = "sizeName";
    public static final String ATTRIBUTE_TYPE_ID = "typeId";
    public static final String ATTRIBUTE_TYPE_NAME = "typeName";
    public static final String ATTRIBUTE_COUNT = "count";
    public static final String ATTRIBUTE_ORDER_ID = "orderId";
    public static final String ATTRIBUTE_PRICE = "price";
    public static final String ATTRIBUTE_NAME = "name";
    public static final String ATTRIBUTE_ACTIVE = "active";
    public static final String ATTRIBUTE_COMPOS_RU = "composRu";
    public static final String ATTRIBUTE_COMPOS_EN = "composEn";
    public static final String ATTRIBUTE_IMG_PATH = "imgPath";
    public static final String ATTRIBUTE_BALANCE = "balance";
    public static final String ATTRIBUTE_ROLE = "role";
    public static final String ATTRIBUTE_VAL = "value";
    public static final String ATTRIBUTE_DELETE = "delete";
    public static final String ATTRIBUTE_UPDATE = "update";
    public static final String ATTRIBUTE_ALL_ORDER_DETAILS_LIST = "allOrderDetails";
    public static final String ATTRIBUTE_ALL_ORDERS_LIST = "orderList";
    public static final String ATTRIBUTE_PRODUCT_LIST = "products_list";
    public static final String ATTRIBUTE_USER_LIST = "user_list";
    public static final String ATTRIBUTE_FOOD = "food";
    public static final String ATTRIBUTE_ORDER_DETAILS_ID = "order_detail_id";
    public static final String ATTRIBUTE_ORDER_DETAILS_FINAL_PRICE = "order_detail_final_price";

    /*Parameters*/
    public static final String PAR_TOPIC = "topic";

    /*Resource bundle*/
    private static final ResourceBundle RB_VALIDATOR = ResourceBundle.getBundle("validator");
    private static final ResourceBundle RB_DB = ResourceBundle.getBundle("db");
    public static final String EMAIL_REGEX_RB = RB_VALIDATOR.getString("validation.email");
    public static final String PASSWORD_REGEX_RB = RB_VALIDATOR.getString("validation.password");
    public static final String DRIVER_RB = RB_DB.getString("db.driver");
    public static final String URL_RB = RB_DB.getString("db.url");
    public static final String USER_RB = RB_DB.getString("db.user");
    public static final String PASSWORD_RB = RB_DB.getString("db.password");
    public static final int POOL_SIZE_RB = Integer.parseInt(RB_DB.getString("db.pool_size"));

    /*Errors*/
    public static final String JAVAX_ERROR = "javax.servlet.error.exception";
    public static final String BAD_REQUEST_ERROR = "error.badRequest";
    public static final String ACTION_FAILED_ERROR = "error.actionFailed ";
    public static final String SERVER_ERROR = "error.serverError";
    public static final String ACCESS_DENIED_ERROR = "error.accessDenied";
    public static final String NOT_FOUND_ERROR = "error.notFound";
    public static final String ACCOUNT_NOT_FOUND_ERROR = "account.notFound";
    public static final String ACCOUNT_IS_BAD_ERROR = "account.isBad";
    public static final String BALANCE_IS_LOW_ERROR = "account.BalanceIsLow";
    public static final String PASSWORDS_NOT_MATCH_ERROR = "error.passwordsNotMatch";
    public static final String USER_EXIST_ERROR = "error.userExist";
    public static final String OLD_PASS_WRONG_ERROR = "password.oldPasswordWrong";
    public static final String PASS_EMPTY_ERROR = "password.passwordEmpty";
    public static final String USER_NOT_LOGGED_ERROR = "error.UserIsNotLogged";

    /*Strings*/
    public static final String JSP_STRING = ".jsp";
    public static final String WEB_INF_STRING = "/WEB-INF/";
    public static final String DO_STRING = "/do/";
    public static final String STATIC_STRING = "/static/";
    public static final String WEBJARS_STRING = "/webjars/";
    public static final String UTF_8 = "UTF-8";

    /*Columns of Tables*/
    public static final String ID_COL = "ID";
    public static final String ACTIVE_COL = "ACTIVE";
    //FOOD
    public static final String TYPE_ID_COL = "TYPE_ID";
    public static final String NAME_RU_COL = "NAME_RU";
    public static final String NAME_EN_COL = "NAME_EN";
    public static final String COMPOS_RU_COL = "COMPOS_RU";
    public static final String COMPOS_EN_COL = "COMPOS_EN";
    public static final String PRICE_COL = "PRICE";
    public static final String IMG_COL = "IMG";
    //ORDER
    public static final String STATUS_COL = "STATUS";
    public static final String USER_ID_COL = "USER_ID";
    public static final String SUM_COL = "SUM";
    public static final String ADDRESS_COL = "ADDRESS";
    public static final String PHONE_COL = "PHONE";
    public static final String MAX_ID_COL = "MAX(ID)";
    //ORDER DETAILS
    public static final String FOOD_ID_COL = "FOOD_ID";
    public static final String FOOD_NAME_RU_COL = "FOOD_NAME_RU";
    public static final String FOOD_NAME_EN_COL = "FOOD_NAME_EN";
    public static final String ORDER_ID_COL = "ORDER_ID";
    public static final String TYPE_NAME_COL = "TYPE_NAME";
    public static final String SIZE_NAME_COL = "SIZE_NAME";
    public static final String QNT_COL = "QUANTITY";
    //SIZE
    public static final String SIZE_COL = "SIZE";
    public static final String NAME_COL = "NAME";
    //TYPE
    public static final String TYPE_COL = "TYPE";
    //USER
    public static final String FIRSTNAME_COL = "FIRSTNAME";
    public static final String LASTNAME_COL = "LASTNAME";
    public static final String USERNAME_COL = "USERNAME";
    public static final String EMAIL_COL = "EMAIL";
    public static final String PASSWORD_COL = "PASSWORD";
    public static final String ROLE_COL = "ROLE";
    public static final String BALANCE_COL = "BALANCE";

    /* Messages fo logger*/
    public static final String STATEMENT_CREATE_ERROR = "Statement cannot be created.";

    /*Pages*/
    public static final String PAGE_REFERER = "referer";
    public static final String PAGE_SUBS_UNLOG = "subs_unreg";
    public static final String PAGE_PIZZA_UNLOG = "pizza_unreg";
    public static final String PAGE_BEVS_UNLOG = "bev_unreg";
    public static final String PAGE_SUBS_LOGGED = "subs_logged";
    public static final String PAGE_PIZZA_LOGGED = "pizza_logged";
    public static final String PAGE_BEVS_LOGGED = "bev_logged";
    public static final String PAGE_CABINET = "user_cabinet";
    public static final String PAGE_ADMIN_CABINET = "admin_cabinet";
    public static final String PAGE_ADMIN_TYPES = "admin_types";
    public static final String PAGE_ERROR = "error";
    public static final String ACTION_CABINET = "cabinet";
    public static final String ACTION_PIZZA = "pizza";
    public static final String ACTION_PRODUCTS = "products";
    public static final String ACTION_SIZES = "sizes";
    public static final String ACTION_TYPES = "types";
    public static final String ACTION_ORDERS = "orders";
    public static final String ACTION_ADMIN_ORDER_DETAILS = "admin_order_details";
    public static final String ACTION_ADMIN_ORDERS = "admin_orders";
    public static final String ACTION_ADMIN_PRODUCTS = "admin_products";
    public static final String ACTION_ADMIN_SIZES = "admin_sizes";

    /* Strings for create entity*/
    public static final String CREATE_STRING_USER = "createUser";
    public static final String CREATE_STRING_PRODUCT = "createProduct";
    public static final String CREATE_STRING_SIZE = "createSize";
    public static final String CREATE_STRING_TYPE = "createType";
    public static final String CREATE_STRING_ORDER_DETAILS = "createOrderDetails";
    public static final String CREATE_STRING_ORDER = "createOrder";

    public static final Locale DEFAULT_LOCALE = Locale.getDefault();

}
