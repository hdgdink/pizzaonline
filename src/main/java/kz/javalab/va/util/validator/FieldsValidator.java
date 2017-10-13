package kz.javalab.va.util.validator;

import kz.javalab.va.connection.pool.ConnectionPoolException;
import kz.javalab.va.dao.DAOException;
import kz.javalab.va.dao.impl.UserDao;
import kz.javalab.va.entity.user.User;
import org.apache.log4j.Logger;

import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class FieldsValidator {
    private final static Logger LOGGER = Logger.getLogger(FieldsValidator.class);
    private static final ResourceBundle RB = ResourceBundle.getBundle("validator");
    private static final String EMAIL_REGEX = RB.getString("validation.email");
    private static final String PASSWORD_REGEX = RB.getString("validation.password");

    /**
     * Is used to check whether values are null or not.
     *
     * @param values The values to check.
     * @return true if there is at least one null value, false otherwise.
     */
    public static boolean equalNull(String... values) {
        boolean result = false;
        for (int i = 0; i < values.length; i++) {
            if (values[i] == null) {
                result = true;
                break;
            }
        }
        return result;
    }

    /**
     * Is used to check whether values are null or not.
     *
     * @param values The values to check.
     * @return true if there is at least one empty value, false otherwise.
     */
    public static boolean empty(String... values) {
        boolean result = false;
        for (int i = 0; i < values.length; i++) {
            if (values[i].isEmpty()) {
                result = true;
                break;
            }
        }
        return result;
    }

    /**
     * Is used to check whether email value is valid.
     *
     * @param email The user email.
     * @return True if email is valid, false otherwise.
     * @throws ValidationException If property manager cannot be taken or email
     *                             pattern is not valid
     */
    public static boolean emailValid(String email) throws ValidationException {
        Pattern pattern;
        Matcher matcher;
        try {
            pattern = Pattern.compile(EMAIL_REGEX);
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

    /**
     * Is used to check whether password value is valid.
     *
     * @param password The user password to validate.
     * @return True if password is valid, false otherwise.
     * @throws ValidationException If property manager cannot be taken or email
     *                             pattern is not valid
     */
    public static boolean passwordValid(String password)
            throws ValidationException {
        Pattern pattern;
        Matcher matcher;
        try {
            pattern = Pattern.compile(PASSWORD_REGEX);
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

    public static boolean userNameCheck(String username)
            throws ValidationException {
        User user = null;
        try {
            UserDao userDao = new UserDao();
            user = userDao.getByUsername(username);
        } catch (ConnectionPoolException e) {
            e.printStackTrace();
        } catch (DAOException e) {
            e.printStackTrace();
        }
        if (user != null) {
            LOGGER.debug("Username alredy busy");
            return false;
        }
        return true;
    }
}
