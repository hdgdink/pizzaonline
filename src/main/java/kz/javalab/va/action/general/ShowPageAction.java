package kz.javalab.va.action.general;

import kz.javalab.va.action.Action;
import kz.javalab.va.action.ActionException;
import kz.javalab.va.action.ActionResult;
import kz.javalab.va.entity.user.User;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ShowPageAction implements Action {
    private ActionResult result;
    User user;


    public ShowPageAction(String view) {
        result = new ActionResult(ActionResult.METHOD.FORWARD, view);
    }


    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {
        return result;
    }
}
