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

    /**
     * Is used to perform required actions and define method and view for
     * <code>Controller</code>. Returns result as <code>ActionResult</code>.
     *
     * @param request  Request to process.
     * @param response Response to send.
     * @return ActionResult where to redirect user
     * @throws ActionException If something fails during method performing.
     */
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
            session.setAttribute("signInError", "account.notFound");
            LOGGER.debug("Account not found.");
            result.setMethod(ActionResult.METHOD.FORWARD);
            result.setView("pizza_unreg");
            return result;
        }
        LOGGER.debug(user.getPassword());
        session.setAttribute("user", user);
        session.removeAttribute("email");
        session.removeAttribute("signInError");
        session.removeAttribute("error");
        session.removeAttribute("link");
        LOGGER.debug("User " + user.getEmail() + " has been registered.");
        result = new ActionResult(ActionResult.METHOD.REDIRECT, "pizza_loged");
        System.out.println(user.getUsername());
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
