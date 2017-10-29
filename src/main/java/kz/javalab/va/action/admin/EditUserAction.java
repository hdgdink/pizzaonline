package kz.javalab.va.action.admin;

import kz.javalab.va.action.Action;
import kz.javalab.va.action.ActionException;
import kz.javalab.va.action.ActionResult;
import kz.javalab.va.connection.pool.ConnectionPoolException;
import kz.javalab.va.dao.DAOException;
import kz.javalab.va.dao.impl.UserDao;
import kz.javalab.va.entity.user.Role;
import kz.javalab.va.entity.user.User;
import kz.javalab.va.util.Constants;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class EditUserAction implements Action {
    private static final Logger LOGGER = Logger.getLogger(EditUserAction.class);

    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {
        UserDao userDao;
        try {
            userDao = new UserDao();
        } catch (ConnectionPoolException e) {
            LOGGER.error(Constants.USER_DAO_INIT_ERROR, e);
            throw new ActionException(e);
        }
        HttpSession session = request.getSession();
        Integer id = Integer.parseInt(request.getParameter(Constants.ATTRIBUTE_ID));
        String username = request.getParameter(Constants.ATTRIBUTE_USERNAME);
        String email = request.getParameter(Constants.ATTRIBUTE_EMAIL);
        String firstName = request.getParameter(Constants.ATTRIBUTE_FIRSTNAME);
        String lastName = request.getParameter(Constants.ATTRIBUTE_LASTNAME);
        Integer balance = Integer.parseInt(request.getParameter(Constants.ATTRIBUTE_BALANCE));
        String password = request.getParameter(Constants.ATTRIBUTE_PASSWORD);
        Role role = Role.valueOf(request.getParameter(Constants.ATTRIBUTE_ROLE));
        try {
            User user = userDao.getById(id);
            if ((!user.getUsername().equals(username)) && (userDao.getUsersListByUsername(username) != null)) {
                LOGGER.info("Username is busy, select another");
                session.setAttribute(Constants.ATTRIBUTE_ERROR, Constants.USER_EXIST_ERROR);
            } else {
                user.setUsername(username);
                user.setId(id);
                user.setEmail(email);
                user.setFirstname(firstName);
                user.setLastname(lastName);
                user.setBalance(balance);
                user.setPassword(password);
                user.setRole(role);
                userDao.update(user);
                LOGGER.debug("User" + user.getId() + "updated");
            }
        } catch (DAOException e) {
            LOGGER.error("Error of SizeDao", e);
            throw new ActionException(e);
        }
        String referer = request.getHeader(Constants.PAGE_REFERER);
        referer = referer.substring(referer.lastIndexOf("/") + 1, referer.length());
        return new ActionResult(ActionResult.METHOD.REDIRECT, referer);
    }

}
