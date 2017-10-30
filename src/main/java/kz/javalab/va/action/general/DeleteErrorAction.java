package kz.javalab.va.action.general;

import kz.javalab.va.action.Action;
import kz.javalab.va.action.ActionException;
import kz.javalab.va.action.ActionResult;
import kz.javalab.va.util.Constants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class DeleteErrorAction implements Action {
    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {
        HttpSession session = request.getSession();
        System.out.println("DELETE IS RUNNING");
        String atrr = (String) session.getAttribute("error");
        System.out.println("ERROR=" + atrr);
        session.removeAttribute("error");

        return new ActionResult(ActionResult.METHOD.REDIRECT, Constants.ACTION_PIZZA);
    }
}
