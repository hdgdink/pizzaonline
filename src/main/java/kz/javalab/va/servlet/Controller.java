package kz.javalab.va.servlet;

import kz.javalab.va.action.Action;
import kz.javalab.va.action.ActionException;
import kz.javalab.va.action.ActionFactory;
import kz.javalab.va.action.ActionResult;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Controller extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String actionName = req.getMethod() + req.getPathInfo();
        String query = actionName + "/" + req.getQueryString();
        Action action = ActionFactory.getAction(actionName);
        if (action == null) {
            resp.sendError(404, req.getRequestURI());
            return;
        }
        ActionResult result = null;
        try {
            result = action.execute(req);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (result != null && result.isRedirection()) {
            resp.sendRedirect(req.getContextPath() + "/do/" + result.getView());
            return;
        }
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/" + (result != null ? result.getView() : null) + ".jsp");
        requestDispatcher.forward(req, resp);

        //  RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/main_unreg.jsp");
        //requestDispatcher.forward(req, resp);
    }
}
