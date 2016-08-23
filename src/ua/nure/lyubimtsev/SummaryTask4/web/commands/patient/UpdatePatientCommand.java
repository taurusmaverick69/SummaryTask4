package ua.nure.lyubimtsev.SummaryTask4.web.commands.patient;

import ua.nure.lyubimtsev.SummaryTask4.ForwardingType;
import ua.nure.lyubimtsev.SummaryTask4.Path;
import ua.nure.lyubimtsev.SummaryTask4.Redirect;
import ua.nure.lyubimtsev.SummaryTask4.Role;
import ua.nure.lyubimtsev.SummaryTask4.db.dao.DAOFactory;
import ua.nure.lyubimtsev.SummaryTask4.db.dao.entitydao.PatientDAO;
import ua.nure.lyubimtsev.SummaryTask4.db.entities.*;
import ua.nure.lyubimtsev.SummaryTask4.exception.AppException;
import ua.nure.lyubimtsev.SummaryTask4.web.commands.Command;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(name = "EditPatientCommand", urlPatterns = "/editPatientServlet")
public class UpdatePatientCommand extends Command {
    @Override
    public Redirect execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {


        HttpSession session = request.getSession();

        int stateId = Integer.parseInt(request.getParameter("state"));
        List<State> states = ((List<State>) session.getAttribute("states"));
        State stateById = states
                .stream()
                .filter(state -> state.getId() == stateId)
                .collect(Collectors.collectingAndThen(Collectors.toList(), list -> list.get(0)));

        int patientId = Integer.parseInt(request.getParameter("id"));

        Date birthDate = new Date();
        try {
            birthDate = new SimpleDateFormat("dd.MM.yyyy").parse(request.getParameter("birthDate"));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        String name = request.getParameter("name");
        String address = request.getParameter("address");


        List<Patient> patients = new ArrayList<>();
        Role role = (Role) session.getAttribute("role");
        switch (role) {
            case ADMIN:
                patients = ((Admin) session.getAttribute("user")).getPatients();
                break;
            case DOCTOR:
                patients = ((Doctor) session.getAttribute("user")).getPatients();
                break;
        }

        Patient patientById = patients
                .stream()
                .filter(patient -> patient.getId() == patientId)
                .collect(Collectors.collectingAndThen(Collectors.toList(), list -> list.get(0)));

        PatientDAO patientDAO = DAOFactory.getMySQLDAOFactory().getPatientDAO();
        patientById.setName(name);
        patientById.setAddress(address);
        patientById.setBirthDate(birthDate);
        patientById.setState(stateById);

        boolean success;
        if (success = patientDAO.updatePatient(patientById) > 0) {
            patients.set(patients.indexOf(patientById), patientById);
        }
        return new Redirect(Path.PRG_COMMAND + "&entity=Patient&action=update&success=" + success, ForwardingType.SEND_REDIRECT);
    }
}
