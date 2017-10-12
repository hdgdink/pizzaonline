package kz.javalab.va.action.account;

import kz.javalab.va.action.Action;
import kz.javalab.va.action.ActionException;
import kz.javalab.va.action.ActionResult;
import kz.javalab.va.connection.pool.ConnectionPoolException;
import kz.javalab.va.dao.DAOException;
import kz.javalab.va.dao.impl.UserDao;
import kz.javalab.va.entity.user.Role;
import kz.javalab.va.entity.user.User;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginAction implements Action {
    private static final Logger LOGGER = Logger.getLogger(LoginAction.class);
    private ActionResult result;
    private UserDao dao;

    @Override
    public ActionResult execute(HttpServletRequest request,
                                HttpServletResponse response) throws ActionException {
        HttpSession session = request.getSession();
        Integer finalPrice=0;
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user;
        try {
            user = userDao().getByUsername(username);
        } catch (DAOException e) {
            LOGGER.warn("Request cannot be executed.");
            throw new ActionException(e);
        }
        if (user == null) {
            session.setAttribute("logInError", "account.notFound");
            LOGGER.debug("Wrong username.");
            result = new ActionResult(ActionResult.METHOD.FORWARD, "pizza_unreg");
            return result;
        }
        if (!password.equals(user.getPassword())) {
            session.setAttribute("logInError", "account.isBad");
            LOGGER.debug("Wrong password. " + password);
            result = new ActionResult(ActionResult.METHOD.FORWARD, "pizza_unreg");
            return result;
        }
        if (user.getRole().equals(Role.ADMIN))
            result = new ActionResult(ActionResult.METHOD.REDIRECT, "cabinet");
        else result = new ActionResult(ActionResult.METHOD.REDIRECT, "pizza_loged");
        session.setAttribute("user", user);
        session.setAttribute("id", user.getId());
        session.setAttribute("finalPrice",finalPrice);
        System.out.println(session.getAttribute("id"));
        session.removeAttribute("email");
        session.removeAttribute("signInError");
        session.removeAttribute("error");
        session.removeAttribute("link");
        LOGGER.debug("User " + user.getEmail() + " has been logged.");
                System.out.println("LoginAction:" + session.getAttribute("user"));
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
