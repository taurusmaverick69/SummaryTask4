package ua.nure.lyubimtsev.SummaryTask4.web.commands.appointment;

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
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


/**
 * Insert appointment.
 *
 * @author Vladislav
 *
 */
public class InsertAppointmentCommand extends Command {
    @Override
    public Redirect execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {

        HttpSession session = request.getSession();

        String diagnose = request.getParameter("diagnose");

        int typeId = Integer.parseInt(request.getParameter("type"));
        List<Type> types = (List<Type>) session.getAttribute("types");
        Type typeById = types
                .stream()
                .filter(type -> type.getId() == typeId)
                .collect(Collectors.collectingAndThen(Collectors.toList(), list -> list.get(0)));

        String info = request.getParameter("info");


        Doctor doctor = (Doctor) session.getAttribute("doctor");
        MedicalCard medicalCard = (MedicalCard) session.getAttribute("medicalCard");


        Appointment appointment = new Appointment(diagnose, typeById, info, new Date(), doctor, medicalCard.getId());

        boolean success;
        if (success = factory.getAppointmentDAO().insertAppointment(appointment) > 0) {
            medicalCard.getAppointments().add(appointment);
        }

        return new Redirect(Path.PRG_COMMAND + "&entity=Appointment&action=insert&success=" + success, ForwardingType.SEND_REDIRECT);

    }
}
