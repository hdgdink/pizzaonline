package kz.javalab.va.servlet;

import kz.javalab.va.action.Action;
import kz.javalab.va.action.ActionException;
import kz.javalab.va.action.ActionFactory;
import kz.javalab.va.action.ActionResult;
import kz.javalab.va.util.Constants;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Controller extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(Controller.class);


    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Action action;
        ActionResult result;
        ActionResult.METHOD method;
        String view;
        action = ActionFactory.getAction(request);
        try {
            result = action.execute(request, response);
        } catch (ActionException e) {
            throw new ServletException(e);
        }
        method = result.getMethod();
        view = result.getView();
        LOGGER.debug(method + "/" + view);
        switch (method) {
            case FORWARD:
                request.getRequestDispatcher(Constants.WEB_INF_STRING + view + Constants.JSP_STRING)
                        .forward(request, response);
                break;
            case REDIRECT:
                response.sendRedirect(request.getContextPath() + Constants.DO_STRING + result.getView());
                break;
        }

    }
}
