package kz.javalab.va.action.account;

import kz.javalab.va.action.Action;
import kz.javalab.va.action.ActionException;
import kz.javalab.va.action.ActionResult;
import kz.javalab.va.connection.pool.ConnectionPoolException;
import kz.javalab.va.dao.DAOException;
import kz.javalab.va.dao.impl.UserDao;
import kz.javalab.va.entity.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginAction implements Action {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginAction.class);
    private ActionResult result;
    private UserDao dao;


    @Override
    public ActionResult execute(HttpServletRequest request,
                                HttpServletResponse response) throws ActionException {
        HttpSession session = request.getSession();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user;

        session.setAttribute("username", username);
        try {
            user = userDao().getByUsername(username);
        } catch (DAOException e) {
            LOGGER.warn("Request cannot be executed.");
            throw new ActionException(e);
        }
        if (user == null) {
            session.setAttribute("logInError", "account.notFound");
            LOGGER.debug("Wrong username.");
            result.setMethod(ActionResult.METHOD.FORWARD);
            result.setView("pizza_unreg");
            return result;
        }
        if (!password.equals(user.getPassword())) {
            session.setAttribute("logInError", "password.isBad");
            LOGGER.debug("Wrong password. " + password);
            result.setMethod(ActionResult.METHOD.FORWARD);
            result.setView("pizza_unreg");
            return result;
        }

        session.setAttribute("user", user);
        session.removeAttribute("email");
        session.removeAttribute("signInError");
        session.removeAttribute("error");
        session.removeAttribute("link");
        LOGGER.debug("User " + user.getEmail() + " has been logged.");
        result = new ActionResult(ActionResult.METHOD.FORWARD, "pizza_loged");
        System.out.println(session.getAttribute("username"));
        return result;
    }

    /**
     * Is used to get user dao. It initializes dao during the first use.
     *
     * @return The user.
     * @throws DAOException If something fails.
     */
    private UserDao userDao() throws DAOException {
        if (dao == null) {
            try {
                dao = new UserDao();
            } catch (ConnectionPoolException e) {
                e.printStackTrace();
            }
        }
        return dao;
    }
}
