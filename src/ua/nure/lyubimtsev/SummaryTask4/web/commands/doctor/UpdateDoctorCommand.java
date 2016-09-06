package ua.nure.lyubimtsev.SummaryTask4.web.commands.doctor;

import org.apache.log4j.Logger;
import ua.nure.lyubimtsev.SummaryTask4.ForwardingType;
import ua.nure.lyubimtsev.SummaryTask4.Path;
import ua.nure.lyubimtsev.SummaryTask4.Redirect;
import ua.nure.lyubimtsev.SummaryTask4.Role;
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


/**
 * Update doctor.
 *
 * @author Vladislav
 */
public class UpdateDoctorCommand extends Command {

    private static final Logger LOG = Logger.getLogger(UpdateDoctorCommand.class);

    private static final String DOCTOR_UPDATE_SUCCESS = "doctor.edit.success";
    private static final String DOCTOR_UPDATE_FAILED = "doctor.edit.failed";

    @Override
    public Redirect execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {

        LOG.debug("Command starts");

        HttpSession session = request.getSession();

        int doctorId = Integer.parseInt(request.getParameter("doctorId"));
        LOG.trace("doctorId --> " + doctorId);

        String name = request.getParameter("name");
        LOG.trace("name --> " + name);

        String login = request.getParameter("login");
        LOG.trace("login --> " + login);

        String password = request.getParameter("password");
        LOG.trace("password --> " + password);

        int categoryId = Integer.parseInt(request.getParameter("category"));
        List<Category> categories = ((List<Category>) session.getAttribute("categories"));
        Category categoryById = categories
                .stream()
                .filter(category -> category.getId() == categoryId)
                .collect(Collectors.collectingAndThen(Collectors.toList(), list -> list.get(0)));
        LOG.trace("categoryById --> " + categoryById);

        Doctor doctorById = ((Admin) session.getAttribute("user")).getDoctorById(doctorId);
        LOG.trace("doctorById --> " + doctorById);

        Doctor tempDoctor = new Doctor(doctorById.getId(), login, password, name, categoryById);

        Role role = (Role) session.getAttribute("role");
        boolean success = factory.getDoctorDAO().updateDoctor(tempDoctor, role) > 0;
        if (success) {
            switch (role) {
                case ADMIN:
                    doctorById.setName(name);
                    doctorById.setCategory(categoryById);
                    break;

                case DOCTOR:
                    doctorById.setLogin(login);
                    doctorById.setPassword(password);
                    doctorById.setName(name);
                    doctorById.setCategory(categoryById);
                    break;
            }
        }

        session.setAttribute("result", success ? DOCTOR_UPDATE_SUCCESS : DOCTOR_UPDATE_FAILED);

        LOG.debug("Commands finished");
        return new Redirect(Path.GET_DOCTORS_COMMAND, ForwardingType.SEND_REDIRECT);
    }
}
