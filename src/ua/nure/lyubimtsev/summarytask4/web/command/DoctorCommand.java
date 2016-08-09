package ua.nure.lyubimtsev.SummaryTask4.web.command;


import ua.nure.lyubimtsev.SummaryTask4.ForwardingType;
import ua.nure.lyubimtsev.SummaryTask4.Path;
import ua.nure.lyubimtsev.SummaryTask4.Redirect;
import ua.nure.lyubimtsev.SummaryTask4.db.dao.DAOFactory;
import ua.nure.lyubimtsev.SummaryTask4.db.entities.Role;
import ua.nure.lyubimtsev.SummaryTask4.db.entities.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(name = "DoctorCommand", urlPatterns = "/doctors")
public class DoctorCommand extends Command {


    @Override
    public Redirect execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        String role = request.getParameter("role");


        List<User> doctors = ((User) session.getAttribute("user")).getDoctors();

        if (role.equals("all")){
            session.setAttribute("doctorsByCategory", doctors);
        } else {
            List<User> doctorsByCategory = doctors
                    .stream()
                    .filter(doctor -> doctor.getRole().getName().equals(role))
                    .collect(Collectors.toList());
            session.setAttribute("doctorsByCategory", doctorsByCategory);
        }

        request.setAttribute("role", role);

        List<Role> categories = DAOFactory.getMySQLDAOFactory().getRoleDAO().getRoles();
        session.setAttribute("categories", categories);

        return new Redirect(Path.PAGE_DOCTORS_PAGE, ForwardingType.FORWARD);
    }
}
