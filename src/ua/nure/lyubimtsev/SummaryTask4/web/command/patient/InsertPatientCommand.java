package ua.nure.lyubimtsev.SummaryTask4.web.command.patient;

import ua.nure.lyubimtsev.SummaryTask4.ForwardingType;
import ua.nure.lyubimtsev.SummaryTask4.Path;
import ua.nure.lyubimtsev.SummaryTask4.Redirect;
import ua.nure.lyubimtsev.SummaryTask4.db.dao.DAOFactory;
import ua.nure.lyubimtsev.SummaryTask4.db.entities.*;
import ua.nure.lyubimtsev.SummaryTask4.exception.AppException;
import ua.nure.lyubimtsev.SummaryTask4.web.command.Command;

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

public class InsertPatientCommand extends Command {
    @Override
    public Redirect execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {

        HttpSession session = request.getSession();

        List<State> states = (List<State>) session.getAttribute("states");

        String stateStr = request.getParameter("state");

        State myState = states
                .stream()
                .filter(state -> state.getName().equals(stateStr))
                .collect(Collectors.collectingAndThen(Collectors.toList(), list -> list.get(0)));

        String doctorIdStr = request.getParameter("doctor");
        Doctor doctor = (Doctor) session.getAttribute("doctor");
        int doctorId = doctorIdStr == null ? doctor.getId() : Integer.parseInt(doctorIdStr);


        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("date"));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Patient patient = new Patient(
                request.getParameter("name"),
                request.getParameter("address"),
                date,
                myState,
                doctorId
        );

        boolean success = DAOFactory.getMySQLDAOFactory().getPatientDAO().insertPatient(patient) > 0;
        if (success) {
            doctor.getPatients().add(patient);
        }
        return new Redirect("controller?command=displayInsertPatient&success=" + success, ForwardingType.SEND_REDIRECT);
    }
}
