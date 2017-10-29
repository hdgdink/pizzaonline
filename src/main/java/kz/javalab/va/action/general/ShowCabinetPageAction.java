package kz.javalab.va.action.general;

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
import java.util.List;

public class ShowCabinetPageAction implements Action {
    private static final Logger LOGGER = Logger.getLogger(ShowCabinetPageAction.class);

    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {
        ActionResult result = null;
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(Constants.ATTRIBUTE_USER);
        if (user.getRole().equals(Role.CLIENT))
            result = new ActionResult(ActionResult.METHOD.FORWARD, Constants.PAGE_CABINET);
        if (user.getRole().equals(Role.ADMIN)) {
            try {
                List<User> userList = new UserDao().getAll();
                session.setAttribute(Constants.ATTRIBUTE_USER_LIST, userList);
            } catch (DAOException | ConnectionPoolException e) {
                LOGGER.error(Constants.USER_DAO_INIT_ERROR, e);
                throw new ActionException(e);
            }
            result = new ActionResult(ActionResult.METHOD.FORWARD, Constants.PAGE_ADMIN_CABINET);
        }
        return result;
    }
}

