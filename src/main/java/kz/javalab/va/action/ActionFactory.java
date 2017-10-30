package kz.javalab.va.action;

import kz.javalab.va.action.account.*;
import kz.javalab.va.action.admin.*;
import kz.javalab.va.action.general.ChangeLocaleAction;
import kz.javalab.va.action.general.DeleteErrorAction;
import kz.javalab.va.action.general.ShowCabinetPageAction;
import kz.javalab.va.action.general.ShowPageAction;
import kz.javalab.va.action.order.AddToOrderAction;
import kz.javalab.va.action.order.CheckOutOrder;
import kz.javalab.va.action.order.DelFromOrderListAction;
import kz.javalab.va.util.Constants;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

public class ActionFactory {
    private static final Logger LOGGER = Logger.getLogger(ActionFactory.class);
    private static final Map<String, Action> ACTIONS = new HashMap<>();

    static {
        ACTIONS.put("GET/subs", new ShowPageAction(Constants.PAGE_SUBS_UNLOG));
        ACTIONS.put("GET/pizza", new ShowPageAction(Constants.PAGE_PIZZA_UNLOG));
        ACTIONS.put("GET/beverage", new ShowPageAction(Constants.PAGE_BEVS_UNLOG));
        ACTIONS.put("GET/subs_logged", new ShowPageAction(Constants.PAGE_SUBS_LOGGED));
        ACTIONS.put("GET/pizza_logged", new ShowPageAction(Constants.PAGE_PIZZA_LOGGED));
        ACTIONS.put("GET/beverage_logged", new ShowPageAction(Constants.PAGE_BEVS_LOGGED));
        ACTIONS.put("GET/user_cabinet", new ShowPageAction(Constants.PAGE_CABINET));

        ACTIONS.put("GET/cabinet", new ShowCabinetPageAction());
        ACTIONS.put("GET/products", new ShowProductsPageAction());
        ACTIONS.put("GET/orders", new ShowOrderPageAction());
        ACTIONS.put("GET/order_details", new ShowOrderDetailsPageAction());
        ACTIONS.put("GET/sizes", new ShowSizesPageAction());
        ACTIONS.put("GET/types", new ShowPageAction(Constants.PAGE_ADMIN_TYPES));

        ACTIONS.put("GET/error", new ShowPageAction(Constants.PAGE_ERROR));
        ACTIONS.put("GET/delete_error", new DeleteErrorAction());
        ACTIONS.put("GET/locale", new ChangeLocaleAction());
        ACTIONS.put("GET/logout", new LogoutAction());

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
        ACTIONS.put("POST/create_user", new CreateEntityAdmin(Constants.CREATE_STRING_USER));
        ACTIONS.put("POST/create_product", new CreateEntityAdmin(Constants.CREATE_STRING_PRODUCT));
        ACTIONS.put("POST/create_size", new CreateEntityAdmin(Constants.CREATE_STRING_SIZE));
        ACTIONS.put("POST/create_type", new CreateEntityAdmin(Constants.CREATE_STRING_TYPE));
        ACTIONS.put("POST/create_order_details", new CreateEntityAdmin(Constants.CREATE_STRING_ORDER_DETAILS));
        ACTIONS.put("POST/create_order", new CreateEntityAdmin(Constants.CREATE_STRING_ORDER));
    }

    public static Action getAction(HttpServletRequest request) {
        HttpSession session;
        String req = request.getMethod() + request.getPathInfo();
        Action action = ACTIONS.get(req);
        if (action == null) {
            action = ACTIONS.get("GET/error");
            session = request.getSession();
            session.setAttribute(Constants.ATTRIBUTE_ERROR, Constants.NOT_FOUND_ERROR);
            LOGGER.debug("Unknown request.");
            return action;
        }
        return action;
    }

}
