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

    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {
        HttpSession session = request.getSession();
        TypeDao typeDao;
        Integer id = Integer.parseInt(request.getParameter(Constants.ATTRIBUTE_ID));
        try {
            typeDao = new TypeDao();
            Type type = typeDao.getById(id);
            CreateEntityAdmin.setType(type, request);
            typeDao.update(type);
            LOGGER.info("Type was updated");
        } catch (ConnectionPoolException e) {
            LOGGER.error("Error of initialization TypeDao", e);
            throw new ActionException(e);
        } catch (DAOException e) {
            LOGGER.error("Error updating Type", e);
            throw new ActionException(e);
        }
        AttributeSetter setter = new AttributeSetter();
        setter.setAttributes(session);
        String referer = request.getHeader(Constants.PAGE_REFERER);
        referer = referer.substring(referer.lastIndexOf("/") + 1, referer.length());
        return new ActionResult(ActionResult.METHOD.REDIRECT, referer);
    }
}


