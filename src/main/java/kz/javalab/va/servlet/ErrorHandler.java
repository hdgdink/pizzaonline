package kz.javalab.va.servlet;

import kz.javalab.va.action.ActionException;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ErrorHandler extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(ErrorHandler.class);
    private static final String ERROR = "error";
    private static final String JAVAX_ERROR = "javax.servlet.error.exception";
    private static final String BAD_REQUEST = "error.badRequest";
    private static final String ACTION_FAILED = "error.actionFailed ";
    private static final String SERVER_ERROR = "error.serverError";

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        handleError(request, response);
        request.getRequestDispatcher(ERROR).forward(request, response);
    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        handleError(request, response);
        response.sendRedirect(ERROR);
    }

    private void handleError(HttpServletRequest request,
                             HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Throwable exception = (Throwable) request
                .getAttribute(JAVAX_ERROR);
        LOGGER.debug("Error Handler...");
        if (exception.getClass().equals(ActionException.class)) {
            session.setAttribute(ERROR, BAD_REQUEST);
            LOGGER.warn("Bad request.");
        } else if (exception.getClass().equals(ActionException.class)) {
            session.setAttribute(ERROR, ACTION_FAILED);
            LOGGER.warn("Database fail.");
        } else {
            session.setAttribute(ERROR, SERVER_ERROR);
            LOGGER.warn("Server error.");
        }
        LOGGER.debug("Details:", exception);
    }
}
