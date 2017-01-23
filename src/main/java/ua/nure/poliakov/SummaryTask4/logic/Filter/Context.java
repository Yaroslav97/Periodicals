package ua.nure.poliakov.SummaryTask4.logic.Filter;

import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(filterName = "profiler", urlPatterns = {"/*"})
public class Context implements Filter {

    private static final Logger log = Logger.getLogger(Context.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.debug("Start application");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        log.debug("Destroy application");
    }
}
