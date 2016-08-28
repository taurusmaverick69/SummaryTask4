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
import ua.nure.lyubimtsev.SummaryTask4.web.commands.appointment.GetAppointmentOnUpdateCommand;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Insert patient.
 *
 * @author Vladislav
 *
 */
public class InsertPatientCommand extends Command {

    private static final String INSERT = "Insert";
    private static final String PATIENT = "Patient";

    private static final Logger LOG = Logger.getLogger(InsertPatientCommand.class);

    @Override
    public Redirect execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {

        LOG.debug("Command starts");

        HttpSession session = request.getSession();

        String name = request.getParameter("name");
        LOG.trace("name --> " + name);

        String address = request.getParameter("address");
        LOG.trace("address --> " + address);

        Date birthDate = null;
        try {
            birthDate = new SimpleDateFormat("dd.MM.yyyy").parse(request.getParameter("birthDate"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        LOG.trace("birthDate --> " + birthDate);

        List<State> states = (List<State>) session.getAttribute("states");
        int stateId = Integer.parseInt(request.getParameter("state"));
        State stateById = states
                .stream()
                .filter(state -> state.getId() == stateId)
                .collect(Collectors.collectingAndThen(Collectors.toList(), list -> list.get(0)));
        LOG.trace("stateById --> " + stateById);

        int doctorId = (int) session.getAttribute("doctorId");
        LOG.trace("doctorId --> " + doctorId);

        Patient patient = new Patient(name, address, birthDate, stateById, doctorId);

        boolean success;
        Object user = session.getAttribute("user");
        if (success = factory.getPatientDAO().insertPatient(patient) > 0) {
            Role role = (Role) session.getAttribute("role");
            LOG.trace("role --> " + role);
            switch (role) {
                case ADMIN:
                    Admin admin = (Admin) user;
                    admin.getDoctorById(doctorId).getPatients().add(patient);
                    break;
                case DOCTOR:
                    Doctor doctor = (Doctor) user;
                    doctor.getPatients().add(patient);
                    break;
            }
        }

        LOG.debug("Commands finished");
        return new Redirect(Path.PRG_COMMAND + "&entity=Patient&action=insert&doctorId=" + doctorId + "&success=" + success, ForwardingType.SEND_REDIRECT);
    }
}
