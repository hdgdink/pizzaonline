package kz.javalab.va.action;

import javax.servlet.http.HttpServletRequest;

public class ShowPageAction implements Action {
    private ActionResult result;
    private String page;

    public ShowPageAction(String page) {
        result = new ActionResult(page);
        this.page = page;
    }

    @Override
    public ActionResult execute(HttpServletRequest request) throws ActionException {
        return result;
    }
}
