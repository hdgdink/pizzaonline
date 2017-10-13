package kz.javalab.va.action.general;

import kz.javalab.va.action.Action;
import kz.javalab.va.action.ActionException;
import kz.javalab.va.action.ActionResult;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Locale;

public class ChangeLocaleAction implements Action {
    private static final Logger LOGGER = Logger.getLogger(ChangeLocaleAction.class);
    private static final String REFERER = "Referer";
    private static final String LOCALE = "locale";

    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {
        HttpSession session = request.getSession();
        String language = request.getParameter(LOCALE);
        Locale locale = new Locale(language);
        LOGGER.info("Selected locale is :" + locale);
        session.setAttribute(LOCALE, locale);
        String referer = request.getHeader(REFERER);
        referer = referer.substring(referer.lastIndexOf("/") + 1, referer.length());
        return new ActionResult(ActionResult.METHOD.REDIRECT, referer);


    }


}

