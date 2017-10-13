package kz.javalab.va.filter;

import org.apache.log4j.Logger;

import javax.servlet.*;
import java.io.IOException;


public class CharsetFilter implements javax.servlet.Filter {
    private final static Logger LOGGER = Logger.getLogger(CharsetFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        LOGGER.debug("Charset filter");
        request.setCharacterEncoding("UTF-8");
        LOGGER.debug(request.getParameter("topic"));
        chain.doFilter(request, response);
    }
}