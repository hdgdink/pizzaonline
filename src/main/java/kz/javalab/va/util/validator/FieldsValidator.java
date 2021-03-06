package kz.javalab.va.util.validator;

import kz.javalab.va.connection.pool.ConnectionPoolException;
import kz.javalab.va.dao.DAOException;
import kz.javalab.va.dao.impl.UserDao;
import kz.javalab.va.entity.user.User;
import kz.javalab.va.util.Constants;
import org.apache.log4j.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class FieldsValidator {
    private final static Logger LOGGER = Logger.getLogger(FieldsValidator.class);

    public static boolean equalNull(String... values) {
        boolean result = false;
        for (String value : values) {
            if (value == null) {
                result = true;
                break;
            }
        }
        return result;
    }

    public static boolean empty(String... values) {
        boolean result = false;
        for (String value : values) {
            if (value.isEmpty()) {
                result = true;
                break;
            }
        }
        return result;
    }

    public static boolean emailValid(String email) throws ValidationException {
        Pattern pattern;
        Matcher matcher;
        try {
            pattern = Pattern.compile(Constants.EMAIL_REGEX_RB);
        } catch (PatternSyntaxException e) {
            LOGGER.debug("Email pattern cannot be compiled.");
            throw new ValidationException(e);
        }
        matcher = pattern.matcher(email);
        if (!matcher.matches()) {
            LOGGER.debug("User email is not valid.");
            return false;
        }
        return true;
    }

    public static boolean passwordValid(String password)
            throws ValidationException {
        Pattern pattern;
        Matcher matcher;
        try {
            pattern = Pattern.compile(Constants.PASSWORD_REGEX_RB);
        } catch (PatternSyntaxException e) {
            LOGGER.debug("Password pattern cannot be compiled.");
            throw new ValidationException(e);
        }
        matcher = pattern.matcher(password);
        if (!matcher.matches()) {
            LOGGER.debug("User password is not valid.");
            return false;
        }
        return true;
    }

    public static boolean userNameCheck(String username) throws ValidationException {
        User user = null;
        try {
            UserDao userDao = new UserDao();
            user = userDao.getByUsername(username);
        } catch (ConnectionPoolException | DAOException e) {
            LOGGER.error(Constants.USER_DAO_INIT_ERROR);
        }
        if (user != null) {
            LOGGER.debug("Username already busy");
            return false;
        }
        return true;
    }
}
