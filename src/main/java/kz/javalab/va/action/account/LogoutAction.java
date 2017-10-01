package kz.javalab.va.action.account;

import kz.javalab.va.action.Action;
import kz.javalab.va.action.ActionException;
import kz.javalab.va.action.ActionResult;
import kz.javalab.va.entity.user.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by HdgDink} on 01.10.2017.
 */
public class LogoutAction implements Action {
    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        session.removeAttribute("user");
        session.removeAttribute("error");
        session.removeAttribute("loged");
        return new ActionResult(ActionResult.METHOD.FORWARD, "pizza_unreg");
    }

}
