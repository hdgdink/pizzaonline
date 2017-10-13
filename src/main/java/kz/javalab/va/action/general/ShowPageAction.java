package kz.javalab.va.action.general;

import kz.javalab.va.action.Action;
import kz.javalab.va.action.ActionException;
import kz.javalab.va.action.ActionResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ShowPageAction implements Action {
    private ActionResult result;

    public ShowPageAction(String view) {
        result = new ActionResult(ActionResult.METHOD.FORWARD, view);
    }

    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {
        return result;
    }
}