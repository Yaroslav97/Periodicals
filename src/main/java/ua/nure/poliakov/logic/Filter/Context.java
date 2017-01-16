package ua.nure.poliakov.logic.Filter;

import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter("/*")
public class Context implements Filter {

    private static final Logger log = Logger.getLogger(Context.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.debug("Start application");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        chain.doFilter(request, response);
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
    }

    @Override
    public void destroy() {
        log.debug("Destroy application");
    }
}