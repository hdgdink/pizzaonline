package kz.javalab.va.action.account;

import kz.javalab.va.action.Action;
import kz.javalab.va.action.ActionResult;
import kz.javalab.va.connection.pool.ConnectionPoolException;
import kz.javalab.va.dao.DAOException;
import kz.javalab.va.dao.impl.UserDao;
import kz.javalab.va.entity.user.Role;
import kz.javalab.va.entity.user.User;
import kz.javalab.va.util.validator.FieldsValidator;
import kz.javalab.va.util.validator.ValidationException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class RegisterAction implements Action {
    private static final Logger LOGGER = Logger.getLogger(RegisterAction.class);
    private ActionResult result;
    private UserDao userDAO = null;
    private static final String FIRSTNAME = "firstname";
    private static final String LASTNAME = "lastname";
    private static final String USERNAME = "username";
    private static final String EMAIL = "email";
    private static final String PASSWORD = "password";
    private static final String RE_PASSWORD = "re-password";
    private static final String USER = "user";
    private static final String ID = "id";
    private static final ActionResult REG_SUCCESS = new ActionResult(ActionResult.METHOD.REDIRECT, "pizza_loged");
    private static final ActionResult REG_FAILED = new ActionResult(ActionResult.METHOD.REDIRECT, "pizza");
    private static final String ERROR_MESSAGE_ATTRIBUTE = "RegisterErrorMessageKey";
    private static final String SUCCESS_MESSAGE_ATTRIBUTE = "RegisterSuccessMessageKey";
    private static final String REGISTRATION_SUCCESS_ATTRIBUTE = "success_registration";
    private static final String PASSWORDS_NOT_MATCH = "passwords_not_match";
    private static final String USER_EXIST = "user_exist";
    private boolean userNameValid = false;

    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        try {
            userDAO = new UserDao();
        } catch (ConnectionPoolException e) {
            LOGGER.error("Error at createUserDao()", e);
            e.printStackTrace();
        }
        String firstname = request.getParameter(FIRSTNAME);
        String lastname = request.getParameter(LASTNAME);
        String username = request.getParameter(USERNAME);
        String email = request.getParameter(EMAIL);
        String password = request.getParameter(PASSWORD);
        String password2 = request.getParameter(RE_PASSWORD);
        User user = null;
        try {
            userNameValid = FieldsValidator.userNameCheck(username);
        } catch (ValidationException e) {
            LOGGER.error("Validation is not succes", e);
            e.printStackTrace();
        }
        if (!userNameValid) {
            session.setAttribute(ERROR_MESSAGE_ATTRIBUTE, USER_EXIST);
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
                session.setAttribute(USER, user);
                session.setAttribute(ID, user.getId());
                request.setAttribute(SUCCESS_MESSAGE_ATTRIBUTE, REGISTRATION_SUCCESS_ATTRIBUTE);
            } else {
                LOGGER.error("Re-password is not valid");
                session.setAttribute(ERROR_MESSAGE_ATTRIBUTE, PASSWORDS_NOT_MATCH);
                result = REG_FAILED;
            }
            return result;
        }
    }
}