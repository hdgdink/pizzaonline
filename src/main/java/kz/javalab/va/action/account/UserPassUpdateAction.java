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

public class UserPassUpdateAction implements Action {
    private static final Logger LOGGER = Logger.getLogger(UserPassUpdateAction.class);

    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {
        String referer = request.getHeader(Constants.PAGE_REFERER);
        referer = referer.substring(referer.lastIndexOf("/") + 1, referer.length());
        ActionResult result = new ActionResult(ActionResult.METHOD.REDIRECT, referer);
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(Constants.ATTRIBUTE_USER);
        String oldPassword = request.getParameter(Constants.ATTRIBUTE_OLD_PASSWORD);
        String newPassword1 = request.getParameter(Constants.ATTRIBUTE_NEW_PASSWORD1);
        String newPassword2 = request.getParameter(Constants.ATTRIBUTE_NEW_PASSWORD2);
        Integer id;
        boolean passwordFieldsNull;
        boolean newPasswordEmpty;
        UserDao userDao;
        try {
            userDao = new UserDao();
        } catch (ConnectionPoolException e) {
            LOGGER.error(Constants.USER_DAO_INIT_ERROR, e);
            throw new ActionException(e);
        }
        id = (Integer) session.getAttribute(Constants.ATTRIBUTE_ID);
        session.setAttribute(Constants.ATTRIBUTE_OLD_PASSWORD, oldPassword);
        session.setAttribute(Constants.ATTRIBUTE_NEW_PASSWORD1, newPassword1);
        session.setAttribute(Constants.ATTRIBUTE_NEW_PASSWORD2, newPassword2);
        LOGGER.debug("Old pass: " + oldPassword + ", New Pass1: " + newPassword1 + ", New Pass2" + newPassword2);
        if (!user.getPassword().equals(oldPassword)) {
            session.removeAttribute(Constants.ATTRIBUTE_ERROR);
            session.setAttribute(Constants.ATTRIBUTE_ERROR, Constants.OLD_PASS_WRONG_ERROR);
            LOGGER.debug("Old password value is wrong.");
            return result;
        }
        passwordFieldsNull = FieldsValidator.equalNull(newPassword1, newPassword2);
        if (passwordFieldsNull) {
            LOGGER.warn("Old password field is not valid.");
            return result;
        }
        newPasswordEmpty = FieldsValidator.empty(newPassword1);
        if (newPasswordEmpty) {
            session.setAttribute(Constants.ATTRIBUTE_ERROR, Constants.PASS_EMPTY_ERROR);
            LOGGER.debug("New password value is empty.");
            return result;
        }
        if (!newPassword1.equals(newPassword2)) {
            session.setAttribute(Constants.ATTRIBUTE_ERROR, Constants.PASSWORDS_NOT_MATCH_ERROR);
            LOGGER.debug("Passwords don't match.");
            return result;
        }
        try {
            userDao.resetPassword(newPassword1, id);
            LOGGER.debug("Password has been changed.");
        } catch (DAOException e) {
            LOGGER.warn("Password can't be changed.");
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
        session.removeAttribute(Constants.ATTRIBUTE_ERROR);
        return result;
    }
}