package ua.nure.lyubimtsev.SummaryTask4.web.commands.patient;


import ua.nure.lyubimtsev.SummaryTask4.ForwardingType;
import ua.nure.lyubimtsev.SummaryTask4.Path;
import ua.nure.lyubimtsev.SummaryTask4.Redirect;
import ua.nure.lyubimtsev.SummaryTask4.db.dao.DAOFactory;
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
import java.util.List;
import java.util.stream.Collectors;

public class AssignPatientCommand extends Command {
    @Override
    public Redirect execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {

        HttpSession session = request.getSession();

        int patientId = Integer.parseInt(request.getParameter("patientId"));
        int doctorId = ((Doctor) request.getSession().getAttribute("doctor")).getId();

        boolean success;
        if (success = DAOFactory.getMySQLDAOFactory().getPatientDAO().assignPatient(patientId, doctorId) > 0) {

            List<Patient> unassignedPatients = (List<Patient>) session.getAttribute("unassignedPatients");
            Patient patientById = unassignedPatients
                    .stream()
                    .filter(patient -> patient.getId() == patientId)
                    .collect(Collectors.collectingAndThen(Collectors.toList(), list -> list.get(0)));

            Doctor doctor = (Doctor) session.getAttribute("doctor");
            doctor.getPatients().add(patientById);
        }

        return new Redirect(Path.PRG_COMMAND + "&entity=Patient&action=assign&doctorId=" + doctorId + "&success=" + success, ForwardingType.SEND_REDIRECT);

    }
}
