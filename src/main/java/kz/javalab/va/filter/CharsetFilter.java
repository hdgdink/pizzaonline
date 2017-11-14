package kz.javalab.va.filter;

import kz.javalab.va.util.Constants;
import org.apache.log4j.Logger;

import javax.servlet.*;
import java.io.IOException;


public class CharsetFilter implements Filter {
    private final static Logger LOGGER = Logger.getLogger(CharsetFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        LOGGER.debug("Charset filter init...");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        LOGGER.debug("Charset filter");
        request.setCharacterEncoding(Constants.UTF_8);
        LOGGER.debug(request.getParameter(Constants.PAR_TOPIC));
        chain.doFilter(request, response);
    }
}