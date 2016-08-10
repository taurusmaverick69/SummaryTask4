package ua.nure.lyubimtsev.SummaryTask4.web.command.doctor;

import ua.nure.lyubimtsev.SummaryTask4.ForwardingType;
import ua.nure.lyubimtsev.SummaryTask4.Hash;
import ua.nure.lyubimtsev.SummaryTask4.Path;
import ua.nure.lyubimtsev.SummaryTask4.Redirect;
import ua.nure.lyubimtsev.SummaryTask4.db.dao.DAOFactory;
import ua.nure.lyubimtsev.SummaryTask4.db.dao.entitydao.DoctorDAO;
import ua.nure.lyubimtsev.SummaryTask4.db.entities.Admin;
import ua.nure.lyubimtsev.SummaryTask4.db.entities.Category;
import ua.nure.lyubimtsev.SummaryTask4.db.entities.Doctor;
import ua.nure.lyubimtsev.SummaryTask4.exception.AppException;
import ua.nure.lyubimtsev.SummaryTask4.web.command.Command;

import javax.print.Doc;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
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

        Admin admin = (Admin) session.getAttribute("admin");

        Doctor doctor = new Doctor(
                request.getParameter("login"),
                Hash.md5Custom(request.getParameter("password")),
                request.getParameter("name"),
                myCategory,
                admin.getId()
        );

        DoctorDAO doctorDAO = DAOFactory.getMySQLDAOFactory().getDoctorDAO();

        if (doctorDAO.isLoginExists(doctor.getLogin())) {
            return new Redirect(Path.PAGE_INSERT_DOCTOR_PAGE + "?result=" + "Пользователь с таким именем уже существует", ForwardingType.FORWARD);
        } else {
            boolean success = doctorDAO.insertDoctor(doctor) > 0;
            if (success) {
                admin.getDoctors().add(doctor);
            }
            return new Redirect("controller?command=displayInsertDoctor&success=" + success, ForwardingType.SEND_REDIRECT);
        }

    }
}
