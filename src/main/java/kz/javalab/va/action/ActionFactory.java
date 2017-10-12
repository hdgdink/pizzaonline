package kz.javalab.va.action;


import kz.javalab.va.action.account.*;
import kz.javalab.va.action.admin.CreateEntityAdmin;
import kz.javalab.va.action.admin.EditProductAction;
import kz.javalab.va.action.admin.EditUserAction;
import kz.javalab.va.action.admin.ShowProductsPageAction;
import kz.javalab.va.action.general.ChangeLocaleAction;
import kz.javalab.va.action.general.ShowCabinetPageAction;
import kz.javalab.va.action.general.ShowPageAction;
import kz.javalab.va.action.order.AddToOrderAction;
import kz.javalab.va.action.order.CheckOutOrder;
import kz.javalab.va.action.order.DelFromOrderListAction;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

public class ActionFactory {
    private static final Logger LOGGER = Logger.getLogger(ActionFactory.class);
    private static final Map<String, Action> ACTIONS = new HashMap<>();
    private static final String PAGE_SUBS = "subs_unreg";
    private static final String PAGE_PIZZA = "pizza_unreg";
    private static final String PAGE_BEVS = "bev_unreg";
    private static final String PAGE_SUBS_LOGED = "subs_loged";
    private static final String PAGE_PIZZA_LOGED = "pizza_loged";
    private static final String PAGE_BEVS_LOGED = "bev_loged";
    private static final String PAGE_REGISTERED = "success";
    private static final String PAGE_CABINET = "user_cabinet";
    private static final String ADMIN_PAGE_ORDERS = "admin_orders";
    private static final String ADMIN_PAGE_ORDER_DETAILS = "admin_order_details";
    private static final String ADMIN_PAGE_SIZES = "admin_sizes";
    private static final String ADMIN_PAGE_TYPES = "admin_types";
    private static final String ERROR = "error";
    private static final String USER = "user";
    private static final String PRODUCT = "product";

    static {
        ACTIONS.put("GET/subs", new ShowPageAction(PAGE_SUBS));
        ACTIONS.put("GET/pizza", new ShowPageAction(PAGE_PIZZA));
        ACTIONS.put("GET/beverage", new ShowPageAction(PAGE_BEVS));
        ACTIONS.put("GET/subs_loged", new ShowPageAction(PAGE_SUBS_LOGED));
        ACTIONS.put("GET/pizza_loged", new ShowPageAction(PAGE_PIZZA_LOGED));
        ACTIONS.put("GET/beverage_loged", new ShowPageAction(PAGE_BEVS_LOGED));
        ACTIONS.put("GET/user_cabinet", new ShowPageAction(PAGE_CABINET));

        ACTIONS.put("GET/cabinet", new ShowCabinetPageAction());
        ACTIONS.put("GET/products", new ShowProductsPageAction());
        ACTIONS.put("GET/orders", new ShowPageAction(ADMIN_PAGE_ORDERS));
        ACTIONS.put("GET/order_details", new ShowPageAction(ADMIN_PAGE_ORDER_DETAILS));
        ACTIONS.put("GET/sizes", new ShowPageAction(ADMIN_PAGE_SIZES));
        ACTIONS.put("GET/types", new ShowPageAction(ADMIN_PAGE_TYPES));

        ACTIONS.put("GET/error", new ShowPageAction(ERROR));
        ACTIONS.put("GET/locale", new ChangeLocaleAction());
        ACTIONS.put("GET/registered", new ShowPageAction(PAGE_REGISTERED));
        ACTIONS.put("GET/logout", new LogoutAction());

        ACTIONS.put("GET/add_to_orderlist", new AddToOrderAction());
        ACTIONS.put("GET/del_from_orderlist", new DelFromOrderListAction());

        ACTIONS.put("POST/register", new RegisterAction());
        ACTIONS.put("POST/login", new LoginAction());
        ACTIONS.put("POST/info_update", new UserInfoUpdateAction());
        ACTIONS.put("POST/pass_update", new UserPassUpdateAction());
        ACTIONS.put("POST/chekout_order", new CheckOutOrder());
        ACTIONS.put("POST/edit_user", new EditUserAction());
        ACTIONS.put("POST/edit_product", new EditProductAction());
        ACTIONS.put("POST/create_user", new CreateEntityAdmin(USER));
        ACTIONS.put("POST/create_product", new CreateEntityAdmin(PRODUCT));

    }

    public static Action getAction(HttpServletRequest request) {
        HttpSession session;
        String req = request.getMethod() + request.getPathInfo();
        Action action = ACTIONS.get(req);
        if (action == null) {
            action = ACTIONS.get("GET/error");
            session = request.getSession();
            session.setAttribute("error", "error.notFound");
            LOGGER.debug("Unknown request.");
            return action;
        }
        return action;
    }

}
