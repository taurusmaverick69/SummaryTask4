package ua.nure.lyubimtsev.summarytask4.web.command;


import ua.nure.lyubimtsev.summarytask4.ForwardingType;
import ua.nure.lyubimtsev.summarytask4.Path;
import ua.nure.lyubimtsev.summarytask4.Redirect;
import ua.nure.lyubimtsev.summarytask4.db.dao.DAOFactory;
import ua.nure.lyubimtsev.summarytask4.db.entities.Admin;
import ua.nure.lyubimtsev.summarytask4.db.entities.Category;
import ua.nure.lyubimtsev.summarytask4.db.entities.Doctor;

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
        String category = request.getParameter("category");
        List<Doctor> doctors = ((Admin) session.getAttribute("user")).getDoctors();

        if (category.equals("all")){
            session.setAttribute("doctorsByCategory", doctors);
        } else {
            List<Doctor> doctorsByCategory = doctors
                    .stream()
                    .filter(doctor -> doctor.getCategory().getName().equals(category))
                    .collect(Collectors.toList());
            session.setAttribute("doctorsByCategory", doctorsByCategory);
        }

        request.setAttribute("category", category);

        List<Category> categories = DAOFactory.getMySQLDAOFactory().getCategoryDAO().getCategories();
        session.setAttribute("categories", categories);

        return new Redirect(Path.PAGE_DOCTORS_PAGE, ForwardingType.FORWARD);
    }
}
