package ua.nure.lyubimtsev.SummaryTask4.web.commands.medicalCard;

import org.apache.log4j.Logger;
import ua.nure.lyubimtsev.SummaryTask4.ForwardingType;
import ua.nure.lyubimtsev.SummaryTask4.Path;
import ua.nure.lyubimtsev.SummaryTask4.Redirect;
import ua.nure.lyubimtsev.SummaryTask4.db.dao.entitydao.MedicalCardDAO;
import ua.nure.lyubimtsev.SummaryTask4.db.entities.MedicalCard;
import ua.nure.lyubimtsev.SummaryTask4.exception.AppException;
import ua.nure.lyubimtsev.SummaryTask4.web.commands.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;

/**
 * Get medical card.
 *
 * @author Vladislav
 *
 */
public class GetMedicalCardCommand extends Command {

    private static final Logger LOG = Logger.getLogger(GetMedicalCardCommand.class);

    @Override
    public Redirect execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {

        LOG.debug("Command starts");

        HttpSession session = request.getSession();

        int patientId = Integer.parseInt(request.getParameter("patientId"));
        LOG.trace("patientId --> " + patientId);


        MedicalCardDAO medicalCardDAO = factory.getMedicalCardDAO();
        MedicalCard medicalCard = medicalCardDAO.getMedicalCardByPatientId(patientId);

        LOG.trace("medicalCard --> " + medicalCard);



        if (medicalCard == null) {
            medicalCard = new MedicalCard(new Date(), patientId);
            medicalCardDAO.insertMedicalCard(medicalCard);
        }


        session.setAttribute("medicalCard", medicalCard);
        LOG.trace("Set the session attribute: medicalCard --> " + medicalCard);

        LOG.debug("Commands finished");
        return new Redirect(Path.GET_APPOINTMENTS_COMMAND, ForwardingType.FORWARD);
    }
}
