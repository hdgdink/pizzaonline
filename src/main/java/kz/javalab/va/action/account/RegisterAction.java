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
    private static final ActionResult REG_SUCCESS = new ActionResult(ActionResult.METHOD.REDIRECT, "pizza_loged");
    private static final ActionResult REG_FAILED = new ActionResult(ActionResult.METHOD.REDIRECT, "pizza");
    private ActionResult result;
    private UserDao userDAO = null;
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
            e.printStackTrace();
        }
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String password2 = request.getParameter("re-password");
        User user = null;
        try {
            userNameValid = FieldsValidator.userNameCheck(username);
        } catch (ValidationException e) {
            e.printStackTrace();
        }
        if (!userNameValid) {
            session.setAttribute(ERROR_MESSAGE_ATTRIBUTE, USER_EXIST);
            System.out.println("Username is not valid");
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
                System.out.println(user.toString());
                try {
                    userDAO.create(user);
                } catch (DAOException e) {
                    e.printStackTrace();
                }
                result = REG_SUCCESS;
                session.setAttribute("user", user);
                session.setAttribute("id", user.getId());
                request.setAttribute(SUCCESS_MESSAGE_ATTRIBUTE, REGISTRATION_SUCCESS_ATTRIBUTE);
            } else {
                session.setAttribute(ERROR_MESSAGE_ATTRIBUTE, PASSWORDS_NOT_MATCH);
                result = REG_FAILED;
            }
            return result;
        }
    }
}