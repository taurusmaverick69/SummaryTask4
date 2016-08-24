package ua.nure.lyubimtsev.SummaryTask4.web.commands.medicalCard;

import ua.nure.lyubimtsev.SummaryTask4.ForwardingType;
import ua.nure.lyubimtsev.SummaryTask4.Path;
import ua.nure.lyubimtsev.SummaryTask4.Redirect;
import ua.nure.lyubimtsev.SummaryTask4.db.dao.DAOFactory;
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
import java.util.List;

public class GetMedicalCardCommand extends Command {
    @Override
    public Redirect execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {

        HttpSession session = request.getSession();

        int patientId = Integer.parseInt(request.getParameter("id"));

        System.out.println("patientId = " + patientId);

        DAOFactory factory = DAOFactory.getMySQLDAOFactory();
        MedicalCard medicalCard = factory.getMedicalCardDAO().getMedicalCardByPatientId(patientId);
        medicalCard.setAppointments(factory.getAppointmentDAO().getAppointmentsByMedicalCardId(medicalCard.getId()));

        session.setAttribute("medicalCard", medicalCard);

        List<Type> types = factory.getTypeDAO().getAllTypes();

        Doctor doctor = (Doctor) session.getAttribute("user");

        if (doctor.getCategory().getName().equals("nurse")){
            types.remove(new Type("operation"));
        }

        session.setAttribute("types", types);
        return new Redirect(Path.MEDICAL_CARD_PAGE, ForwardingType.FORWARD);
    }
}
