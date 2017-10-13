package kz.javalab.va.action;


import kz.javalab.va.action.account.*;
import kz.javalab.va.action.admin.*;
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
    private static final String ADMIN_PAGE_TYPES = "admin_types";
    private static final String ERROR = "error";
    private static final String USER = "user";
    private static final String PRODUCT = "product";
    private static final String SIZE = "size";
    private static final String TYPE = "type";
    private static final String ORDER_DETAILS = "orderDetails";
    private static final String ORDER = "order";
    private static final Object ERROR_NOT_FOUND = "error.notFound";

    static {
        ACTIONS.put("GET/subs", new ShowPageAction(PAGE_SUBS));
        ACTIONS.put("GET/pizza", new ShowPageAction(PAGE_PIZZA));
        ACTIONS.put("GET/beverage", new ShowPageAction(PAGE_BEVS));
        ACTIONS.put("GET/subs_loged", new ShowPageAction(PAGE_SUBS_LOGED));
        ACTIONS.put("GET/pizza_loged", new ShowPageAction(PAGE_PIZZA_LOGED));
        ACTIONS.put("GET/beverage_loged", new ShowPageAction(PAGE_BEVS_LOGED));
        ACTIONS.put("GET/user_cabinet", new ShowPageAction(PAGE_CABINET));

        /*Admin pages*/
        ACTIONS.put("GET/cabinet", new ShowCabinetPageAction());
        ACTIONS.put("GET/products", new ShowProductsPageAction());
        ACTIONS.put("GET/orders", new ShowOrderPageAction());
        ACTIONS.put("GET/order_details", new ShowOrderDetailsPageAction());
        ACTIONS.put("GET/sizes", new ShowSizesPageAction());
        ACTIONS.put("GET/types", new ShowPageAction(ADMIN_PAGE_TYPES));

        ACTIONS.put("GET/error", new ShowPageAction(ERROR));
        ACTIONS.put("GET/locale", new ChangeLocaleAction());
        ACTIONS.put("GET/registered", new ShowPageAction(PAGE_REGISTERED));
        ACTIONS.put("GET/logout", new LogoutAction());
/*Order actions*/
        ACTIONS.put("GET/add_to_orderlist", new AddToOrderAction());
        ACTIONS.put("GET/del_from_orderlist", new DelFromOrderListAction());


        ACTIONS.put("POST/register", new RegisterAction());
        ACTIONS.put("POST/login", new LoginAction());
        ACTIONS.put("POST/info_update", new UserInfoUpdateAction());
        ACTIONS.put("POST/pass_update", new UserPassUpdateAction());
        ACTIONS.put("POST/chekout_order", new CheckOutOrder());

        /*Admin post actions*/
        ACTIONS.put("POST/edit_user", new EditUserAction());
        ACTIONS.put("POST/edit_product", new EditProductAction());
        ACTIONS.put("POST/edit_sizes", new EditSizeAction());
        ACTIONS.put("POST/edit_types", new EditTypeAction());
        ACTIONS.put("POST/edit_order_details", new EditOrderDetailsAction());
        ACTIONS.put("POST/edit_order", new EditOrderAction());
        ACTIONS.put("POST/create_user", new CreateEntityAdmin(USER));
        ACTIONS.put("POST/create_product", new CreateEntityAdmin(PRODUCT));
        ACTIONS.put("POST/create_size", new CreateEntityAdmin(SIZE));
        ACTIONS.put("POST/create_type", new CreateEntityAdmin(TYPE));
        ACTIONS.put("POST/create_order_details", new CreateEntityAdmin(ORDER_DETAILS));
        ACTIONS.put("POST/create_order", new CreateEntityAdmin(ORDER));
    }

    public static Action getAction(HttpServletRequest request) {
        HttpSession session;
        String req = request.getMethod() + request.getPathInfo();
        Action action = ACTIONS.get(req);
        if (action == null) {
            action = ACTIONS.get("GET/error");
            session = request.getSession();
            session.setAttribute(ERROR, ERROR_NOT_FOUND);
            LOGGER.debug("Unknown request.");
            return action;
        }
        return action;
    }

}
