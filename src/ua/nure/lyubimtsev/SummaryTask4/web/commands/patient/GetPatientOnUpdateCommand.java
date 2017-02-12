package ua.nure.lyubimtsev.SummaryTask4.web.commands.patient;

import org.apache.log4j.Logger;
import ua.nure.lyubimtsev.SummaryTask4.ForwardingType;
import ua.nure.lyubimtsev.SummaryTask4.Path;
import ua.nure.lyubimtsev.SummaryTask4.Redirect;
import ua.nure.lyubimtsev.SummaryTask4.db.entities.*;
import ua.nure.lyubimtsev.SummaryTask4.exception.AppException;
import ua.nure.lyubimtsev.SummaryTask4.web.commands.Command;
import ua.nure.lyubimtsev.SummaryTask4.web.commands.appointment.GetAppointmentOnUpdateCommand;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Get patient that will be updated
 *
 * @author Vladislav
 *
 */
public class GetPatientOnUpdateCommand extends Command {

    private static final Logger LOG = Logger.getLogger(GetPatientOnUpdateCommand.class);

    private static final String PAGE_TITLE_ATTRIBUTE = "pageTitle";
    private static final String LOCALE_KEY = "patients";

    @Override
    public Redirect execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {

        LOG.debug("Command starts");

        HttpSession session = request.getSession();

        int patientId = Integer.parseInt(request.getParameter("patientId"));
        LOG.trace("patientId --> " + patientId);

        List<Patient> patients = (List<Patient>) session.getAttribute("patients");
        Patient patientById = patients
                .stream()
                .filter(patient -> patient.getId() == patientId)
                .collect(Collectors.collectingAndThen(Collectors.toList(), list -> list.get(0)));

        LOG.trace("Set the request attribute: patientById --> " + patientById);
        request.setAttribute("patientById", patientById);

        LOG.trace("Set the request attribute: pageTitle --> " + LOCALE_KEY);
        request.setAttribute(PAGE_TITLE_ATTRIBUTE, LOCALE_KEY);

        LOG.debug("Commands finished");
        return new Redirect(Path.PATIENTS_PAGE, ForwardingType.FORWARD);
    }
}