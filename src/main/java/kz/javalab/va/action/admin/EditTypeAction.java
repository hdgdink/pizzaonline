package kz.javalab.va.action.admin;

import kz.javalab.va.action.Action;
import kz.javalab.va.action.ActionException;
import kz.javalab.va.action.ActionResult;
import kz.javalab.va.connection.pool.ConnectionPoolException;
import kz.javalab.va.dao.DAOException;
import kz.javalab.va.dao.impl.TypeDao;
import kz.javalab.va.entity.Type;
import kz.javalab.va.util.AttributeSetter;
import kz.javalab.va.util.Constants;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class EditTypeAction implements Action {
    private static final Logger LOGGER = Logger.getLogger(EditTypeAction.class);
    private TypeDao dao;
    private Type type = null;

    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {
        HttpSession session = request.getSession();
        Integer id = Integer.parseInt(request.getParameter(Constants.ATTRIBUTE_ID));
        String value = request.getParameter(Constants.ATTRIBUTE_TYPE);
        Boolean active = Boolean.parseBoolean(request.getParameter(Constants.ATTRIBUTE_ACTIVE));
        try {
            type = typeDao().getById(id);
            type.setType(value);
            type.setActive(active);
            typeDao().update(type);
            LOGGER.info("Type was updated");
        } catch (DAOException e) {
            LOGGER.error("Error of TypeDao", e);
            e.printStackTrace();
        }
        AttributeSetter setter = new AttributeSetter();
        setter.setAttributes(session);
        String referer = request.getHeader(Constants.PAGE_REFERER);
        referer = referer.substring(referer.lastIndexOf("/") + 1, referer.length());
        return new ActionResult(ActionResult.METHOD.REDIRECT, referer);
    }

    private TypeDao typeDao() throws DAOException {
        if (dao == null) {
            try {
                dao = new TypeDao();
            } catch (ConnectionPoolException e) {
                e.printStackTrace();
            }
        }
        return dao;
    }
}


