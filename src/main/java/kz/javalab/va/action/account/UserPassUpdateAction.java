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
    private static final String REFERER = "referer";


    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        String oldPassword = request.getParameter("old-password");
        String newPassword1 = request.getParameter("new-password1");
        String newPassword2 = request.getParameter("new-password2");
        int id = 0;
        boolean passwordFieldsNull = false;
        boolean newPasswordEmpty = false;
        boolean newPasswordValid = false;
        try {
            id = (int) session.getAttribute("id");
            System.out.println(id);
        } catch (IllegalArgumentException e) {
            LOGGER.warn("Id field is not valid.", e);
            throw new ActionException();
        }
        session.setAttribute("oldPassword", oldPassword);
        session.setAttribute("newPassword1", newPassword1);
        session.setAttribute("newPassword2", newPassword2);
        LOGGER.debug(oldPassword);
        LOGGER.debug(newPassword1);
        LOGGER.debug(newPassword2);
        LOGGER.debug(user.getPassword());
        if (!user.getPassword().equals(oldPassword)) {
            session.removeAttribute("passwordError");
            session.setAttribute("oldPasswordError", "account.oldPasswordWrong");
            System.out.println("Old password value is wrong. ");
            LOGGER.debug("Old password value is wrong.");
            return new ActionResult(ActionResult.METHOD.REDIRECT, request.getHeader(REFERER));
        }
        session.removeAttribute("oldPasswordError");
        passwordFieldsNull = FieldsValidator.equalNull(newPassword1, newPassword2);
        if (passwordFieldsNull) {
            System.out.println("Old password field is not valid.");
            LOGGER.warn("Old password field is not valid.");
            throw new ActionException();
        }
        newPasswordEmpty = FieldsValidator.empty(newPassword1);
        if (newPasswordEmpty) {
            session.setAttribute("passwordError", "account.passwordEmpty");
            LOGGER.debug("New password value is empty.");
            System.out.println("New password value is empty.");
            return new ActionResult(ActionResult.METHOD.REDIRECT, request.getHeader(REFERER));
        }
       /* try {
            newPasswordValid = FieldsValidator.passwordValid(newPassword1);
        } catch (ValidationException e) {
            System.out.println("Password cannot be validated.");
            LOGGER.debug("Password cannot be validated.");
            throw new ActionException();
        }
        if (!newPasswordValid) {
            session.setAttribute("passwordError", "account.passwordNotValid");
            LOGGER.debug("New password value is not valid.");
            System.out.println( "New password value is not valid.");
            return new ActionResult(ActionResult.METHOD.REDIRECT, request.getHeader(REFERER));
        }*/
        if (!newPassword1.equals(newPassword2)) {
            session.setAttribute("passwordError", "account.passwordsDontMatch");
            LOGGER.debug("Passwords don't match.");
            System.out.println("Passwords don't match.");
            return new ActionResult(ActionResult.METHOD.REDIRECT, request.getHeader(REFERER));
        }
        try {
            userDao().resetPassword(newPassword1,id);
            LOGGER.debug("Password has been changed.");
            System.out.println("Password has been changed.");
        } catch (DAOException e) {
            LOGGER.warn("Password cannot be changed.");
            System.out.println("Password cannot be changed.");
            throw new ActionException(e);
        }
        if (user.getId() == id) {
            user.setPassword(newPassword1);
            session.setAttribute("user", user);
        }
        session.setAttribute("success", "account.changePasswordSuccess");
        session.removeAttribute("oldPassword");
        session.removeAttribute("newPassword1");
        session.removeAttribute("newPassword2");
        session.removeAttribute("oldPasswordError");
        session.removeAttribute("emailError");
        session.removeAttribute("passwordError");
        session.removeAttribute("error");
        String referer = request.getHeader("referer");
        referer = referer.substring(referer.lastIndexOf("/") + 1, referer.length());
        return new ActionResult(ActionResult.METHOD.REDIRECT, referer);
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