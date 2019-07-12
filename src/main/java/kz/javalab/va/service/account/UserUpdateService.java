package kz.javalab.va.service.account;

import kz.javalab.va.dao.DaoException;
import kz.javalab.va.dao.impl.UserDao;
import kz.javalab.va.entity.user.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Objects;

@Service
@ComponentScan
public class UserUpdateService {
    private static final Logger logger = Logger.getRootLogger();
    private static final String USER = "user";
    @Autowired
    private UserDao userDao;

    public UserUpdateService(UserDao userDao) {
        this.userDao = userDao;
    }

    public UserUpdateService() {
    }

    public void updateInfo(HttpSession session, String email, String firstName, String lastName) {
        User userBefore = (User) session.getAttribute(USER);
        User userAfter = new User();

        userAfter = setNewUserInfo(email, firstName, lastName, userBefore, userAfter);
        updateUser(userAfter, session, userBefore);
    }

    public void updatePass(HttpSession session, String newPassword1) {
        User user = (User) session.getAttribute(USER);

        try {
            logger.info("user is " + user.getUsername() + " new pass: " + newPassword1);
            userDao.resetPassword(newPassword1, user.getId());
            user.setPassword(newPassword1);

            session.setAttribute(USER, user);
            session.setAttribute("changePassSuccess", "password.changePasswordSuccess");
            logger.debug("Password has been changed.");
        } catch (DaoException e) {
            logger.error("Password can't be changed.");

        }
    }

    private User setNewUserInfo(String email, String firstName, String lastName, User userBefore, User userAfter) {
        userAfter.setId(userBefore.getId());
        userAfter.setEmail(email);
        userAfter.setFirstname(firstName);
        userAfter.setLastname(lastName);
        userAfter.setUsername(userBefore.getUsername());
        userAfter.setBalance(userBefore.getBalance());
        userAfter.setPassword(userBefore.getPassword());
        userAfter.setRole(userBefore.getRole());

        return userAfter;
    }

    private void updateUser(User userAfter, HttpSession session, User userBefore) {
        try {
            userDao.update(userAfter);
            logger.debug("User " + userAfter.getUsername() + " has been updated.");
        } catch (DaoException e) {
            logger.error("Error in UserDao during user updating", e);
            e.printStackTrace();
        }

        if (Objects.equals(userBefore.getId(), userAfter.getId())) {
            logger.debug("User's own account.");
            session.setAttribute("user", userAfter);
        }

        logger.debug("account has been updated.");
    }
}