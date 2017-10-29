package kz.javalab.va.action.admin;

import kz.javalab.va.action.Action;
import kz.javalab.va.action.ActionException;
import kz.javalab.va.action.ActionResult;
import kz.javalab.va.connection.pool.ConnectionPoolException;
import kz.javalab.va.dao.DAOException;
import kz.javalab.va.dao.impl.OrderDetailsDao;
import kz.javalab.va.entity.OrderDetails;
import kz.javalab.va.util.Constants;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class ShowOrderDetailsPageAction implements Action {
    private static final Logger LOGGER = Logger.getLogger(ShowOrderDetailsPageAction.class);

    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {
        HttpSession session = request.getSession();
        try {
            List<OrderDetails> detailsList = new OrderDetailsDao().getAll();
            session.setAttribute(Constants.ATTRIBUTE_ALL_ORDER_DETAILS_LIST, detailsList);
        } catch (DAOException | ConnectionPoolException e) {
            LOGGER.error("Error of creating DetailsList in OrderDetailsDao", e);
            throw new ActionException(e);
        }
        return new ActionResult(ActionResult.METHOD.FORWARD, Constants.ACTION_ADMIN_ORDER_DETAILS);
    }
}
