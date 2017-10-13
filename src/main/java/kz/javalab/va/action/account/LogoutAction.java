package kz.javalab.va.action.account;

import kz.javalab.va.action.Action;
import kz.javalab.va.action.ActionException;
import kz.javalab.va.action.ActionResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutAction implements Action {
    private ActionResult result = new ActionResult();

    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {
        HttpSession session = request.getSession();
        session.removeAttribute("user");
        session.removeAttribute("error");
        session.removeAttribute("order_details");
        session.removeAttribute("order");
        session.removeAttribute("quantity");
        session.removeAttribute("size");
        session.removeAttribute("finalPrice");
        session.removeAttribute("allSizesAdmin");
        result.setMethod(ActionResult.METHOD.REDIRECT);
        result.setView("pizza");
        return result;
    }

}
