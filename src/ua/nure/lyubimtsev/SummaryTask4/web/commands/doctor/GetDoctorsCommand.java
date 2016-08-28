package ua.nure.lyubimtsev.SummaryTask4.web.commands.doctor;

import ua.nure.lyubimtsev.SummaryTask4.ForwardingType;
import ua.nure.lyubimtsev.SummaryTask4.Path;
import ua.nure.lyubimtsev.SummaryTask4.Redirect;
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

public class GetDoctorsCommand extends Command {

    @Override
    public Redirect execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException, AppException {

        HttpSession session = request.getSession();

        Admin admin = (Admin) session.getAttribute("user");

        List<Doctor> allDoctors = factory.getDoctorDAO().getAllDoctors();
        admin.setDoctors(allDoctors);

        List<Category> categories = factory.getCategoryDAO().getAllCategories();
        session.setAttribute("categories", categories);
        for (Category category : categories) {
            session.setAttribute(category.getName(), admin.getDoctorsByCategory(category));
        }

        Category all = new Category(0, "all");
        if (!categories.contains(all)) {
            categories.add(0, all);
        }

        session.setAttribute("all", admin.getDoctors());
        return new Redirect(Path.DOCTORS_PAGE, ForwardingType.FORWARD);
    }
}
