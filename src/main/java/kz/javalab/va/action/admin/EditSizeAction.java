package kz.javalab.va.action.admin;

import kz.javalab.va.action.Action;
import kz.javalab.va.action.ActionException;
import kz.javalab.va.action.ActionResult;
import kz.javalab.va.connection.pool.ConnectionPoolException;
import kz.javalab.va.dao.DAOException;
import kz.javalab.va.dao.impl.SizeDao;
import kz.javalab.va.entity.Size;
import kz.javalab.va.util.AttributeSetter;
import kz.javalab.va.util.Constants;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class EditSizeAction implements Action {
    private static final Logger LOGGER = Logger.getLogger(EditSizeAction.class);
    private SizeDao dao;
    private Size size = null;

    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {
        HttpSession session = request.getSession();
        Integer id = Integer.parseInt(request.getParameter(Constants.ATTRIBUTE_ID));
        Integer value = Integer.parseInt(request.getParameter(Constants.ATTRIBUTE_VAL));
        String name = request.getParameter(Constants.ATTRIBUTE_NAME);
        Boolean active = Boolean.parseBoolean(request.getParameter(Constants.ATTRIBUTE_ACTIVE));
        try {
            size = sizeDao().getById(id);
            size.setSize(value);
            size.setName(name);
            size.setActive(active);
            sizeDao().update(size);
            LOGGER.info("Size was updated");
        } catch (DAOException e) {
            LOGGER.error("Error of SizeDao", e);
            e.printStackTrace();
        }
        AttributeSetter setter = new AttributeSetter();
        setter.setAttributes(session);
        String referer = request.getHeader(Constants.PAGE_REFERER);
        referer = referer.substring(referer.lastIndexOf("/") + 1, referer.length());
        return new ActionResult(ActionResult.METHOD.REDIRECT, referer);
    }

    private SizeDao sizeDao() throws DAOException {
        if (dao == null) {
            try {
                dao = new SizeDao();
            } catch (ConnectionPoolException e) {
                e.printStackTrace();
            }
        }
        return dao;
    }
}

