package ua.nure.lyubimtsev.SummaryTask4.web.commands.appointment;

import ua.nure.lyubimtsev.SummaryTask4.ForwardingType;
import ua.nure.lyubimtsev.SummaryTask4.Path;
import ua.nure.lyubimtsev.SummaryTask4.Redirect;
import ua.nure.lyubimtsev.SummaryTask4.db.entities.Appointment;
import ua.nure.lyubimtsev.SummaryTask4.db.entities.MedicalCard;
import ua.nure.lyubimtsev.SummaryTask4.exception.AppException;
import ua.nure.lyubimtsev.SummaryTask4.web.commands.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


public class GetAppointmentOnUpdateCommand extends Command {
    @Override
    public Redirect execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {

        HttpSession session = request.getSession();
        int id = Integer.parseInt(request.getParameter("id"));

        MedicalCard medicalCard = (MedicalCard) session.getAttribute("medicalCard");
        Appointment appointmentById = medicalCard.getAppointmentById(id);

        session.setAttribute("appointmentById", appointmentById);

        return new Redirect(Path.UPDATE_APPOINTMENT_PAGE, ForwardingType.FORWARD);
    }
}
