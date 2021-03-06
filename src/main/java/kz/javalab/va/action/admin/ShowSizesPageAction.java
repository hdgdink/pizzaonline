package kz.javalab.va.action.admin;

import kz.javalab.va.action.Action;
import kz.javalab.va.action.ActionException;
import kz.javalab.va.action.ActionResult;
import kz.javalab.va.connection.pool.ConnectionPoolException;
import kz.javalab.va.dao.DAOException;
import kz.javalab.va.dao.impl.SizeDao;
import kz.javalab.va.entity.Size;
import kz.javalab.va.util.Constants;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class ShowSizesPageAction implements Action {
    private static final Logger LOGGER = Logger.getLogger(ShowSizesPageAction.class);

    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {
        HttpSession session = request.getSession();
        try {
            List<Size> sizeList = new SizeDao().getAll();
            session.setAttribute(Constants.ATTRIBUTE_ALL_SIZES_ADMIN, sizeList);
        } catch (DAOException | ConnectionPoolException e) {
            LOGGER.error("Error of creating SizeList in SizeDao", e);
            throw new ActionException(e);
        }
        return new ActionResult(ActionResult.METHOD.FORWARD, Constants.ACTION_ADMIN_SIZES);
    }
}
