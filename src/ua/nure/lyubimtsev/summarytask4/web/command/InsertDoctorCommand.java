package ua.nure.lyubimtsev.SummaryTask4.web.command;

import ua.nure.lyubimtsev.SummaryTask4.ForwardingType;
import ua.nure.lyubimtsev.SummaryTask4.Redirect;
import ua.nure.lyubimtsev.SummaryTask4.db.dao.DAOFactory;
import ua.nure.lyubimtsev.SummaryTask4.db.entities.Role;
import ua.nure.lyubimtsev.SummaryTask4.db.entities.User;
import ua.nure.lyubimtsev.SummaryTask4.exception.AppException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
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
        List<Role> categories = (List<Role>) session.getAttribute("roles");

        String roleStr = request.getParameter("role");

        Role myRole = categories
                .stream()
                .filter(category -> category.getName().equals(roleStr))
                .collect(Collectors.collectingAndThen(Collectors.toList(), list -> list.get(0)));

        User admin = (User) session.getAttribute("user");


        User doctor = new User(
                request.getParameter("login"),
                request.getParameter("password"),
                request.getParameter("name"),
                myRole
        );

        boolean success = DAOFactory.getMySQLDAOFactory().getUserDAO().insertUser(doctor) > 0;

        if (success) {
            admin.getDoctors().add(doctor);
        }

        return new Redirect("displayInsertDoctorServlet?success=" + success, ForwardingType.SEND_REDIRECT);

    }
}
