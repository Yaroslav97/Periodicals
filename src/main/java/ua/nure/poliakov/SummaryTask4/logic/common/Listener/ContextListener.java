package ua.nure.poliakov.SummaryTask4.logic.common.listener;

import org.apache.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ContextListener implements ServletContextListener {

    private static final Logger log = Logger.getLogger(ContextListener.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        log.info("Start Periodicals");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        log.info("Destroy Periodicals");
    }
}
