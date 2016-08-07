package ua.nure.lyubimtsev.summarytask4.servlets;

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

@WebServlet(name = "SortPatientsServlet", urlPatterns = "/sortPatientsServlet")
public class SortPatientsServlet extends HttpServlet {

    private static final Comparator<Patient> SORT_PATIENTS_ALPHABETICALLY = new Comparator<Patient>() {
        @Override
        public int compare(Patient o1, Patient o2) {
            return o1.getName().compareTo(o2.getName());
        }
    };

    private static final Comparator<Patient> SORT_PATIENTS_BY_BIRTH_DATE = new Comparator<Patient>() {
        @Override
        public int compare(Patient o1, Patient o2) {
            return o1.getBirthDate().compareTo(o2.getBirthDate());
        }
    };

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        List<Patient> patients = (List<Patient>) session.getAttribute("patients");

        switch (request.getParameter("sort")) {
            case "alphabetically":
                Collections.sort(patients, SORT_PATIENTS_ALPHABETICALLY);
                break;

            case "birthDate":
                Collections.sort(patients, SORT_PATIENTS_BY_BIRTH_DATE);
                break;
        }

        request.setAttribute("patients", patients);
        request.getRequestDispatcher("patients.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
