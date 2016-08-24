package ua.nure.lyubimtsev.SummaryTask4.web.commands.doctor;

import ua.nure.lyubimtsev.SummaryTask4.ForwardingType;
import ua.nure.lyubimtsev.SummaryTask4.Path;
import ua.nure.lyubimtsev.SummaryTask4.Redirect;
import ua.nure.lyubimtsev.SummaryTask4.db.entities.Admin;
import ua.nure.lyubimtsev.SummaryTask4.db.entities.Category;
import ua.nure.lyubimtsev.SummaryTask4.web.commands.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class GetDoctorsCommand extends Command {

    @Override
    public Redirect execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        HttpSession session = request.getSession();

        Admin admin = (Admin) session.getAttribute("user");
        List<Category> categories = (List<Category>) session.getAttribute("categories");

        session.setAttribute("allDoctors", admin.getDoctors());
        for (Category category : categories) {
            session.setAttribute(category.getName() + "s", admin.getDoctorsByCategory(category));
        }

        return new Redirect(Path.DOCTORS_PAGE, ForwardingType.FORWARD);
    }
}
