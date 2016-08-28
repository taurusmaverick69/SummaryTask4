package ua.nure.lyubimtsev.SummaryTask4.web.commands.patient;

import ua.nure.lyubimtsev.SummaryTask4.ForwardingType;
import ua.nure.lyubimtsev.SummaryTask4.Path;
import ua.nure.lyubimtsev.SummaryTask4.Redirect;
import ua.nure.lyubimtsev.SummaryTask4.Role;
import ua.nure.lyubimtsev.SummaryTask4.db.entities.Admin;
import ua.nure.lyubimtsev.SummaryTask4.db.entities.Doctor;
import ua.nure.lyubimtsev.SummaryTask4.db.entities.Patient;
import ua.nure.lyubimtsev.SummaryTask4.exception.AppException;
import ua.nure.lyubimtsev.SummaryTask4.web.commands.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GetPatientsCommand extends Command {

    private static final String PAGE_TITLE_ATTRIBUTE = "pageTitle";
    private static final String LOCALE_KEY = "patients";

    @Override
    public Redirect execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {

        HttpSession session = request.getSession();

        Role role = (Role) session.getAttribute("role");
        Object user = session.getAttribute("user");

        session.setAttribute("states", factory.getStateDAO().getAllStates());

        List<Patient> patients = new ArrayList<>();

        int doctorId = 0;
        Doctor doctor;
        switch (role) {
            case ADMIN:
                doctorId = Integer.parseInt(request.getParameter("doctorId"));
                Admin admin = (Admin) user;
                patients = admin.getDoctorById(doctorId).getPatients();
                break;
            case DOCTOR:
                doctor = (Doctor) user;
                patients = doctor.getPatients();
                doctorId = doctor.getId();
                break;
        }
        session.setAttribute("doctorId", doctorId);


        session.setAttribute("patients", patients);
        request.setAttribute(PAGE_TITLE_ATTRIBUTE, LOCALE_KEY);
        return new Redirect(Path.PATIENTS_PAGE, ForwardingType.FORWARD);

    }
}