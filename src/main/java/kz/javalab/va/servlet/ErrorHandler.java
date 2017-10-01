package kz.javalab.va.servlet;

import kz.javalab.va.action.ActionException;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by HdgDink} on 01.10.2017.
 */
public class ErrorHandler extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(ErrorHandler.class);

        protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        handleError(request, response);
        request.getRequestDispatcher("error").forward(request, response);
    }


    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        handleError(request, response);
        response.sendRedirect("error");
    }


    private void handleError(HttpServletRequest request,
                             HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Throwable exception = (Throwable) request
                .getAttribute("javax.servlet.error.exception");
        LOGGER.debug("Error Handler...");
        if (exception.getClass().equals(ActionException.class)) {
            session.setAttribute("error", "error.badRequest");
            LOGGER.warn("Bad request.");
        } else if (exception.getClass().equals(ActionException.class)) {
            session.setAttribute("error", "error.actionFailed ");
            LOGGER.warn("Database fail.");
        } else {
            session.setAttribute("error", "error.serverError");
            LOGGER.warn("Server error.");
        }
        LOGGER.debug("Details:", exception);
    }
}
