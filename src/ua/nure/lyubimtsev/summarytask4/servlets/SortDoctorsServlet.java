package ua.nure.lyubimtsev.summarytask4.servlets;

import ua.nure.lyubimtsev.summarytask4.entities.Doctor;
import ua.nure.lyubimtsev.summarytask4.entities.Patient;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@WebServlet(name = "SortDoctorsServlet", urlPatterns = "/sortDoctorsServlet")
public class SortDoctorsServlet extends HttpServlet {

    private static final Comparator<Doctor> SORT_DOCTORS_ALPHABETICALLY = new Comparator<Doctor>() {
        @Override
        public int compare(Doctor o1, Doctor o2) {
            return o1.getName().compareTo(o2.getName());
        }
    };

    private static final Comparator<Doctor> SORT_DOCTORS_BY_CATEGORY = new Comparator<Doctor>() {
        @Override
        public int compare(Doctor o1, Doctor o2) {
            return o1.getCategory().getName().compareTo(o2.getCategory().getName());
        }
    };

    private static final Comparator<Doctor> SORT_DOCTORS_BY_NUMBER_OF_PATIENTS = new Comparator<Doctor>() {
        @Override
        public int compare(Doctor o1, Doctor o2) {
            return o1.getPatients().size() - o2.getPatients().size();
        }
    };


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        List<Doctor> doctorsByCategory = (List<Doctor>) session.getAttribute("doctorsByCategory");

        switch (request.getParameter("sort")) {
            case "alphabetically":
                Collections.sort(doctorsByCategory, SORT_DOCTORS_ALPHABETICALLY);
                break;
            case "category":
                Collections.sort(doctorsByCategory, SORT_DOCTORS_BY_CATEGORY);
                break;
            case "numberOfPatients":
                Collections.sort(doctorsByCategory, SORT_DOCTORS_BY_NUMBER_OF_PATIENTS);
                break;
        }

        request.setAttribute("doctorsByCategory", doctorsByCategory);
        request.getRequestDispatcher("doctors.jsp").forward(request, response);

    }
}
