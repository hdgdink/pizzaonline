package kz.javalab.va.action.general;

import kz.javalab.va.action.Action;
import kz.javalab.va.action.ActionException;
import kz.javalab.va.action.ActionResult;
import kz.javalab.va.connection.pool.ConnectionPoolException;
import kz.javalab.va.dao.DAOException;
import kz.javalab.va.dao.impl.UserDao;
import kz.javalab.va.entity.user.Role;
import kz.javalab.va.entity.user.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by HdgDink} on 03.10.2017.
 */
public class ShowCabinetPageAction implements Action {
    private ActionResult result;


    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user.getRole().equals(Role.CLIENT))
            result = new ActionResult(ActionResult.METHOD.FORWARD, "user_cabinet");
        else if (user.getRole().equals(Role.ADMIN)) {
            try {
                List<User> userList = new UserDao().getAll();
                session.setAttribute("user_list",userList);
            } catch (DAOException e) {
                e.printStackTrace();
            } catch (ConnectionPoolException e) {
                e.printStackTrace();
            }
            result = new ActionResult(ActionResult.METHOD.FORWARD, "admin_cabinet");
        }
        return result;
    }
}

