package kz.javalab.va.servlet;

import kz.javalab.va.action.ActionException;
import kz.javalab.va.dao.DAOException;
import kz.javalab.va.util.Constants;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ErrorHandler extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(ErrorHandler.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            handleError(request, response);
            request.getRequestDispatcher(Constants.PAGE_ERROR).forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            handleError(request, response);
            response.sendRedirect(Constants.PAGE_ERROR);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleError(HttpServletRequest request,
                             HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Throwable exception = (Throwable) request
                .getAttribute(Constants.JAVAX_ERROR);
        LOGGER.debug("Error Handler...");
        if (exception.getClass().equals(ActionException.class)) {
            session.setAttribute(Constants.ATTRIBUTE_ERROR, Constants.BAD_REQUEST_ERROR);
            LOGGER.warn("Bad request.");
        } else if (exception.getClass().equals(DAOException.class)) {
            session.setAttribute(Constants.ATTRIBUTE_ERROR, Constants.ACTION_FAILED_ERROR);
            LOGGER.warn("Database fail.");
        } else {
            session.setAttribute(Constants.ATTRIBUTE_ERROR, Constants.SERVER_ERROR);
            LOGGER.warn("Server error.");
        }
        LOGGER.debug("Details:", exception);
    }
}
