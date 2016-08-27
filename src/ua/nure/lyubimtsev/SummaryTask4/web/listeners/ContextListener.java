package ua.nure.lyubimtsev.SummaryTask4.web.listeners;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import ua.nure.lyubimtsev.SummaryTask4.Path;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.*;

/**
 * Context listeners.
 *
 * @author Vladislav
 */
@WebListener()
public class ContextListener implements ServletContextListener {

    private static final Logger LOG = Logger.getLogger(ContextListener.class);

    public void contextDestroyed(ServletContextEvent event) {

        log("Servlet context destruction starts");
        // no op
        log("Servlet context destruction finished");
    }

    public void contextInitialized(ServletContextEvent event) {

        Map<String, String> adminBreadCrumbs = new LinkedHashMap<>();
        adminBreadCrumbs.put(Path.GET_DOCTORS_COMMAND, "Доктора");
        adminBreadCrumbs.put(Path.GET_PATIENTS_COMMAND, "Пациенты");


        Map<String, String> doctorBreadCrumbs = new LinkedHashMap<>();
        doctorBreadCrumbs.put(Path.GET_PATIENTS_COMMAND, "Пациенты");
        doctorBreadCrumbs.put(Path.GET_APPOINTMENTS_COMMAND, "Мед карта пациента");


        log("Servlet context initialization starts");

        ServletContext servletContext = event.getServletContext();
        initLog4J(servletContext);
        initCommandContainer();

        log("Servlet context initialization finished");

        servletContext.setAttribute("adminBreadCrumbs", adminBreadCrumbs);
        servletContext.setAttribute("doctorBreadCrumbs", doctorBreadCrumbs);
    }

    /**
     * Initializes log4j framework.
     *
     * @param servletContext
     */
    private void initLog4J(ServletContext servletContext) {
        log("Log4J initialization started");
        try {
            PropertyConfigurator.configure(
                    servletContext.getRealPath("WEB-INF/log4j.properties"));
            LOG.debug("Log4j has been initialized");
        } catch (Exception ex) {
            log("Cannot configure Log4j");
            ex.printStackTrace();
        }
        log("Log4J initialization finished");
    }

    /**
     * Initializes CommandContainer.
     */
    private void initCommandContainer() {

        // initialize commands container
        // just load class to JVM

        try {
            Class.forName("ua.nure.lyubimtsev.SummaryTask4.web.commands.CommandContainer");
        } catch (ClassNotFoundException ex) {
            throw new IllegalStateException("Cannot initialize Command Container");
        }
    }

    private void log(String msg) {
        System.out.println("[ua.nure.lyubimtsev.SummaryTask4.web.listeners.ContextListener] " + msg);
    }
}