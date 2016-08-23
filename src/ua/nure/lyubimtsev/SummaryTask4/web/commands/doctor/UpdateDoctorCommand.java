package ua.nure.lyubimtsev.SummaryTask4.web.commands.doctor;

import ua.nure.lyubimtsev.SummaryTask4.ForwardingType;
import ua.nure.lyubimtsev.SummaryTask4.Path;
import ua.nure.lyubimtsev.SummaryTask4.Redirect;
import ua.nure.lyubimtsev.SummaryTask4.Role;
import ua.nure.lyubimtsev.SummaryTask4.db.dao.DAOFactory;
import ua.nure.lyubimtsev.SummaryTask4.db.dao.entitydao.DoctorDAO;
import ua.nure.lyubimtsev.SummaryTask4.db.entities.Admin;
import ua.nure.lyubimtsev.SummaryTask4.db.entities.Category;
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

@WebServlet(name = "EditDoctorCommand", urlPatterns = "/editDoctorServlet")
public class UpdateDoctorCommand extends Command {

    @Override
    public Redirect execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {

        HttpSession session = request.getSession();

        int categoryId = Integer.parseInt(request.getParameter("category"));
        List<Category> categories = ((List<Category>) session.getAttribute("categories"));
        Category categoryById = categories
                .stream()
                .filter(category -> category.getId() == categoryId)
                .collect(Collectors.collectingAndThen(Collectors.toList(), list -> list.get(0)));

        int doctorId = Integer.parseInt(request.getParameter("id"));
        List<Doctor> doctors = ((Admin) session.getAttribute("user")).getDoctors();
        Doctor doctorById = doctors
                .stream()
                .filter(doctor -> doctor.getId() == doctorId)
                .collect(Collectors.collectingAndThen(Collectors.toList(), list -> list.get(0)));

        String name = request.getParameter("name");
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        Role role = (Role) session.getAttribute("role");
        DoctorDAO doctorDAO = DAOFactory.getMySQLDAOFactory().getDoctorDAO();

        boolean success;
        switch (role) {
            case ADMIN:
                doctorById.setName(name);
                doctorById.setCategory(categoryById);
                break;

            case DOCTOR:
                doctorById.setLogin(login);
                doctorById.setPassword(password);
                doctorById.setName(name);
                doctorById.setCategory(categoryById);
                break;
        }

        if (success = doctorDAO.updateDoctor(doctorById, role) > 0) {
            doctors.set(doctors.indexOf(doctorById), doctorById);
        }

        return new Redirect(Path.PRG_COMMAND + "&entity=Doctor&action=update&success=" + success, ForwardingType.SEND_REDIRECT);
    }
}
