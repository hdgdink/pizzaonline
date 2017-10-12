package kz.javalab.va.listener;

import kz.javalab.va.connection.pool.ConnectionPoolException;
import kz.javalab.va.dao.DAOException;
import kz.javalab.va.dao.impl.FoodDao;
import kz.javalab.va.dao.impl.SizeDao;
import kz.javalab.va.entity.Food;
import kz.javalab.va.entity.Size;
import kz.javalab.va.util.AttributeSetter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.List;
import java.util.Locale;

public class SessionListener implements HttpSessionListener {
    private static final String ATTR_LOCALE = "locale";
    private static final Locale DEFAULT_LOCALE = Locale.getDefault();

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        HttpSession session = se.getSession();
        ServletContext context = session.getServletContext();
        context.setAttribute(ATTR_LOCALE, DEFAULT_LOCALE);
        AttributeSetter setter = new AttributeSetter();
        setter.setAttributes(session);

    }
}
