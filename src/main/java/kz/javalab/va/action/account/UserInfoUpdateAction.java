package kz.javalab.va.action.account;

import kz.javalab.va.action.Action;
import kz.javalab.va.action.ActionException;
import kz.javalab.va.action.ActionResult;
import kz.javalab.va.connection.pool.ConnectionPoolException;
import kz.javalab.va.dao.DAOException;
import kz.javalab.va.dao.impl.UserDao;
import kz.javalab.va.entity.user.User;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * Created by HdgDink} on 02.10.2017.
 */
public class UserInfoUpdateAction implements Action {
    private static final Logger LOGGER = Logger.getLogger(UserInfoUpdateAction.class);
    private UserDao dao;

    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {
        HttpSession session = request.getSession();
        User userBefore = (User) session.getAttribute("user");
        User userAfter = new User();
        String email = request.getParameter("email");
        String firstName = request.getParameter("firstname");
        String lastName = request.getParameter("lastname");
        userAfter.setId(userBefore.getId());
        userAfter.setEmail(email);
        userAfter.setFirstname(firstName);
        userAfter.setLastname(lastName);
        userAfter.setUsername(userBefore.getUsername());
        userAfter.setBalance(userBefore.getBalance());
        userAfter.setPassword(userBefore.getPassword());
        userAfter.setRole(userBefore.getRole());
        LOGGER.debug("Account has been changed.");
        try {
            userDao().update(userAfter);
            System.out.println("infoupdateAction userafter: "+ userAfter.getId() + userAfter);
            LOGGER.debug("User " + userAfter.getUsername() + " has been updated.");
        } catch (DAOException e) {
            e.printStackTrace();
        }
        if (userBefore.getId() == userAfter.getId()) {
            LOGGER.debug("User's own account.");
            session.setAttribute("user", userAfter);
            System.out.println("infoupdateaction session attribute user:"+session.getAttribute("user.id")+session.getAttribute("user"));
        }
        return new ActionResult(ActionResult.METHOD.REDIRECT, request.getHeader("referer"));
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
