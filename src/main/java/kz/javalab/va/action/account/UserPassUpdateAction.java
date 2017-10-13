package kz.javalab.va.action.account;

import kz.javalab.va.action.Action;
import kz.javalab.va.action.ActionException;
import kz.javalab.va.action.ActionResult;
import kz.javalab.va.connection.pool.ConnectionPoolException;
import kz.javalab.va.dao.DAOException;
import kz.javalab.va.dao.impl.UserDao;
import kz.javalab.va.entity.user.User;
import kz.javalab.va.util.validator.FieldsValidator;
import kz.javalab.va.util.validator.ValidationException;
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
    private static final String REFERER = "referer";
    private static final String USER = "user";
    private static final String OLD_PASSWORD = "oldPassword";
    private static final String NEW_PASSWORD1 = "newPassword1";
    private static final String NEW_PASSWORD2 = "newPassword2";
    private static final String ID = "id";
    private static final String PASSWORD_ERROR = "passwordError";
    private static final String OLD_PASSWORD_ERROR = "oldPasswordError";
    private static final String ACOUNT = "account";
    private static final String ERROR_OLD_PASS_WRONG = ".oldPasswordWrong";
    private static final String ERROR_PASS_EMPTY = ".passwordEmpty";
    private static final String ERROR_PASS_NOT_MATCH = ".passwordsDontMatch";
    private static final String CHANGE_SUCCESS = ".changePasswordSuccess";
    private static final String SUCCESS = "success";

    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(USER);
        String oldPassword = request.getParameter(OLD_PASSWORD);
        String newPassword1 = request.getParameter(NEW_PASSWORD1);
        String newPassword2 = request.getParameter(NEW_PASSWORD2);
        int id = 0;
        boolean passwordFieldsNull = false;
        boolean newPasswordEmpty = false;
        view = request.getHeader(REFERER);
        view = view.substring(view.lastIndexOf("/") + 1, view.length());

        try {
            id = (int) session.getAttribute(ID);
        } catch (IllegalArgumentException e) {
            LOGGER.warn("Id field is not valid.", e);
            throw new ActionException();
        }
        session.setAttribute(OLD_PASSWORD, oldPassword);
        session.setAttribute(NEW_PASSWORD1, newPassword1);
        session.setAttribute(NEW_PASSWORD2, newPassword2);
        LOGGER.debug(oldPassword);
        LOGGER.debug(newPassword1);
        LOGGER.debug(newPassword2);
        LOGGER.debug(user.getPassword());
        if (!user.getPassword().equals(oldPassword)) {
            session.removeAttribute(PASSWORD_ERROR);
            session.setAttribute(OLD_PASSWORD_ERROR, ACOUNT + ERROR_OLD_PASS_WRONG);
            LOGGER.debug("Old password value is wrong.");
            return new ActionResult(ActionResult.METHOD.REDIRECT, request.getHeader(REFERER));
        }
        session.removeAttribute(OLD_PASSWORD_ERROR);
        passwordFieldsNull = FieldsValidator.equalNull(newPassword1, newPassword2);
        if (passwordFieldsNull) {
            LOGGER.warn("Old password field is not valid.");
            return result;
        }
        newPasswordEmpty = FieldsValidator.empty(newPassword1);
        if (newPasswordEmpty) {
            session.setAttribute(PASSWORD_ERROR, ACOUNT + ERROR_PASS_EMPTY);
            LOGGER.debug("New password value is empty.");
            return result;
        }
        if (!newPassword1.equals(newPassword2)) {
            session.setAttribute(PASSWORD_ERROR, ACOUNT + ERROR_PASS_NOT_MATCH);
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
            session.setAttribute(USER, user);
        }
        session.setAttribute(SUCCESS, ACOUNT + CHANGE_SUCCESS);
        session.removeAttribute(OLD_PASSWORD);
        session.removeAttribute(NEW_PASSWORD1);
        session.removeAttribute(NEW_PASSWORD2);
        session.removeAttribute(OLD_PASSWORD_ERROR);
        session.removeAttribute(PASSWORD_ERROR);
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