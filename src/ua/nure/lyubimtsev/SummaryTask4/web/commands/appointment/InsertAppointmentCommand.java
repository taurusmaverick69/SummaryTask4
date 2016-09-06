package ua.nure.lyubimtsev.SummaryTask4.web.commands.appointment;

import org.apache.log4j.Logger;
import ua.nure.lyubimtsev.SummaryTask4.ForwardingType;
import ua.nure.lyubimtsev.SummaryTask4.Path;
import ua.nure.lyubimtsev.SummaryTask4.Redirect;
import ua.nure.lyubimtsev.SummaryTask4.db.entities.Appointment;
import ua.nure.lyubimtsev.SummaryTask4.db.entities.Doctor;
import ua.nure.lyubimtsev.SummaryTask4.db.entities.MedicalCard;
import ua.nure.lyubimtsev.SummaryTask4.db.entities.Type;
import ua.nure.lyubimtsev.SummaryTask4.exception.AppException;
import ua.nure.lyubimtsev.SummaryTask4.web.commands.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


/**
 * Insert appointment.
 *
 * @author Vladislav
 */
public class InsertAppointmentCommand extends Command {

    private static final Logger LOG = Logger.getLogger(InsertAppointmentCommand.class);

    private static final String APPOINTMENT_INSERT_SUCCESS = "appointment.add.success";
    private static final String APPOINTMENT_INSERT_FAILED = "appointment.add.failed";

    @Override
    public Redirect execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {

        LOG.debug("Command starts");

        HttpSession session = request.getSession();

        String diagnose = request.getParameter("diagnose");
        LOG.trace("diagnose --> " + diagnose);

        int typeId = Integer.parseInt(request.getParameter("type"));
        List<Type> types = (List<Type>) session.getAttribute("types");
        Type typeById = types
                .stream()
                .filter(type -> type.getId() == typeId)
                .collect(Collectors.collectingAndThen(Collectors.toList(), list -> list.get(0)));
        LOG.trace("typeById --> " + typeById);

        String info = request.getParameter("info");
        LOG.trace("info --> " + info);

        Doctor doctor = (Doctor) session.getAttribute("user");
        LOG.trace("doctor --> " + doctor);

        MedicalCard medicalCard = (MedicalCard) session.getAttribute("medicalCard");
        LOG.trace("medicalCard --> " + medicalCard);

        Appointment appointment = new Appointment(diagnose, typeById, info, new Date(), doctor, medicalCard.getId());

        boolean success = factory.getAppointmentDAO().insertAppointment(appointment) > 0;
        if (success) {
            medicalCard.getAppointments().add(appointment);
        }

        session.setAttribute("result", success ? APPOINTMENT_INSERT_SUCCESS : APPOINTMENT_INSERT_FAILED);

        LOG.debug("Commands finished");
        return new Redirect(Path.GET_APPOINTMENTS_COMMAND, ForwardingType.SEND_REDIRECT);

    }
}
