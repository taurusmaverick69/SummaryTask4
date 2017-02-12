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
 * Update appointment.
 *
 * @author Vladislav
 *
 */
public class UpdateAppointmentCommand extends Command {

    private static final Logger LOG = Logger.getLogger(UpdateAppointmentCommand.class);

    private static final String APPOINTMENT_UPDATE_SUCCESS = "appointment.edit.success";
    private static final String APPOINTMENT_UPDATE_FAILED = "appointment.edit.failed";

    @Override
    public Redirect execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {

        LOG.debug("Command starts");

        HttpSession session = request.getSession();

        int appointmentId = Integer.parseInt(request.getParameter("appointmentId"));
        LOG.trace("appointmentId --> " + appointmentId);


        String diagnose = request.getParameter("diagnose");
        LOG.trace("diagnose --> " + diagnose);

        int typeId = Integer.parseInt(request.getParameter("type"));
        List<Type> types = ((List<Type>) session.getAttribute("types"));
        Type typeById = types
                .stream()
                .filter(type -> type.getId() == typeId)
                .collect(Collectors.collectingAndThen(Collectors.toList(), list -> list.get(0)));
        LOG.trace("typeById --> " + typeById);


        String info = request.getParameter("info");
        LOG.trace("info --> " + info);

        MedicalCard medicalCard = (MedicalCard) session.getAttribute("medicalCard");

        Appointment appointmentById = medicalCard.getAppointmentById(appointmentId);
        LOG.trace("appointmentById --> " + appointmentById);

        Appointment tempAppointment = new Appointment();

        tempAppointment.setId(appointmentId);
        tempAppointment.setDiagnose(diagnose);
        tempAppointment.setType(typeById);
        tempAppointment.setInfo(info);


        boolean success = factory.getAppointmentDAO().updateAppointment(tempAppointment) > 0;
        if (success){
            appointmentById.setDiagnose(diagnose);
            appointmentById.setType(typeById);
            appointmentById.setInfo(info);
        }

        session.setAttribute("result", success ? APPOINTMENT_UPDATE_SUCCESS : APPOINTMENT_UPDATE_FAILED);

        LOG.debug("Commands finished");
        return new Redirect(Path.GET_APPOINTMENTS_COMMAND, ForwardingType.SEND_REDIRECT);
    }
}