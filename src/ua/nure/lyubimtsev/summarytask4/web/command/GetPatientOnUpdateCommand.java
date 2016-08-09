package ua.nure.lyubimtsev.SummaryTask4.web.command;

import ua.nure.lyubimtsev.SummaryTask4.ForwardingType;
import ua.nure.lyubimtsev.SummaryTask4.Path;
import ua.nure.lyubimtsev.SummaryTask4.Redirect;
import ua.nure.lyubimtsev.SummaryTask4.db.dao.DAOFactory;
import ua.nure.lyubimtsev.SummaryTask4.db.entities.Role;
import ua.nure.lyubimtsev.SummaryTask4.db.entities.User;
import ua.nure.lyubimtsev.SummaryTask4.db.entities.Patient;
import ua.nure.lyubimtsev.SummaryTask4.exception.AppException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(name = "GetPatientOnEditServlet", urlPatterns = "/getPatientOnEditServlet")
public class GetPatientOnUpdateCommand extends Command {

    @Override
    public Redirect execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        int id = Integer.parseInt(request.getParameter("id"));

        HttpSession session = request.getSession();
        List<User> doctors = ((User) session.getAttribute("user")).getDoctors();

        User doctorById = doctors
                .stream()
                .filter(doctor -> doctor.getId() == id)
                .collect(Collectors.collectingAndThen(Collectors.toList(), list -> list.get(0)));

        List<Patient> patients = doctorById.getPatients();

        List<Role> categories = DAOFactory.getMySQLDAOFactory().getRoleDAO().getRoles();

        session.setAttribute("doctor", doctorById);
        session.setAttribute("categories", categories);

        return new Redirect(Path.PAGE_UPDATE_DOCTOR_PAGE, ForwardingType.FORWARD);

    }
}
