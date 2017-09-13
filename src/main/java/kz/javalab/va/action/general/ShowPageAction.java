package kz.javalab.va.action.general;

import kz.javalab.va.action.Action;
import kz.javalab.va.action.ActionException;
import kz.javalab.va.action.ActionResult;

import javax.servlet.http.HttpServletRequest;

public class ShowPageAction implements Action {
    private ActionResult result;


    public ShowPageAction(String page) {
        result = new ActionResult(page);
    }

    @Override
    public ActionResult execute(HttpServletRequest request) throws ActionException {
        return result;
    }
}
