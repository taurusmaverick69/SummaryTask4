package ua.nure.lyubimtsev.SummaryTask4.web.commands.doctor;

import org.apache.log4j.Logger;
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


/**
 * List doctors.
 *
 * @author Vladislav
 */
public class GetDoctorsCommand extends Command {

    private static final Logger LOG = Logger.getLogger(GetDoctorsCommand.class);

    private static final String PAGE_TITLE_ATTRIBUTE = "pageTitle";
    private static final String LOCALE_KEY = "doctors";

    @Override
    public Redirect execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException, AppException {

        LOG.debug("Command starts");

        HttpSession session = request.getSession();

        Admin admin = (Admin) session.getAttribute("user");
        LOG.trace("admin --> " + admin);

        List<Doctor> allDoctors = factory.getDoctorDAO().getAllDoctors();
        LOG.trace("allDoctors --> " + allDoctors);
        admin.setDoctors(allDoctors);

        List<Category> categories = factory.getCategoryDAO().getAllCategories();

        session.setAttribute("categories", categories);
        LOG.trace("Set the session attribute: categories  -->  " + categories);

        for (Category category : categories) {

            String name = category.getName();
            List<Doctor> doctorsByCategory = admin.getDoctorsByCategory(category);

            session.setAttribute(name, doctorsByCategory);
            LOG.trace("Set the session attribute: " + name + " --> " + doctorsByCategory);
        }

        Category all = new Category(0, "all");
        if (!categories.contains(all)) {
            categories.add(0, all);
        }

        List<Doctor> doctors = admin.getDoctors();
        session.setAttribute("all", doctors);
        LOG.trace("Set the session attribute: all --> " + doctors);

        request.setAttribute(PAGE_TITLE_ATTRIBUTE, LOCALE_KEY);
        LOG.trace("Set the request attribute: pageTitle --> " + LOCALE_KEY);


        LOG.debug("Commands finished");
        return new Redirect(Path.DOCTORS_PAGE, ForwardingType.FORWARD);
    }
}
