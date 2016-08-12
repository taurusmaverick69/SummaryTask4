package ua.nure.lyubimtsev.SummaryTask4.web.command.patient;

import ua.nure.lyubimtsev.SummaryTask4.ForwardingType;
import ua.nure.lyubimtsev.SummaryTask4.Path;
import ua.nure.lyubimtsev.SummaryTask4.Redirect;
import ua.nure.lyubimtsev.SummaryTask4.db.entities.Admin;
import ua.nure.lyubimtsev.SummaryTask4.db.entities.Doctor;
import ua.nure.lyubimtsev.SummaryTask4.db.entities.Patient;
import ua.nure.lyubimtsev.SummaryTask4.exception.AppException;
import ua.nure.lyubimtsev.SummaryTask4.web.command.Command;

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
        List<Doctor> doctors = ((Admin) session.getAttribute("admin")).getDoctors();
        List<Patient> patients = new ArrayList<>();

        int id = Integer.parseInt(request.getParameter("id"));

        if (id == 0) {

            for (Doctor doctor : doctors) {
                patients.addAll(doctor.getPatients());
            }
        } else {
            Doctor myDoctor = doctors
                    .stream()
                    .filter(doctor -> doctor.getId() == id)
                    .collect(Collectors.collectingAndThen(Collectors.toList(), list -> list.get(0)));

            patients = myDoctor.getPatients();
        }



        session.setAttribute("patients", patients);
        return new Redirect(Path.PAGE_PATIENTS_PAGE, ForwardingType.FORWARD);
    }
}
