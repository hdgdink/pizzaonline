package kz.javalab.va.servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Controller extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/test1.jsp");
        requestDispatcher.forward(req, resp);
    }
}
