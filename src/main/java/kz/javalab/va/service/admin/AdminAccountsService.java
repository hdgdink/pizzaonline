package kz.javalab.va.service.admin;

import kz.javalab.va.dao.DaoException;
import kz.javalab.va.dao.impl.UserDao;
import kz.javalab.va.entity.user.Role;
import kz.javalab.va.entity.user.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@ComponentScan
public class AdminAccountsService extends AdminService<User> {
    private final static Logger logger = Logger.getRootLogger();
    @Autowired
    private UserDao userDao;

    public AdminAccountsService(UserDao userDao) {
        this.userDao = userDao;
    }

    public AdminAccountsService() {
    }

    public List<User> getUserList() {
        return getList(userDao);
    }

    public List<Role> getRoles() {
        return Arrays.asList(Role.values());
    }

    public Boolean updateUser(User updUser) {
        int namesCount = 0;
        User oldUser = userDao.getById(updUser.getId());

        try {
            namesCount = userDao.getUsersListByUsername(updUser.getUsername());
        } catch (DaoException e) {
            e.printStackTrace();
        }

        logger.info("names count " + namesCount);

        if (namesCount > 0 && !oldUser.getUsername().equals(updUser.getUsername())) {
            logger.info("username is busy");
            return false;
        } else {
            try {
                userDao.update(updUser);
                logger.info("user updated");
            } catch (DaoException e) {
                e.printStackTrace();
            }
        }

        return true;
    }

    public Boolean createUser(User user) {
        String username;

        try {
            username = userDao.getByUsername(user.getUsername()).getUsername();

            if (username == null) {
                userDao.create(user);
            } else {
                logger.info("username is busy");
                return false;
            }
        } catch (DaoException e) {
            e.printStackTrace();
        }

        return true;
    }
}
