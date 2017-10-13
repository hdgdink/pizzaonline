package kz.javalab.va.action.account;

import kz.javalab.va.action.Action;
import kz.javalab.va.action.ActionException;
import kz.javalab.va.action.ActionResult;
import kz.javalab.va.connection.pool.ConnectionPoolException;
import kz.javalab.va.dao.DAOException;
import kz.javalab.va.dao.impl.UserDao;
import kz.javalab.va.entity.user.User;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UserInfoUpdateAction implements Action {
    private static final Logger LOGGER = Logger.getLogger(UserInfoUpdateAction.class);
    private static final String FIRSTNAME = "firstname";
    private static final String LASTNAME = "lastname";
    private static final String EMAIL = "email";
    private static final String USER = "user";
    private static final String REFERER = "referer";
    private UserDao dao;

    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {
        HttpSession session = request.getSession();
        User userBefore = (User) session.getAttribute(USER);
        User userAfter = new User();
        String email = request.getParameter(EMAIL);
        String firstName = request.getParameter(FIRSTNAME);
        String lastName = request.getParameter(LASTNAME);
        userAfter.setId(userBefore.getId());
        userAfter.setEmail(email);
        userAfter.setFirstname(firstName);
        userAfter.setLastname(lastName);
        userAfter.setUsername(userBefore.getUsername());
        userAfter.setBalance(userBefore.getBalance());
        userAfter.setPassword(userBefore.getPassword());
        userAfter.setRole(userBefore.getRole());

        try {
            userDao().update(userAfter);
            LOGGER.debug("User " + userAfter.getUsername() + " has been updated.");
        } catch (DAOException e) {
            e.printStackTrace();
        }
        if (userBefore.getId() == userAfter.getId()) {
            LOGGER.debug("User's own account.");
            session.setAttribute(USER, userAfter);
        }
        LOGGER.debug("Account has been changed.");
        String referer = request.getHeader(REFERER);
        referer = referer.substring(referer.lastIndexOf("/") + 1, referer.length());
        return new ActionResult(ActionResult.METHOD.REDIRECT, referer);
    }

    private UserDao userDao() throws DAOException {
        if (dao == null) {
            try {
                dao = new UserDao();
            } catch (ConnectionPoolException e) {
                LOGGER.error("Error at createUserDao()", e);
                e.printStackTrace();
            }
        }
        return dao;
    }
}
