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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Update patient.
 *
 * @author Vladislav
 */
public class UpdatePatientCommand extends Command {

    private static final Logger LOG = Logger.getLogger(UpdatePatientCommand.class);

    private static final String PATIENT_UPDATE_SUCCESS = "patient.edit.success";
    private static final String PATIENT_UPDATE_FAILED = "patient.edit.failed";

    @Override
    public Redirect execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {

        LOG.debug("Command starts");

        HttpSession session = request.getSession();

        int patientId = Integer.parseInt(request.getParameter("patientId"));
        LOG.trace("patientId --> " + patientId);

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

        List<State> states = ((List<State>) session.getAttribute("states"));
        int stateId = Integer.parseInt(request.getParameter("state"));
        State stateById = states
                .stream()
                .filter(state -> state.getId() == stateId)
                .collect(Collectors.collectingAndThen(Collectors.toList(), list -> list.get(0)));
        LOG.trace("stateById --> " + stateById);

        int doctorId = (int) session.getAttribute("doctorId");
        LOG.trace("doctorId --> " + doctorId);

        Object user = session.getAttribute("user");
        Patient patientById = null;
        Role role = (Role) session.getAttribute("role");
        LOG.trace("patientId --> " + patientId);
        switch (role) {
            case ADMIN:
                patientById = ((Admin) user).getDoctorById(doctorId).getPatientById(patientId);
                break;
            case DOCTOR:
                patientById = ((Doctor) user).getPatientById(patientId);
                break;
        }
        LOG.trace("patientById --> " + patientById);


        Patient tempPatient = new Patient(patientId, name, address, birthDate, stateById);

        boolean success = factory.getPatientDAO().updatePatient(tempPatient) > 0;
        if (success) {
            patientById.setName(name);
            patientById.setAddress(address);
            patientById.setBirthDate(birthDate);
            patientById.setState(stateById);
        }

        session.setAttribute("result", success ? PATIENT_UPDATE_SUCCESS : PATIENT_UPDATE_FAILED);

        LOG.debug("Commands finished");
        return new Redirect(Path.GET_PATIENTS_COMMAND + "&doctorId=" + doctorId, ForwardingType.SEND_REDIRECT);
    }
}