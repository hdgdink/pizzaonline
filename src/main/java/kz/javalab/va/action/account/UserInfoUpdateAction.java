package kz.javalab.va.action.account;

import kz.javalab.va.action.Action;
import kz.javalab.va.action.ActionException;
import kz.javalab.va.action.ActionResult;
import kz.javalab.va.connection.pool.ConnectionPoolException;
import kz.javalab.va.dao.DAOException;
import kz.javalab.va.dao.impl.UserDao;
import kz.javalab.va.entity.user.User;
import kz.javalab.va.util.Constants;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Objects;

public class UserInfoUpdateAction implements Action {
    private static final Logger LOGGER = Logger.getLogger(UserInfoUpdateAction.class);

    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {
        HttpSession session = request.getSession();
        User userBefore = (User) session.getAttribute(Constants.ATTRIBUTE_USER);
        User userAfter = new User();
        String email = request.getParameter(Constants.ATTRIBUTE_EMAIL);
        String firstName = request.getParameter(Constants.ATTRIBUTE_FIRSTNAME);
        String lastName = request.getParameter(Constants.ATTRIBUTE_LASTNAME);
        UserDao userDao;
        try {
            userDao = new UserDao();
        } catch (ConnectionPoolException e) {
            LOGGER.error(Constants.USER_DAO_INIT_ERROR, e);
            throw new ActionException(e);
        }
        userAfter.setId(userBefore.getId());
        userAfter.setEmail(email);
        userAfter.setFirstname(firstName);
        userAfter.setLastname(lastName);
        userAfter.setUsername(userBefore.getUsername());
        userAfter.setBalance(userBefore.getBalance());
        userAfter.setPassword(userBefore.getPassword());
        userAfter.setRole(userBefore.getRole());
        try {
            userDao.update(userAfter);
            LOGGER.debug("User " + userAfter.getUsername() + " has been updated.");
        } catch (DAOException e) {
            LOGGER.error("Error in UserDao when updating user", e);
            throw new ActionException(e);
        }
        if (Objects.equals(userBefore.getId(), userAfter.getId())) {
            LOGGER.debug("User's own account.");
            session.setAttribute(Constants.ATTRIBUTE_USER, userAfter);
        }
        LOGGER.debug("Account has been updated.");
        String referer = request.getHeader(Constants.PAGE_REFERER);
        referer = referer.substring(referer.lastIndexOf("/") + 1, referer.length());
        return new ActionResult(ActionResult.METHOD.REDIRECT, referer);
    }
}
