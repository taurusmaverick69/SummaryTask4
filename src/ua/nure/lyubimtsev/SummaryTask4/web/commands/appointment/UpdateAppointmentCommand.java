package ua.nure.lyubimtsev.SummaryTask4.web.commands.appointment;

import ua.nure.lyubimtsev.SummaryTask4.ForwardingType;
import ua.nure.lyubimtsev.SummaryTask4.Path;
import ua.nure.lyubimtsev.SummaryTask4.Redirect;
import ua.nure.lyubimtsev.SummaryTask4.db.dao.DAOFactory;
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

public class UpdateAppointmentCommand extends Command {
    @Override
    public Redirect execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {


        HttpSession session = request.getSession();

        String diagnose = request.getParameter("diagnose");

        int typeId = Integer.parseInt(request.getParameter("type"));
        List<Type> types = ((List<Type>) session.getAttribute("types"));
        Type typeById = types
                .stream()
                .filter(type -> type.getId() == typeId)
                .collect(Collectors.collectingAndThen(Collectors.toList(), list -> list.get(0)));

        String info = request.getParameter("info");

        Appointment appointmentById = (Appointment) session.getAttribute("appointmentById");
        Appointment tempAppointment = new Appointment();

        tempAppointment.setId(appointmentById.getId());
        tempAppointment.setDiagnose(diagnose);
        tempAppointment.setType(typeById);
        tempAppointment.setInfo(info);

        boolean success;
        if (success = DAOFactory.getMySQLDAOFactory().getAppointmentDAO().updateAppointment(tempAppointment) > 0){
            appointmentById.setDiagnose(diagnose);
            appointmentById.setType(typeById);
            appointmentById.setInfo(info);
        }

        return new Redirect(Path.PRG_COMMAND + "&entity=Appointment&action=update&success=" + success, ForwardingType.SEND_REDIRECT);
    }
}
