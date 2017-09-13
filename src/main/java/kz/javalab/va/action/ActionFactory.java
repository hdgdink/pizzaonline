package kz.javalab.va.action;


import kz.javalab.va.action.account.RegisterAction;
import kz.javalab.va.action.general.ChangeLocaleAction;
import kz.javalab.va.action.general.ShowPageAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

public class ActionFactory {
    private static final Logger LOGGER = LoggerFactory.getLogger(ActionFactory.class);

    private static final Map<String, Action> ACTIONS = new HashMap<>();
    private static final String PAGE_SUBS = "subs_unreg";
    private static final String PAGE_PIZZA = "pizza_unreg";
    private static final String PAGE_BEVS = "bev_unreg";
    private static final String PAGE_REGISTERED = "main_loged";
    private static final String ERROR = "error";


    static {
        ACTIONS.put("GET/subs", new ShowPageAction(PAGE_SUBS));
        ACTIONS.put("GET/pizza", new ShowPageAction(PAGE_PIZZA));
        ACTIONS.put("GET/beverage", new ShowPageAction(PAGE_BEVS));
        ACTIONS.put("GET/error", new ShowPageAction(ERROR));
        ACTIONS.put("GET/locale", new ChangeLocaleAction());
        ACTIONS.put("GET/registered", new ShowPageAction(PAGE_REGISTERED));

        ACTIONS.put("register", new RegisterAction());
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
