package kz.javalab.va.action.account;

import kz.javalab.va.action.Action;
import kz.javalab.va.action.ActionException;
import kz.javalab.va.action.ActionResult;
import kz.javalab.va.connection.pool.ConnectionPool;
import kz.javalab.va.dao.DaoException;
import kz.javalab.va.dao.DaoFactory;
import kz.javalab.va.dao.impl.UserDao;
import kz.javalab.va.entity.user.Role;
import kz.javalab.va.entity.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Connection;

public class RegisterAction implements Action {
    private static final Logger LOGGER = LoggerFactory.getLogger(RegisterAction.class);
    private static final ActionResult REG_SUCCESS = new ActionResult(ActionResult.METHOD.FORWARD,"main_loged");
    private ActionResult result;
    private DaoFactory factory;
    private UserDao dao;


    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {
        User user;
        HttpSession session = request.getSession();

        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");

        int id = 0;

        LOGGER.debug("Create user action...");

        user = new User();


        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);
        user.setFirstname(firstname);
        user.setLastname(lastname);
        user.setBalance(0);
        user.setRole(Role.CLIENT);

        LOGGER.debug("User " + user.getEmail()
                + " has been created. User id: " + user.getId());
        factory = new DaoFactory(ConnectionPool.getInstance());
        dao = factory.getUserDao();
        try {
            dao.persist(user);

        } catch (DaoException e) {
            e.printStackTrace();
        }

        if (user != null) {
            result = REG_SUCCESS;
        }

        return result;
    }


}
