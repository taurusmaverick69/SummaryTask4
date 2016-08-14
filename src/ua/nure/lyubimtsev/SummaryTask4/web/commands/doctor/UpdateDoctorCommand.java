package ua.nure.lyubimtsev.SummaryTask4.web.commands.doctor;

import ua.nure.lyubimtsev.SummaryTask4.ForwardingType;
import ua.nure.lyubimtsev.SummaryTask4.Redirect;
import ua.nure.lyubimtsev.SummaryTask4.db.dao.DAOFactory;
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

        String categoryByParameter = request.getParameter("category");
        List<Category> categories = ((List<Category>) session.getAttribute("categories"));

        Category categoryByName = categories
                .stream()
                .filter(category -> category.getName().equals(categoryByParameter))
                .collect(Collectors.collectingAndThen(Collectors.toList(), list -> list.get(0)));

        int id = Integer.parseInt(request.getParameter("id"));
        Doctor newDoctor = new Doctor(
                id,
                request.getParameter("login"),
                request.getParameter("password"),
                request.getParameter("name"),
                categoryByName
        );

        if (DAOFactory.getMySQLDAOFactory().getDoctorDAO().updateDoctor(newDoctor) > 0) {
            List<Doctor> doctors = ((Admin) session.getAttribute("user")).getDoctors();

            Doctor doctorById = doctors
                    .stream()
                    .filter(doctor -> doctor.getId() == id)
                    .collect(Collectors.collectingAndThen(Collectors.toList(), list -> list.get(0)));

            doctors.set(doctors.indexOf(doctorById), newDoctor);
        }

        return new Redirect("doctors?category=all", ForwardingType.FORWARD);
    }
}
