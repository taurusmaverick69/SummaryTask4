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

public class UpdatePatientCommand extends Command {
    @Override
    public Redirect execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {

        HttpSession session = request.getSession();

        String name = request.getParameter("name");
        String address = request.getParameter("address");

        Date birthDate = null;
        try {
            birthDate = new SimpleDateFormat("dd.MM.yyyy").parse(request.getParameter("birthDate"));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        List<State> states = ((List<State>) session.getAttribute("states"));
        int stateId = Integer.parseInt(request.getParameter("state"));
        State stateById = states
                .stream()
                .filter(state -> state.getId() == stateId)
                .collect(Collectors.collectingAndThen(Collectors.toList(), list -> list.get(0)));

        int doctorId = Integer.parseInt(request.getParameter("doctor"));

        Patient patientById = (Patient) session.getAttribute("patientById");
        Patient tempPatient = new Patient(patientById.getId(), name, address, birthDate, stateById);
        boolean success;
        if (success = factory.getPatientDAO().updatePatient(tempPatient) > 0) {
            patientById.setName(name);
            patientById.setAddress(address);
            patientById.setBirthDate(birthDate);
            patientById.setState(stateById);
        }
        return new Redirect(Path.PRG_COMMAND + "&entity=Patient&action=update&doctorId=" + doctorId + "&success=" + success, ForwardingType.SEND_REDIRECT);
    }
}
