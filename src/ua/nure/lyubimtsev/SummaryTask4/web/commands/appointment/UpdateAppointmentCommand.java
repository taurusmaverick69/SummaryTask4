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

    @Override
    public Redirect execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {

        LOG.debug("Command starts");

        HttpSession session = request.getSession();

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

        Appointment appointmentById = (Appointment) session.getAttribute("appointmentById");
        LOG.trace("appointmentById --> " + appointmentById);

        Appointment tempAppointment = new Appointment();

        tempAppointment.setId(appointmentById.getId());
        tempAppointment.setDiagnose(diagnose);
        tempAppointment.setType(typeById);
        tempAppointment.setInfo(info);

        boolean success;
        if (success = factory.getAppointmentDAO().updateAppointment(tempAppointment) > 0){
            appointmentById.setDiagnose(diagnose);
            appointmentById.setType(typeById);
            appointmentById.setInfo(info);
        }

        LOG.debug("Commands finished");
        return new Redirect(Path.PRG_COMMAND + "&entity=Appointment&action=update&success=" + success, ForwardingType.SEND_REDIRECT);
    }
}
