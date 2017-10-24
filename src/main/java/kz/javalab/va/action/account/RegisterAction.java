package kz.javalab.va.action.account;

import kz.javalab.va.action.Action;
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
    private boolean userNameValid = false;
    private ActionResult result;
    private UserDao userDAO = null;

    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        try {
            userDAO = new UserDao();
        } catch (ConnectionPoolException e) {
            LOGGER.error("Error at createUserDao()", e);
            e.printStackTrace();
        }
        String firstname = request.getParameter(Constants.ATTRIBUTE_FIRSTNAME);
        String lastname = request.getParameter(Constants.ATTRIBUTE_LASTNAME);
        String username = request.getParameter(Constants.ATTRIBUTE_USERNAME);
        String email = request.getParameter(Constants.ATTRIBUTE_EMAIL);
        String password = request.getParameter(Constants.ATTRIBUTE_PASSWORD);
        String password2 = request.getParameter(Constants.ATTRIBUTE_RE_PASSWORD);
        User user = null;
        try {
            userNameValid = FieldsValidator.userNameCheck(username);
        } catch (ValidationException e) {
            LOGGER.error("Validation is not succes", e);
            e.printStackTrace();
        }
        if (!userNameValid) {
            session.setAttribute(Constants.ATTRIBUTE_REGISTER_ERROR, Constants.USER_EXIST_ERROR);
            LOGGER.error("Username is not valid");
            result = REG_FAILED;
            return result;
        } else {
            if (password.equals(password2)) {
                user = new User();
                user.setFirstname(firstname);
                user.setLastname(lastname);
                user.setUsername(username);
                user.setEmail(email);
                user.setPassword(password);
                user.setRole(Role.CLIENT);
                user.setBalance(0);
                try {
                    userDAO.create(user);
                    user = userDAO.getByUsername(user.getUsername());
                } catch (DAOException e) {
                    LOGGER.error("Error while create User", e);
                    e.printStackTrace();
                }
                result = REG_SUCCESS;
                LOGGER.info("User with username: " + user.getUsername() + " registered");
                session.setAttribute(Constants.ATTRIBUTE_USER, user);
                session.setAttribute(Constants.ATTRIBUTE_ID, user.getId());
                request.setAttribute(Constants.ATTRIBUTE_REGISTER_SUCCESS_KEY, Constants.ATTRIBUTE_REGISTRATION_SUCCESS_MESSAGE);
            } else {
                LOGGER.error("Re-password is not valid");
                session.setAttribute(Constants.ATTRIBUTE_REGISTER_ERROR, Constants.PASSWORDS_NOT_MATCH_ERROR);
                result = REG_FAILED;
            }
            return result;
        }
    }
}