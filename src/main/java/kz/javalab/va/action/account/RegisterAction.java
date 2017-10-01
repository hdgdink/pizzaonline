package kz.javalab.va.action.account;

import kz.javalab.va.action.Action;
import kz.javalab.va.action.ActionResult;
import kz.javalab.va.connection.pool.ConnectionPoolException;
import kz.javalab.va.dao.DAOException;
import kz.javalab.va.dao.impl.UserDao;
import kz.javalab.va.entity.user.Role;
import kz.javalab.va.entity.user.User;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class RegisterAction implements Action {
    private static final Logger LOGGER = Logger.getLogger(RegisterAction.class);
    private static final ActionResult REG_SUCCESS = new ActionResult(ActionResult.METHOD.FORWARD, "loged");
    private static final ActionResult REG_FAILED = new ActionResult(ActionResult.METHOD.REDIRECT, "pizza_unreg");
    private ActionResult result;
    private UserDao userDAO = null;
    private static final String ERROR_MESSAGE_ATTRIBUTE = "RegisterErrorMessageKey";
    private static final String SUCCESS_MESSAGE_ATTRIBUTE = "RegisterSuccessMessageKey";
    private static final String REGISTRATION_SUCCESS_ATTRIBUTE = "success_registration";
    private static final String PASSWORDS_NOT_MATCH = "passwords_not_match";
    private static final String USER_EXIST = "user_exist";

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

        User user = null;
        try {
            user = userDAO.getByUsername(email);
        } catch (DAOException e) {
            e.printStackTrace();
        }
        if (user != null) {
            session.setAttribute(ERROR_MESSAGE_ATTRIBUTE, USER_EXIST);
            result = REG_FAILED;
            return result;
        } else {
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
            request.setAttribute(SUCCESS_MESSAGE_ATTRIBUTE, REGISTRATION_SUCCESS_ATTRIBUTE);
            return result;
        }
    }
}