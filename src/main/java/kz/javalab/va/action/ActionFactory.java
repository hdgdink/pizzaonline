package kz.javalab.va.action;


import java.util.HashMap;
import java.util.Map;

public class ActionFactory {
    private static final Map<String, Action> ACTIONS = new HashMap<>();
    private static final String PAGE_MAIN = "main_unreg";
    private static final String PAGE_SUBS = "subs_unreg";
    private static final String PAGE_PIZZA = "main_unreg";
    private static final String PAGE_BEVS = "bev_unreg";

    static {
        ACTIONS.put("GET/main", new ShowPageAction(PAGE_MAIN));
        ACTIONS.put("GET/locale", new ChangeLocaleAction());
        ACTIONS.put("GET/subs", new ShowPageAction(PAGE_SUBS));
        ACTIONS.put("GET/pizza", new ShowPageAction(PAGE_PIZZA));
        ACTIONS.put("GET/beverage", new ShowPageAction(PAGE_BEVS));

    }

    public static Action getAction(String actionName) {
        return ACTIONS.get(actionName);
    }

}
