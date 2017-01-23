package ua.nure.poliakov.SummaryTask4.logic.Listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        sce.getServletContext().setAttribute("path", "WEB-INF//pages//");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
