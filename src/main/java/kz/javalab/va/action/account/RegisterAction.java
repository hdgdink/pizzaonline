package kz.javalab.va.action.account;

import kz.javalab.va.action.Action;
import kz.javalab.va.action.ActionException;
import kz.javalab.va.action.ActionResult;
import kz.javalab.va.connection.pool.ConnectionPoolException;
import kz.javalab.va.dao.DAOException;
import kz.javalab.va.dao.impl.UserDao;
import kz.javalab.va.entity.user.Role;
import kz.javalab.va.entity.user.User;
import kz.javalab.va.util.Constants;
import kz.javalab.va.util.validator.FieldsValidator;
import kz.javalab.va.util.validator.ValidationException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class RegisterAction implements Action {
    private static final Logger LOGGER = Logger.getLogger(RegisterAction.class);
    private static final ActionResult REG_SUCCESS = new ActionResult(ActionResult.METHOD.REDIRECT, Constants.PAGE_PIZZA_LOGGED);
    private static final ActionResult REG_FAILED = new ActionResult(ActionResult.METHOD.REDIRECT, Constants.ACTION_PIZZA);
    private User user;

    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {
        HttpSession session = request.getSession();
        ActionResult result;
        boolean userNameValid;
        boolean emailValid;
        boolean passValid;
        UserDao userDao;
        try {
            userDao = new UserDao();
        } catch (ConnectionPoolException e) {
            LOGGER.error(Constants.USER_DAO_INIT_ERROR, e);
            throw new ActionException(e);
        }
        String username = request.getParameter(Constants.ATTRIBUTE_USERNAME);
        String email = request.getParameter(Constants.ATTRIBUTE_EMAIL);
        String password = request.getParameter(Constants.ATTRIBUTE_PASSWORD);
        String password2 = request.getParameter(Constants.ATTRIBUTE_RE_PASSWORD);
        try {
            userNameValid = FieldsValidator.userNameCheck(username);
            emailValid = FieldsValidator.emailValid(email);
            passValid = FieldsValidator.passwordValid(password);
        } catch (ValidationException e) {
            LOGGER.error("Validation error", e);
            throw new ActionException(e);
        }
        if (!userNameValid) {
            session.setAttribute(Constants.ATTRIBUTE_ERROR, Constants.USER_EXIST_ERROR);
            LOGGER.error("Username is not valid");
            result = REG_FAILED;
            return result;
        }
        if (!emailValid) {
            session.setAttribute(Constants.ATTRIBUTE_ERROR, Constants.EMAIL_VALID_ERROR);
            LOGGER.error("Email is not valid");
            result = REG_FAILED;
            return result;
        }
        if (!passValid) {
            session.setAttribute(Constants.ATTRIBUTE_ERROR, Constants.PASS_VALID_ERROR);
            LOGGER.error("Password is not valid");
            result = REG_FAILED;
            return result;
        }
        if (password.equals(password2)) {
            createUser(request, username, password, email);
            try {
                userDao.create(user);
                user = userDao.getByUsername(user.getUsername());
            } catch (DAOException e) {
                LOGGER.error("Error in UserDAO while create User.", e);
                throw new ActionException(e);
            }
            result = REG_SUCCESS;
            LOGGER.info("User with username: " + user.getUsername() + " registered");
            session.setAttribute(Constants.ATTRIBUTE_USER, user);
            session.setAttribute(Constants.ATTRIBUTE_ID, user.getId());
        } else {
            LOGGER.error("Re-password is not valid");
            session.setAttribute(Constants.ATTRIBUTE_ERROR, Constants.PASSWORDS_NOT_MATCH_ERROR);
            result = REG_FAILED;
        }
        return result;
    }

    private User createUser(HttpServletRequest request, String username, String email, String password) {
        String firstname = request.getParameter(Constants.ATTRIBUTE_FIRSTNAME);
        String lastname = request.getParameter(Constants.ATTRIBUTE_LASTNAME);
        user = new User();
        user.setFirstname(firstname);
        user.setLastname(lastname);
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);
        user.setRole(Role.CLIENT);
        user.setBalance(0);
        return user;
    }
}