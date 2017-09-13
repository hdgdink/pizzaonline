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
    protected void service(HttpServletRequest request, HttpServletResponse resp) throws IOException, ServletException {

        Action action = ActionFactory.getAction(request);
        if (action == null) {
            resp.sendError(404, request.getRequestURI());
            return;
        }
        ActionResult result = null;
        try {
            result = action.execute(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (result != null && result.isRedirection()) {
            resp.sendRedirect(request.getContextPath() + "/do/" + result.getView());
            return;
        }
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/" + result.getView()+ ".jsp");
        requestDispatcher.forward(request, resp);

        //  RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/main_unreg.jsp");
        //requestDispatcher.forward(req, resp);
    }
}
