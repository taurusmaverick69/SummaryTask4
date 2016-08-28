package ua.nure.lyubimtsev.SummaryTask4.web.commands.patient;

import ua.nure.lyubimtsev.SummaryTask4.ForwardingType;
import ua.nure.lyubimtsev.SummaryTask4.Path;
import ua.nure.lyubimtsev.SummaryTask4.Redirect;
import ua.nure.lyubimtsev.SummaryTask4.db.dao.DAOFactory;
import ua.nure.lyubimtsev.SummaryTask4.db.entities.Patient;
import ua.nure.lyubimtsev.SummaryTask4.exception.AppException;
import ua.nure.lyubimtsev.SummaryTask4.web.commands.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class GetUnassignedPatientsCommand extends Command {

    private static final String PAGE_TITLE_ATTRIBUTE = "pageTitle";
    private static final String LOCALE_KEY = "unassignedPatients";

    @Override
    public Redirect execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {

        int doctorId = Integer.parseInt(request.getParameter("doctorId"));
        List<Patient> unassignedPatients = factory.getPatientDAO().getUnassignedPatients(doctorId);

        request.setAttribute("doctorId", doctorId);
        request.getSession().setAttribute("unassignedPatients", unassignedPatients);

        request.setAttribute(PAGE_TITLE_ATTRIBUTE, LOCALE_KEY);
        return new Redirect(Path.UNASSIGNED_PATIENTS_PAGE, ForwardingType.FORWARD);
    }
}
