package kz.javalab.va.listener;

import kz.javalab.va.util.AttributeSetter;
import kz.javalab.va.util.Constants;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionListener implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        HttpSession session = se.getSession();
        ServletContext context = session.getServletContext();
        context.setAttribute(Constants.ATTRIBUTE_LOCALE, Constants.DEFAULT_LOCALE);
        AttributeSetter setter = new AttributeSetter();
        setter.setAttributes(session);

    }
}
