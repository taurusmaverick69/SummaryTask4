package ua.nure.lyubimtsev.SummaryTask4.web.commands;

import org.apache.log4j.Logger;
import ua.nure.lyubimtsev.SummaryTask4.ForwardingType;
import ua.nure.lyubimtsev.SummaryTask4.Path;
import ua.nure.lyubimtsev.SummaryTask4.Redirect;
import ua.nure.lyubimtsev.SummaryTask4.exception.AppException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * No command.
 *
 * @author Vladislav
 */
public class PRGCommand extends Command {


    private static final String INSERT = "Insert";
    private static final String UPDATE = "Update";
    private static final String ASSIGN = "Assign";

    private static final String DOCTOR = "Doctor";
    private static final String PATIENT = "Patient";
    private static final String APPOINTMENT = "Appointment";

    private static final String DOCTOR_INSERT_SUCCESS = "result.doctor.add.success";
    private static final String DOCTOR_INSERT_FAILED = "result.doctor.add.failed";

    private static final String DOCTOR_UPDATE_SUCCESS = "result.doctor.edit.success";
    private static final String DOCTOR_UPDATE_FAILED = "result.doctor.edit.failed";

    private static final String PATIENT_INSERT_SUCCESS = "result.patient.add.success";
    private static final String PATIENT_INSERT_FAILED = "result.patient.add.failed";

    private static final String PATIENT_UPDATE_SUCCESS = "result.patient.edit.success";
    private static final String PATIENT_UPDATE_FAILED = "result.patient.edit.failed";

    private static final String PATIENT_ASSIGN_SUCCESS = "result.patient.assign.success";
    private static final String PATIENT_ASSIGN_FAILED = "result.patient.assign.failed";

    private static final String APPOINTMENT_INSERT_SUCCESS = "result.appointment.add.success";
    private static final String APPOINTMENT_INSERT_FAILED = "result.appointment.add.failed";

    private static final String APPOINTMENT_UPDATE_SUCCESS = "result.appointment.edit.success";
    private static final String APPOINTMENT_UPDATE_FAILED = "result.appointment.edit.failed";

    private static final String ERROR = "Error";

    private static final Logger LOG = Logger.getLogger(PRGCommand.class);

    @Override
    public Redirect execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {

        LOG.debug("Command starts");

        boolean success = Boolean.parseBoolean(request.getParameter("success"));

        String entity = request.getParameter("entity");
        String action = request.getParameter("action");

        Redirect redirect = new Redirect();
        redirect.setForwardingType(ForwardingType.FORWARD);

        String result = "";

        switch (entity) {
            case DOCTOR:
                switch (action) {
                    case INSERT:
                        result = success ? DOCTOR_INSERT_SUCCESS : DOCTOR_INSERT_FAILED;
                        break;

                    case UPDATE:
                        result = success ? DOCTOR_UPDATE_SUCCESS : DOCTOR_UPDATE_FAILED;
                        break;
                }
                redirect.setURL(Path.GET_DOCTORS_COMMAND);
                break;

            case PATIENT:
                switch (action) {
                    case INSERT:
                        result = success ? PATIENT_INSERT_SUCCESS : PATIENT_INSERT_FAILED;
                        break;

                    case UPDATE:
                        result = success ? PATIENT_UPDATE_SUCCESS : PATIENT_UPDATE_FAILED;
                        break;

                    case ASSIGN:
                        result = success ? PATIENT_ASSIGN_SUCCESS : PATIENT_ASSIGN_FAILED;
                        break;
                }
                int doctorId = Integer.parseInt(request.getParameter("doctorId"));
                redirect.setURL(Path.GET_PATIENTS_COMMAND + "&doctorId=" + doctorId);
                break;

            case APPOINTMENT:
                switch (action) {
                    case INSERT:
                        result = success ? APPOINTMENT_INSERT_SUCCESS : APPOINTMENT_INSERT_FAILED;
                        break;
                    case UPDATE:
                        result = success ? APPOINTMENT_UPDATE_SUCCESS : APPOINTMENT_UPDATE_FAILED;
                        break;
                }
                redirect.setURL(Path.GET_APPOINTMENTS_COMMAND);
                break;
            default:
                result = ERROR;
        }

        request.setAttribute("result", result);

        LOG.debug("Commands finished");
        return redirect;
    }
}
