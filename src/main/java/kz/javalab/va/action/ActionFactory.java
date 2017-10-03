package kz.javalab.va.action;


import kz.javalab.va.action.account.*;
import kz.javalab.va.action.general.ChangeLocaleAction;
import kz.javalab.va.action.general.ShowPageAction;
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
    private static final String PAGE_REGISTERED = "loged";
    private static final String PAGE_CABINET = "cabinet";
    private static final String ERROR = "error";


    static {
        ACTIONS.put("GET/subs", new ShowPageAction(PAGE_SUBS));
        ACTIONS.put("GET/pizza", new ShowPageAction(PAGE_PIZZA));
        ACTIONS.put("GET/beverage", new ShowPageAction(PAGE_BEVS));
        ACTIONS.put("GET/subs_loged", new ShowPageAction(PAGE_SUBS_LOGED));
        ACTIONS.put("GET/pizza_loged", new ShowPageAction(PAGE_PIZZA_LOGED));
        ACTIONS.put("GET/beverage_loged", new ShowPageAction(PAGE_BEVS_LOGED));
        ACTIONS.put("GET/error", new ShowPageAction(ERROR));
        ACTIONS.put("GET/locale", new ChangeLocaleAction());
        ACTIONS.put("GET/registered", new ShowPageAction(PAGE_REGISTERED));
        ACTIONS.put("GET/logout", new LogoutAction());
        ACTIONS.put("GET/cabinet", new ShowPageAction(PAGE_CABINET));

        ACTIONS.put("POST/register", new RegisterAction());
        ACTIONS.put("POST/login", new LoginAction());
        ACTIONS.put("POST/info_update", new UserInfoUpdateAction());
        ACTIONS.put("POST/pass_update", new UserPassUpdateAction());
    }

    public static Action getAction(HttpServletRequest request) {
        HttpSession session;
        String req = request.getMethod() + request.getPathInfo();
        Action action = ACTIONS.get(req);
        if (action == null) {
            action = ACTIONS.get("error");
            session = request.getSession();
            session.setAttribute("error", "error.notFound");
            LOGGER.debug("Unknown request.");
            return action;
        }
        return action;
    }

}
