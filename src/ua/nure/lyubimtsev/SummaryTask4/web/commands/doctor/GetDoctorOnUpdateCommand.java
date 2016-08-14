package ua.nure.lyubimtsev.SummaryTask4.web.commands.doctor;

import ua.nure.lyubimtsev.SummaryTask4.ForwardingType;
import ua.nure.lyubimtsev.SummaryTask4.Path;
import ua.nure.lyubimtsev.SummaryTask4.Redirect;
import ua.nure.lyubimtsev.SummaryTask4.db.entities.Admin;
import ua.nure.lyubimtsev.SummaryTask4.db.entities.Doctor;
import ua.nure.lyubimtsev.SummaryTask4.exception.AppException;
import ua.nure.lyubimtsev.SummaryTask4.web.commands.Command;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(name = "GetDoctorOnEditCommand", urlPatterns = "/getDoctorOnEditServlet")
public class GetDoctorOnUpdateCommand extends Command {

    @Override
    public Redirect execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        int id = Integer.parseInt(request.getParameter("id"));

        HttpSession session = request.getSession();
        List<Doctor> doctors = ((Admin) session.getAttribute("user")).getDoctors();

        Doctor doctorById = doctors
                .stream()
                .filter(doctor -> doctor.getId() == id)
                .collect(Collectors.collectingAndThen(Collectors.toList(), list -> list.get(0)));

        session.setAttribute("doctor", doctorById);

        return new Redirect(Path.UPDATE_DOCTOR_PAGE, ForwardingType.FORWARD);
    }
}
