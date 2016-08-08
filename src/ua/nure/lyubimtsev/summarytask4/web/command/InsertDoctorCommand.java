package ua.nure.lyubimtsev.summarytask4.web.command;

import ua.nure.lyubimtsev.summarytask4.ForwardingType;
import ua.nure.lyubimtsev.summarytask4.Redirect;
import ua.nure.lyubimtsev.summarytask4.db.dao.DAOFactory;
import ua.nure.lyubimtsev.summarytask4.db.entities.Admin;
import ua.nure.lyubimtsev.summarytask4.db.entities.Category;
import ua.nure.lyubimtsev.summarytask4.db.entities.Doctor;
import ua.nure.lyubimtsev.summarytask4.exception.AppException;
import ua.nure.lyubimtsev.summarytask4.web.command.Command;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(name = "InsertDoctorServlet", urlPatterns = "/insertDoctorServlet")
public class InsertDoctorCommand extends Command {



    @Override
    public Redirect execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        HttpSession session = request.getSession();
        List<Category> categories = (List<Category>) session.getAttribute("categories");

        String categoryStr = request.getParameter("category");

        Category myCategory = categories
                .stream()
                .filter(category -> category.getName().equals(categoryStr))
                .collect(Collectors.collectingAndThen(Collectors.toList(), list -> list.get(0)));

        Admin admin = (Admin) session.getAttribute("user");
        // Admin admin = (Admin) session.getAttribute("userId");


        Doctor doctor = new Doctor(
                request.getParameter("login"),
                request.getParameter("password"),
                request.getParameter("name"),
                myCategory,
                admin
        );

        boolean success = DAOFactory.getMySQLDAOFactory().getDoctorDAO().insertDoctor(doctor) > 0;

        if (success) {
            admin.getDoctors().add(doctor);
        }

        return new Redirect("displayInsertDoctorServlet?success=" + success, ForwardingType.SEND_REDIRECT);

    }
}
