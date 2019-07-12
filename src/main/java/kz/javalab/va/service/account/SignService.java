package kz.javalab.va.service.account;

import kz.javalab.va.dao.DaoException;
import kz.javalab.va.dao.impl.UserDao;
import kz.javalab.va.entity.user.Role;
import kz.javalab.va.entity.user.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@ComponentScan
public class SignService {
    private final Logger logger = Logger.getRootLogger();
    private final ResourceBundle RB_VALIDATOR = ResourceBundle.getBundle("validator");

    @Autowired
    private UserDao userDao;

    public SignService(UserDao userDao) {
        this.userDao = userDao;
    }

    public SignService() {
    }

    public Boolean validateUserName(User user) {
        String username = user.getUsername();

        try {
            if (userDao.getByUsername(username).getUsername() != null) {
                logger.info("Username is busy");
                return false;
            }
        } catch (DaoException e) {
            e.printStackTrace();
        }

        logger.info("Username is empty");
        return true;
    }

    public Boolean validateEmail(User user) {
        final String EMAIL_REGEX_RB = RB_VALIDATOR.getString("validation.email");
        Pattern pattern = Pattern.compile(EMAIL_REGEX_RB);
        Matcher matcher = pattern.matcher(user.getEmail());

        if (!matcher.matches()) {
            logger.debug("User email is not valid.");
            return false;
        }

        logger.info("Email validation is ok");
        return true;
    }

    public Boolean validatePass(User user) {
        final String PASSWORD_REGEX_RB = RB_VALIDATOR.getString("validation.password");
        Pattern pattern = Pattern.compile(PASSWORD_REGEX_RB);
        Matcher matcher = pattern.matcher(user.getPassword());

        if (!matcher.matches()) {
            logger.info("User password is not valid.");
            return false;
        }

        logger.info("Password  validation is ok");
        return true;
    }

    public Boolean comparePasswords(User user, String rePass) {
        if (!user.getPassword().equals(rePass)) {
            logger.info("Passwords is not match.");
            return false;
        }

        logger.info("Passwords is match");
        return true;
    }

    public User createUser(User user) {
        String username = user.getUsername();

        try {
            user.setRole(Role.CLIENT);
            user.setBalance(0);
            userDao.create(user);
            //getting already created user with id;
            user = userDao.getByUsername(username);
            logger.info("User creation successful");
        } catch (DaoException e) {
            e.printStackTrace();
        }

        return user;
    }
}