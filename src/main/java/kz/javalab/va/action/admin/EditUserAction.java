package kz.javalab.va.action.admin;

import kz.javalab.va.action.Action;
import kz.javalab.va.action.ActionException;
import kz.javalab.va.action.ActionResult;
import kz.javalab.va.action.account.UserInfoUpdateAction;
import kz.javalab.va.connection.pool.ConnectionPoolException;
import kz.javalab.va.dao.DAOException;
import kz.javalab.va.dao.impl.UserDao;
import kz.javalab.va.entity.user.Role;
import kz.javalab.va.entity.user.User;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by HdgDink} on 11.10.2017.
 */
public class EditUserAction implements Action {
    private static final Logger LOGGER = Logger.getLogger(EditUserAction.class);
    private UserDao dao;
    private User user = null;

    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {
        HttpSession session = request.getSession();

        Integer id = Integer.parseInt(request.getParameter("id"));
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String firstName = request.getParameter("firstname");
        String lastName = request.getParameter("lastname");
        Integer balance = Integer.parseInt(request.getParameter("balance"));
        String password = request.getParameter("password");
        Role role = Role.valueOf(request.getParameter("role"));

        try {
            user = userDao().getById(id);
            System.out.println("User : " + user.toString());
            if ((!user.getUsername().equals(username)) && (userDao().getUsersListByUsername(username) != null)) {
                session.setAttribute("user_change_error", "error.busy-username");
                System.out.println("Error");
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
            }
        } catch (DAOException e) {
            e.printStackTrace();
        }
        String referer = request.getHeader("referer");
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
