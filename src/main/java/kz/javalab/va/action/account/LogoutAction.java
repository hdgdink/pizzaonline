package kz.javalab.va.action.account;

import kz.javalab.va.action.Action;
import kz.javalab.va.action.ActionException;
import kz.javalab.va.action.ActionResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutAction implements Action {
    private static final String USER = "user";
    private static final String ERROR = "error";
    private static final String ORDER_DETAILS = "order_details";
    private static final String ORDER = "order";
    private static final String QNT = "quantity";
    private static final String SIZE = "size";
    private static final String FINAL_PRICE = "finalPrice";
    private static final String ALL_SIZES_ADMIN = "allSizesAdmin";
    private static final String PIZZA = "pizza";
    private ActionResult result = new ActionResult();

    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {
        HttpSession session = request.getSession();
        session.removeAttribute(USER);
        session.removeAttribute(ERROR);
        session.removeAttribute(ORDER_DETAILS);
        session.removeAttribute(ORDER);
        session.removeAttribute(QNT);
        session.removeAttribute(SIZE);
        session.removeAttribute(FINAL_PRICE);
        session.removeAttribute(ALL_SIZES_ADMIN);
        result.setMethod(ActionResult.METHOD.REDIRECT);
        result.setView(PIZZA);
        return result;
    }

}
