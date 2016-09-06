package ua.nure.lyubimtsev.SummaryTask4.web.commands.appointment;

import org.apache.log4j.Logger;
import ua.nure.lyubimtsev.SummaryTask4.ForwardingType;
import ua.nure.lyubimtsev.SummaryTask4.Path;
import ua.nure.lyubimtsev.SummaryTask4.Redirect;
import ua.nure.lyubimtsev.SummaryTask4.db.entities.*;
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
 * List appointments.
 *
 * @author Vladislav
 *
 */
public class GetAppointmentsCommand extends Command {

    private static final Logger LOG = Logger.getLogger(GetAppointmentsCommand.class);

    private static final String PAGE_TITLE_ATTRIBUTE = "pageTitle";
    private static final String LOCALE_KEY = "medicalCard";

    @Override
    public Redirect execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {

        LOG.debug("Command starts");

        HttpSession session = request.getSession();

        MedicalCard medicalCard = (MedicalCard) session.getAttribute("medicalCard");
        LOG.trace("medicalCard --> " + medicalCard);

        List<Appointment> appointments =
                factory.getAppointmentDAO().getAppointmentsByMedicalCardId(medicalCard.getId());
        LOG.trace("appointments --> " + appointments);

        medicalCard.setAppointments(appointments);

        List<Type> types = factory.getTypeDAO().getAllTypes();
        Doctor doctor = (Doctor) session.getAttribute("user");
        if (doctor.getCategory().getName().equals("nurse")) {
            types.remove(new Type("operation"));
        }

        session.setAttribute("types", types);
        LOG.trace("Set the session attribute: types --> " + types);

        request.setAttribute(PAGE_TITLE_ATTRIBUTE, LOCALE_KEY);
        LOG.trace("Set the request attribute: pageTitle --> " + LOCALE_KEY);

        List<Patient> patients = (List<Patient>) session.getAttribute("patients");
        Patient patientById = patients
                .stream()
                .filter(patient -> patient.getId() == medicalCard.getPatientId())
                .collect(Collectors.collectingAndThen(Collectors.toList(), list -> list.get(0)));

        String name = patientById.getName();
        session.setAttribute("patientName", name);
        LOG.trace("Set the session attribute: pageTitle --> " + name);

        request.setAttribute(PAGE_TITLE_ATTRIBUTE, LOCALE_KEY);
        LOG.trace("Set the request attribute: pageTitle --> " + LOCALE_KEY);

        LOG.debug("Commands finished");
        return new Redirect(Path.MEDICAL_CARD_PAGE, ForwardingType.FORWARD);
    }
}
