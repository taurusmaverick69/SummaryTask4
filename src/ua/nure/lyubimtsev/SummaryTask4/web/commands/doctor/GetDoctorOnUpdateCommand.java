package ua.nure.lyubimtsev.SummaryTask4.web.commands.doctor;

import org.apache.log4j.Logger;
import ua.nure.lyubimtsev.SummaryTask4.ForwardingType;
import ua.nure.lyubimtsev.SummaryTask4.Path;
import ua.nure.lyubimtsev.SummaryTask4.Redirect;
import ua.nure.lyubimtsev.SummaryTask4.db.entities.Admin;
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

        Admin admin = (Admin) session.getAttribute("user");

        request.setAttribute("doctorById", admin.getDoctorById(id));
        request.setAttribute(PAGE_TITLE_ATTRIBUTE, LOCALE_KEY);
        return new Redirect(Path.DOCTORS_PAGE, ForwardingType.FORWARD);
    }
}
