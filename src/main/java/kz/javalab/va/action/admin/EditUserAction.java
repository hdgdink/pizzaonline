package kz.javalab.va.action.admin;

import kz.javalab.va.action.Action;
import kz.javalab.va.action.ActionException;
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


public class EditUserAction implements Action {
    private static final Logger LOGGER = Logger.getLogger(EditUserAction.class);
    private static final String ID = "id";
    private static final String USERNAME = "username";
    private static final String EMAIL = "email";
    private static final String FIRSTNAME = "firstname";
    private static final String LASTNAME = "lastname";
    private static final String BALANCE = "balance";
    private static final String PASSWORD = "password";
    private static final String ROLE = "role";
    private static final String USER_CHANGE_ERROR = "user_change_error";
    private static final Object BUSY_USERNAME = "error.busy-username";
    private static final String REFERER = "referer";
    private UserDao dao;
    private User user = null;

    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {
        HttpSession session = request.getSession();
        Integer id = Integer.parseInt(request.getParameter(ID));
        String username = request.getParameter(USERNAME);
        String email = request.getParameter(EMAIL);
        String firstName = request.getParameter(FIRSTNAME);
        String lastName = request.getParameter(LASTNAME);
        Integer balance = Integer.parseInt(request.getParameter(BALANCE));
        String password = request.getParameter(PASSWORD);
        Role role = Role.valueOf(request.getParameter(ROLE));

        try {
            user = userDao().getById(id);
            if ((!user.getUsername().equals(username)) && (userDao().getUsersListByUsername(username) != null)) {
                LOGGER.info("Username is busy, select another");
                session.setAttribute(USER_CHANGE_ERROR, BUSY_USERNAME);
            } else {
                user.setUsername(username);
                user.setId(id);
                user.setEmail(email);
                user.setFirstname(firstName);
                user.setLastname(lastName);
                user.setBalance(balance);
                user.setPassword(password);
                user.setRole(role);
                userDao().update(user);
                LOGGER.info("User updated");
            }
        } catch (DAOException e) {
            LOGGER.error("Error of SizeDao", e);
            e.printStackTrace();
        }
        String referer = request.getHeader(REFERER);
        referer = referer.substring(referer.lastIndexOf("/") + 1, referer.length());
        return new ActionResult(ActionResult.METHOD.REDIRECT, referer);
    }

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
