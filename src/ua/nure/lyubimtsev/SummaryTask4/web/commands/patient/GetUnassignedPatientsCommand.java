package ua.nure.lyubimtsev.SummaryTask4.web.commands.patient;

import org.apache.log4j.Logger;
import ua.nure.lyubimtsev.SummaryTask4.ForwardingType;
import ua.nure.lyubimtsev.SummaryTask4.Path;
import ua.nure.lyubimtsev.SummaryTask4.Redirect;
import ua.nure.lyubimtsev.SummaryTask4.db.entities.Patient;
import ua.nure.lyubimtsev.SummaryTask4.exception.AppException;
import ua.nure.lyubimtsev.SummaryTask4.web.commands.Command;
import ua.nure.lyubimtsev.SummaryTask4.web.commands.appointment.GetAppointmentOnUpdateCommand;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Get unassigned patients.
 *
 * @author Vladislav
 *
 */
public class GetUnassignedPatientsCommand extends Command {

    private static final Logger LOG = Logger.getLogger(GetUnassignedPatientsCommand.class);

    private static final String PAGE_TITLE_ATTRIBUTE = "pageTitle";
    private static final String LOCALE_KEY = "unassignedPatients";

    @Override
    public Redirect execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {

        LOG.debug("Command starts");

        HttpSession session = request.getSession();

        int doctorId = Integer.parseInt(request.getParameter("doctorId"));

        List<Patient> unassignedPatients = factory.getPatientDAO().getUnassignedPatients(doctorId);

        request.setAttribute("doctorId", doctorId);
        LOG.trace("Set the request attribute: doctorId --> " + doctorId);

        session.setAttribute("unassignedPatients", unassignedPatients);
        LOG.trace("Set the session attribute: unassignedPatients --> " + unassignedPatients);

        request.setAttribute(PAGE_TITLE_ATTRIBUTE, LOCALE_KEY);
        LOG.trace("Set the request attribute: pageTitle --> " + LOCALE_KEY);

        LOG.debug("Commands finished");
        return new Redirect(Path.UNASSIGNED_PATIENTS_PAGE, ForwardingType.FORWARD);
    }
}
