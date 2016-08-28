package ua.nure.lyubimtsev.SummaryTask4.web.filtres;

import ua.nure.lyubimtsev.SummaryTask4.Path;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.util.*;

@WebFilter("/controller")
public class BreadcrumbFilter implements Filter {


    private Map<String, String> adminBreadCrumbs = new LinkedHashMap<>();
    private Map<String, String> doctorBreadCrumbs = new LinkedHashMap<>();

    public void init(FilterConfig config) throws ServletException {

    }


    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

//        System.out.println("BreadcrumbFilter.doFilter");
//
//
//        List<String> allowCommands = Arrays.asList("doctors", "patients.field");
//        String command = req.getParameter("command");
//
//        ResourceBundle bundle1 = ResourceBundle.getBundle("resources");
//
//        Locale locale1 = bundle1.getLocale();
//
//
//        if (allowCommands.contains(command)) {
//
//            String locale = req.getParameter("locale");
//            ResourceBundle bundle =
//                    locale == null ?
//                            ResourceBundle.getBundle("resources") :
//                            ResourceBundle.getBundle("resources" , new Locale(locale));
//
//
//            String doctorsByLocale = bundle.getString("doctor.breadcrumb.doctors");
//            String patientsByLocale = bundle.getString("doctor.breadcrumb.patients");
//
//            adminBreadCrumbs.put(Path.GET_DOCTORS_COMMAND, doctorsByLocale);
//            adminBreadCrumbs.put(Path.GET_PATIENTS_COMMAND, patientsByLocale);
//
//            doctorBreadCrumbs.put(Path.GET_PATIENTS_COMMAND, "Пациенты");
//            doctorBreadCrumbs.put(Path.GET_APPOINTMENTS_COMMAND, "Мед карта пациента");
//
//
//            req.setAttribute("adminBreadCrumbs", adminBreadCrumbs);
//            req.setAttribute("doctorBreadCrumbs", doctorBreadCrumbs);
//
//        }

        chain.doFilter(req, resp);
    }


    public void destroy() {
    }

}
