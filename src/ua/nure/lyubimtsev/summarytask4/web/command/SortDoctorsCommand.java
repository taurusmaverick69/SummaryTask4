package ua.nure.lyubimtsev.SummaryTask4.web.command;

import ua.nure.lyubimtsev.SummaryTask4.ForwardingType;
import ua.nure.lyubimtsev.SummaryTask4.Path;
import ua.nure.lyubimtsev.SummaryTask4.Redirect;
import ua.nure.lyubimtsev.SummaryTask4.exception.AppException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "SortDoctorsServlet", urlPatterns = "/sortDoctorsServlet")
public class SortDoctorsCommand extends Command {

//    private static final Comparator<Doctor> SORT_DOCTORS_ALPHABETICALLY = new Comparator<Doctor>() {
//        @Override
//        public int compare(Doctor o1, Doctor o2) {
//            return o1.getName().compareTo(o2.getName());
//        }
//    };
//
//    private static final Comparator<Doctor> SORT_DOCTORS_BY_CATEGORY = new Comparator<Doctor>() {
//        @Override
//        public int compare(Doctor o1, Doctor o2) {
//            return o1.getCategory().getName().compareTo(o2.getCategory().getName());
//        }
//    };
//
//    private static final Comparator<Doctor> SORT_DOCTORS_BY_NUMBER_OF_PATIENTS = new Comparator<Doctor>() {
//        @Override
//        public int compare(Doctor o1, Doctor o2) {
//            return o1.getPatients().size() - o2.getPatients().size();
//        }
//    };
//
//
    @Override
    public Redirect execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
//        HttpSession session = request.getSession();
//        List<Doctor> doctorsByCategory = (List<Doctor>) session.getAttribute("doctorsByCategory");
//
//        switch (request.getParameter("sort")) {
//            case "alphabetically":
//                Collections.sort(doctorsByCategory, SORT_DOCTORS_ALPHABETICALLY);
//                break;
//            case "category":
//                Collections.sort(doctorsByCategory, SORT_DOCTORS_BY_CATEGORY);
//                break;
//            case "numberOfPatients":
//                Collections.sort(doctorsByCategory, SORT_DOCTORS_BY_NUMBER_OF_PATIENTS);
//                break;
//        }
//
//        request.setAttribute("doctorsByCategory", doctorsByCategory);
        return new Redirect(Path.PAGE_DOCTORS_PAGE, ForwardingType.FORWARD);
    }
}
