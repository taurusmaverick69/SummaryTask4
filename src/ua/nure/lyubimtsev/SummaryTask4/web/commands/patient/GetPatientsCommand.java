package ua.nure.lyubimtsev.SummaryTask4.web.commands.patient;

import org.apache.log4j.Logger;
import ua.nure.lyubimtsev.SummaryTask4.ForwardingType;
import ua.nure.lyubimtsev.SummaryTask4.Path;
import ua.nure.lyubimtsev.SummaryTask4.Redirect;
import ua.nure.lyubimtsev.SummaryTask4.Role;
import ua.nure.lyubimtsev.SummaryTask4.db.entities.Admin;
import ua.nure.lyubimtsev.SummaryTask4.db.entities.Doctor;
import ua.nure.lyubimtsev.SummaryTask4.db.entities.Patient;
import ua.nure.lyubimtsev.SummaryTask4.db.entities.State;
import ua.nure.lyubimtsev.SummaryTask4.exception.AppException;
import ua.nure.lyubimtsev.SummaryTask4.web.commands.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * Get patients.
 *
 * @author Vladislav
 *
 */
public class GetPatientsCommand extends Command {

    private static final Logger LOG = Logger.getLogger(GetPatientsCommand.class);

    private static final String PAGE_TITLE_ATTRIBUTE = "pageTitle";
    private static final String LOCALE_KEY = "patients";

    @Override
    public Redirect execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {

        LOG.debug("Command starts");

        HttpSession session = request.getSession();

        Role role = (Role) session.getAttribute("role");
        LOG.trace("role --> " + role);

        Object user = session.getAttribute("user");


        List<State> allStates = factory.getStateDAO().getAllStates();
        session.setAttribute("states", allStates);
        LOG.trace("Set the session attribute: states --> " + allStates);

        List<Patient> patients = new ArrayList<>();

        int doctorId = 0;
        Doctor doctor = null;
        switch (role) {
            case ADMIN:
                doctorId = Integer.parseInt(request.getParameter("doctorId"));
                Admin admin = (Admin) user;
                doctor = admin.getDoctorById(doctorId);
                patients = doctor.getPatients();
                break;
            case DOCTOR:
                doctor = (Doctor) user;
                patients = doctor.getPatients();
                doctorId = doctor.getId();
                break;
        }

        session.setAttribute("doctorName", doctor.getName());
        LOG.trace("Set the session attribute: doctorId --> " + doctorId);

        session.setAttribute("doctorId", doctorId);
        LOG.trace("Set the session attribute: doctorId --> " + doctorId);

        session.setAttribute("patients", patients);
        LOG.trace("Set the session attribute: patients --> " + patients);

        request.setAttribute(PAGE_TITLE_ATTRIBUTE, LOCALE_KEY);
        LOG.trace("Set the request attribute: pageTitle --> " + LOCALE_KEY);

        LOG.debug("Commands finished");
        return new Redirect(Path.PATIENTS_PAGE, ForwardingType.FORWARD);

    }
}