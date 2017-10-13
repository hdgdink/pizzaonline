package kz.javalab.va.servlet;

import kz.javalab.va.action.Action;
import kz.javalab.va.action.ActionException;
import kz.javalab.va.action.ActionFactory;
import kz.javalab.va.action.ActionResult;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Controller extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(Controller.class);
    private static final String JSP = ".jsp";
    private static final String WEB_INF = "/WEB-INF/";
    private static final String DO = "/do/";

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
                request.getRequestDispatcher(WEB_INF + view + JSP)
                        .forward(request, response);
                break;
            case REDIRECT:
                response.sendRedirect(request.getContextPath() + DO + result.getView());
                break;
        }

    }
}
