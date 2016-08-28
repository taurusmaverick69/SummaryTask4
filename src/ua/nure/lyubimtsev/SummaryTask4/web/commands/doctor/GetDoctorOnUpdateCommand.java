package ua.nure.lyubimtsev.SummaryTask4.web.commands.doctor;

import org.apache.log4j.Logger;
import ua.nure.lyubimtsev.SummaryTask4.ForwardingType;
import ua.nure.lyubimtsev.SummaryTask4.Path;
import ua.nure.lyubimtsev.SummaryTask4.Redirect;
import ua.nure.lyubimtsev.SummaryTask4.db.entities.Admin;
import ua.nure.lyubimtsev.SummaryTask4.db.entities.Doctor;
import ua.nure.lyubimtsev.SummaryTask4.exception.AppException;
import ua.nure.lyubimtsev.SummaryTask4.web.commands.Command;
import ua.nure.lyubimtsev.SummaryTask4.web.commands.appointment.GetAppointmentOnUpdateCommand;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Get doctor that will be updated
 *
 * @author Vladislav
 *
 */
public class GetDoctorOnUpdateCommand extends Command {

    private static final Logger LOG = Logger.getLogger(GetDoctorOnUpdateCommand.class);

    private static final String PAGE_TITLE_ATTRIBUTE = "pageTitle";
    private static final String LOCALE_KEY = "doctors";


    @Override
    public Redirect execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {

        LOG.debug("Command starts");

        HttpSession session = request.getSession();
        int id = Integer.parseInt(request.getParameter("id"));
        LOG.trace("id --> " + id);

        Admin admin = (Admin) session.getAttribute("user");
        LOG.trace("admin --> " + admin);


        Doctor doctorById = admin.getDoctorById(id);
        request.setAttribute("doctorById", doctorById);
        LOG.trace("Set the request attribute: doctorById --> " + doctorById);

        request.setAttribute(PAGE_TITLE_ATTRIBUTE, LOCALE_KEY);
        LOG.trace("Set the request attribute: pageTitle --> " + LOCALE_KEY);

        LOG.debug("Commands finished");
        return new Redirect(Path.DOCTORS_PAGE, ForwardingType.FORWARD);
    }
}
