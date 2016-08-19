package ua.nure.lyubimtsev.SummaryTask4.web.commands.doctor;


import ua.nure.lyubimtsev.SummaryTask4.ForwardingType;
import ua.nure.lyubimtsev.SummaryTask4.Path;
import ua.nure.lyubimtsev.SummaryTask4.Redirect;
import ua.nure.lyubimtsev.SummaryTask4.db.entities.Admin;
import ua.nure.lyubimtsev.SummaryTask4.db.entities.Doctor;
import ua.nure.lyubimtsev.SummaryTask4.db.entities.Patient;
import ua.nure.lyubimtsev.SummaryTask4.web.commands.Command;

import javax.print.Doc;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(name = "DoctorCommand", urlPatterns = "/doctors")
public class GetDoctorsCommand extends Command {

    @Override
    public Redirect execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        HttpSession session = request.getSession();

        List<Doctor> allDoctors = ((Admin) session.getAttribute("user")).getDoctors();
        List<Patient> allPatients = ((Admin) session.getAttribute("user")).getPatients();


        for (Doctor doctor : allDoctors) {
            System.err.println(doctor.getPatients().size());
        }

        List<Doctor> pediatricians = allDoctors
                .stream()
                .filter(doctor -> doctor.getCategory().getName().equals("pediatrician"))
                .collect(Collectors.toList());

        List<Doctor> traumatologists = allDoctors
                .stream()
                .filter(doctor -> doctor.getCategory().getName().equals("traumatologist"))
                .collect(Collectors.toList());

        List<Doctor> surgeons = allDoctors
                .stream()
                .filter(doctor -> doctor.getCategory().getName().equals("surgeon"))
                .collect(Collectors.toList());

        List<Doctor> nurses = allDoctors
                .stream()
                .filter(doctor -> doctor.getCategory().getName().equals("nurse"))
                .collect(Collectors.toList());


        session.setAttribute("allDoctors", allDoctors);
        session.setAttribute("pediatricians", pediatricians);
        session.setAttribute("traumatologists", traumatologists);
        session.setAttribute("surgeons", surgeons);
        session.setAttribute("nurses", nurses);

        return new Redirect(Path.DOCTORS_PAGE, ForwardingType.FORWARD);
    }
}
