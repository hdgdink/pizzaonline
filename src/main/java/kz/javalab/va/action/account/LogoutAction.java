package kz.javalab.va.action.account;

import kz.javalab.va.action.Action;
import kz.javalab.va.action.ActionException;
import kz.javalab.va.action.ActionResult;
import kz.javalab.va.util.Constants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutAction implements Action {

    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {
        HttpSession session = request.getSession();
        session.removeAttribute(Constants.ATTRIBUTE_USER);
        session.removeAttribute(Constants.ATTRIBUTE_ERROR);
        session.removeAttribute(Constants.ATTRIBUTE_ORDER_DETAILS);
        session.removeAttribute(Constants.ATTRIBUTE_ORDER);
        session.removeAttribute(Constants.ATTRIBUTE_QNT);
        session.removeAttribute(Constants.ATTRIBUTE_SIZE);
        session.removeAttribute(Constants.ATTRIBUTE_FINALPRICE);
        session.removeAttribute(Constants.ATTRIBUTE_ALL_SIZES_ADMIN);
        return new ActionResult(ActionResult.METHOD.REDIRECT, Constants.ACTION_PIZZA);
    }
}
