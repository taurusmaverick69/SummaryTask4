package ua.nure.lyubimtsev.SummaryTask4.web.commands.patient;

import ua.nure.lyubimtsev.SummaryTask4.ForwardingType;
import ua.nure.lyubimtsev.SummaryTask4.Path;
import ua.nure.lyubimtsev.SummaryTask4.Redirect;
import ua.nure.lyubimtsev.SummaryTask4.Role;
import ua.nure.lyubimtsev.SummaryTask4.db.entities.Admin;
import ua.nure.lyubimtsev.SummaryTask4.db.entities.Doctor;
import ua.nure.lyubimtsev.SummaryTask4.db.entities.Patient;
import ua.nure.lyubimtsev.SummaryTask4.db.entities.State;
import ua.nure.lyubimtsev.SummaryTask4.exception.AppException;
import ua.nure.lyubimtsev.SummaryTask4.web.commands.Command;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(name = "PatientServlet", urlPatterns = "/patients")
public class GetPatientsCommand extends Command {


    @Override
    public Redirect execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {

        HttpSession session = request.getSession();
        List<Patient> patients = new ArrayList<>();
        List<Doctor> doctors = new ArrayList<>();

        Role role = (Role) session.getAttribute("role");
        Object user = session.getAttribute("user");

        int doctorId;
        switch (role) {
            case ADMIN:
                doctorId = Integer.parseInt(request.getParameter("doctorId"));
                Admin admin = (Admin) user;

                List<Patient> allPatients = admin.getPatients();


                patients = allPatients
                        .stream()
                        .filter(patient -> patient.getDoctor_id() == doctorId)
                        .collect(Collectors.toList());

                System.err.println(patients.size());

                doctors = admin.getDoctors();
                break;
            case DOCTOR:
                patients = ((Doctor) user).getPatients();
                doctors.add((Doctor) user);
                break;
        }

        session.setAttribute("patients", patients);
        session.setAttribute("doctors", doctors);
        return new Redirect(Path.PATIENTS_PAGE, ForwardingType.FORWARD);

    }
}
