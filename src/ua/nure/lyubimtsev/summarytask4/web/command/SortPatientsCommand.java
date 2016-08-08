package ua.nure.lyubimtsev.summarytask4.web.command;

import ua.nure.lyubimtsev.summarytask4.ForwardingType;
import ua.nure.lyubimtsev.summarytask4.Path;
import ua.nure.lyubimtsev.summarytask4.Redirect;
import ua.nure.lyubimtsev.summarytask4.db.entities.Patient;
import ua.nure.lyubimtsev.summarytask4.exception.AppException;
import ua.nure.lyubimtsev.summarytask4.web.command.Command;

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
public class SortPatientsCommand extends Command {

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


    @Override
    public Redirect execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
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
        return new Redirect(Path.PAGE_PATIENTS_PAGE, ForwardingType.FORWARD);
    }
}
