package kz.javalab.va.action.account;

import kz.javalab.va.action.Action;
import kz.javalab.va.action.ActionException;
import kz.javalab.va.action.ActionResult;
import kz.javalab.va.connection.pool.ConnectionPoolException;
import kz.javalab.va.dao.DAOException;
import kz.javalab.va.dao.impl.UserDao;
import kz.javalab.va.entity.user.User;
import kz.javalab.va.util.Constants;
import kz.javalab.va.util.validator.FieldsValidator;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by HdgDink} on 02.10.2017.
 */
public class UserPassUpdateAction implements Action {
    private static final Logger LOGGER = Logger.getLogger(UserPassUpdateAction.class);
    private UserDao dao;
    private String view;
    private ActionResult result = new ActionResult(ActionResult.METHOD.REDIRECT, view);


    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(Constants.ATTRIBUTE_USER);
        String oldPassword = request.getParameter(Constants.ATTRIBUTE_OLD_PASSWORD);
        String newPassword1 = request.getParameter(Constants.ATTRIBUTE_NEW_PASSWORD1);
        String newPassword2 = request.getParameter(Constants.ATTRIBUTE_NEW_PASSWORD2);
        int id = 0;
        boolean passwordFieldsNull = false;
        boolean newPasswordEmpty = false;
        view = request.getHeader(Constants.PAGE_REFERER);
        view = view.substring(view.lastIndexOf("/") + 1, view.length());
        try {
            id = (int) session.getAttribute(Constants.ATTRIBUTE_ID);
        } catch (IllegalArgumentException e) {
            LOGGER.warn("Id field is not valid.", e);
            throw new ActionException();
        }
        session.setAttribute(Constants.ATTRIBUTE_OLD_PASSWORD, oldPassword);
        session.setAttribute(Constants.ATTRIBUTE_NEW_PASSWORD1, newPassword1);
        session.setAttribute(Constants.ATTRIBUTE_NEW_PASSWORD2, newPassword2);
        LOGGER.debug(oldPassword);
        LOGGER.debug(newPassword1);
        LOGGER.debug(newPassword2);
        LOGGER.debug(user.getPassword());
        if (!user.getPassword().equals(oldPassword)) {
            session.removeAttribute(Constants.ATTRIBUTE_PASSWORD_ERROR);
            session.setAttribute(Constants.ATTRIBUTE_OLD_PASSWORD_ERROR, Constants.OLD_PASS_WRONG_ERROR);
            LOGGER.debug("Old password value is wrong.");
            return new ActionResult(ActionResult.METHOD.REDIRECT, request.getHeader(Constants.PAGE_REFERER));
        }
        session.removeAttribute(Constants.ATTRIBUTE_OLD_PASSWORD_ERROR);
        passwordFieldsNull = FieldsValidator.equalNull(newPassword1, newPassword2);
        if (passwordFieldsNull) {
            LOGGER.warn("Old password field is not valid.");
            return result;
        }
        newPasswordEmpty = FieldsValidator.empty(newPassword1);
        if (newPasswordEmpty) {
            session.setAttribute(Constants.ATTRIBUTE_PASSWORD_ERROR, Constants.PASS_EMPTY_ERROR);
            LOGGER.debug("New password value is empty.");
            return result;
        }
        if (!newPassword1.equals(newPassword2)) {
            session.setAttribute(Constants.ATTRIBUTE_PASSWORD_ERROR, Constants.PASSWORDS_NOT_MATCH_ERROR);
            LOGGER.debug("Passwords don't match.");
            return result;
        }
        try {
            userDao().resetPassword(newPassword1, id);
            LOGGER.debug("Password has been changed.");
        } catch (DAOException e) {
            LOGGER.warn("Password cannot be changed.");
            throw new ActionException(e);
        }
        if (user.getId() == id) {
            user.setPassword(newPassword1);
            session.setAttribute(Constants.ATTRIBUTE_USER, user);
        }
        session.setAttribute(Constants.ATTRIBUTE_CHANGE_PASS_SUCCESS, Constants.ATTRIBUTE_CHANGE_SUCCESS_MESSAGE);
        session.removeAttribute(Constants.ATTRIBUTE_OLD_PASSWORD);
        session.removeAttribute(Constants.ATTRIBUTE_NEW_PASSWORD1);
        session.removeAttribute(Constants.ATTRIBUTE_NEW_PASSWORD2);
        session.removeAttribute(Constants.ATTRIBUTE_OLD_PASSWORD_ERROR);
        session.removeAttribute(Constants.ATTRIBUTE_PASSWORD_ERROR);
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