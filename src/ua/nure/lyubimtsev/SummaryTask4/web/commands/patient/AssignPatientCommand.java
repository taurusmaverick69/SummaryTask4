package ua.nure.lyubimtsev.SummaryTask4.web.commands.patient;

import org.apache.log4j.Logger;
import ua.nure.lyubimtsev.SummaryTask4.ForwardingType;
import ua.nure.lyubimtsev.SummaryTask4.Path;
import ua.nure.lyubimtsev.SummaryTask4.Redirect;
import ua.nure.lyubimtsev.SummaryTask4.Role;
import ua.nure.lyubimtsev.SummaryTask4.db.entities.Admin;
import ua.nure.lyubimtsev.SummaryTask4.db.entities.Doctor;
import ua.nure.lyubimtsev.SummaryTask4.db.entities.Patient;
import ua.nure.lyubimtsev.SummaryTask4.exception.AppException;
import ua.nure.lyubimtsev.SummaryTask4.web.commands.Command;
import ua.nure.lyubimtsev.SummaryTask4.web.commands.appointment.GetAppointmentOnUpdateCommand;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Assign patient to doctor.
 *
 * @author Vladislav
 *
 */
public class AssignPatientCommand extends Command {

    private static final Logger LOG = Logger.getLogger(AssignPatientCommand.class);

    @Override
    public Redirect execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {

        LOG.debug("Command starts");

        HttpSession session = request.getSession();

        int patientId = Integer.parseInt(request.getParameter("patientId"));
        int doctorId = (Integer) session.getAttribute("doctorId");

        boolean success;
        if (success = factory.getPatientDAO().assignPatient(patientId, doctorId) > 0) {

            List<Patient> unassignedPatients = (List<Patient>) session.getAttribute("unassignedPatients");
            Patient patientById = unassignedPatients
                    .stream()
                    .filter(patient -> patient.getId() == patientId)
                    .collect(Collectors.collectingAndThen(Collectors.toList(), list -> list.get(0)));

            Object user = session.getAttribute("user");
            Doctor doctor = null;
            Role role = (Role) session.getAttribute("role");
            switch (role) {
                case ADMIN:
                    doctor = ((Admin) user).getDoctorById(doctorId);
                    break;
                case DOCTOR:
                    doctor = ((Doctor) user);
                    break;
            }
            doctor.getPatients().add(patientById);
        }

        return new Redirect(Path.PRG_COMMAND + "&entity=Patient&action=assign&doctorId=" + doctorId + "&success=" + success, ForwardingType.SEND_REDIRECT);

    }
}
