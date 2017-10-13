package kz.javalab.va.filter;

import org.apache.log4j.Logger;

import javax.servlet.*;
import java.io.IOException;


public class CharsetFilter implements javax.servlet.Filter {
    private final static Logger LOGGER = Logger.getLogger(CharsetFilter.class);
    private static final String UTF_8 = "UTF-8";
    private static final String TOPIC = "topic";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        LOGGER.debug("Charset filter");
        request.setCharacterEncoding(UTF_8);
        LOGGER.debug(request.getParameter(TOPIC));
        chain.doFilter(request, response);
    }
}