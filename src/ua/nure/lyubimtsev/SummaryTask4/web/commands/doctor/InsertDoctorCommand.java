package ua.nure.lyubimtsev.SummaryTask4.web.commands.doctor;

import org.apache.commons.codec.digest.DigestUtils;
import ua.nure.lyubimtsev.SummaryTask4.ForwardingType;
import ua.nure.lyubimtsev.SummaryTask4.Path;
import ua.nure.lyubimtsev.SummaryTask4.Redirect;
import ua.nure.lyubimtsev.SummaryTask4.db.dao.DAOFactory;
import ua.nure.lyubimtsev.SummaryTask4.db.dao.entitydao.DoctorDAO;
import ua.nure.lyubimtsev.SummaryTask4.db.entities.Admin;
import ua.nure.lyubimtsev.SummaryTask4.db.entities.Category;
import ua.nure.lyubimtsev.SummaryTask4.db.entities.Doctor;
import ua.nure.lyubimtsev.SummaryTask4.exception.AppException;
import ua.nure.lyubimtsev.SummaryTask4.web.commands.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class InsertDoctorCommand extends Command {

    @Override
    public Redirect execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {

        HttpSession session = request.getSession();

        int categoryId = Integer.parseInt(request.getParameter("category"));
        List<Category> categories = (List<Category>) session.getAttribute("categories");

        Category categoryById = categories
                .stream()
                .filter(category -> category.getId() == categoryId)
                .collect(Collectors.collectingAndThen(Collectors.toList(), list -> list.get(0)));

        Admin admin = (Admin) session.getAttribute("user");

        Doctor doctor = new Doctor(
                request.getParameter("login"),
                DigestUtils.md5Hex(request.getParameter("password")),
                request.getParameter("name"),
                categoryById,
                admin.getId()
        );

        DoctorDAO doctorDAO = DAOFactory.getMySQLDAOFactory().getDoctorDAO();

        if (doctorDAO.isLoginExists(doctor.getLogin())) {
            return new Redirect(Path.INSERT_DOCTOR_PAGE + "?result=" + "Пользователь с таким именем уже существует", ForwardingType.FORWARD);
        } else {
            boolean success;
            if (success = doctorDAO.insertDoctor(doctor) > 0) {
                admin.getDoctors().add(doctor);
            }
            return new Redirect(Path.PRG_COMMAND + "&entity=Doctor&action=insert&success=" + success, ForwardingType.SEND_REDIRECT);
        }
    }

}
