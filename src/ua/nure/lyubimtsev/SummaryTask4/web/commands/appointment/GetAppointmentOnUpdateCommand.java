package ua.nure.lyubimtsev.SummaryTask4.web.commands.appointment;

import org.apache.log4j.Logger;
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

/**
 * Get appointment that will be updated
 *
 * @author Vladislav
 *
 */
public class GetAppointmentOnUpdateCommand extends Command {

    private static final Logger LOG = Logger.getLogger(GetAppointmentOnUpdateCommand.class);

    private static final String PAGE_TITLE_ATTRIBUTE = "pageTitle";
    private static final String LOCALE_KEY = "medicalCard";

    @Override
    public Redirect execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {

        LOG.debug("Command starts");

        HttpSession session = request.getSession();
        int id = Integer.parseInt(request.getParameter("id"));

        MedicalCard medicalCard = (MedicalCard) session.getAttribute("medicalCard");
        Appointment appointmentById = medicalCard.getAppointmentById(id);

        request.setAttribute("appointmentById", appointmentById);

        request.setAttribute(PAGE_TITLE_ATTRIBUTE, LOCALE_KEY);
        return new Redirect(Path.MEDICAL_CARD_PAGE, ForwardingType.FORWARD);
    }
}
