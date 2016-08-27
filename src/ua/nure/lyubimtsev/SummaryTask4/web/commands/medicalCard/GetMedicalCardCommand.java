package ua.nure.lyubimtsev.SummaryTask4.web.commands.medicalCard;

import ua.nure.lyubimtsev.SummaryTask4.ForwardingType;
import ua.nure.lyubimtsev.SummaryTask4.Path;
import ua.nure.lyubimtsev.SummaryTask4.Redirect;
import ua.nure.lyubimtsev.SummaryTask4.db.dao.DAOFactory;
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

public class GetMedicalCardCommand extends Command {
    @Override
    public Redirect execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {

        HttpSession session = request.getSession();

        int patientId = Integer.parseInt(request.getParameter("patientId"));


        MedicalCardDAO medicalCardDAO = factory.getMedicalCardDAO();
        MedicalCard medicalCard = medicalCardDAO.getMedicalCardByPatientId(patientId);

        if (medicalCard == null) {
            medicalCard = new MedicalCard(new Date(), patientId);
            medicalCardDAO.insertMedicalCard(medicalCard);
        }

        session.setAttribute("medicalCard", medicalCard);

        return new Redirect(Path.GET_APPOINTMENTS_COMMAND, ForwardingType.FORWARD);
    }
}
