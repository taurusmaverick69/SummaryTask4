package ua.nure.lyubimtsev.SummaryTask4.web.commands.patient;

import ua.nure.lyubimtsev.SummaryTask4.ForwardingType;
import ua.nure.lyubimtsev.SummaryTask4.Path;
import ua.nure.lyubimtsev.SummaryTask4.Redirect;
import ua.nure.lyubimtsev.SummaryTask4.db.dao.DAOFactory;
import ua.nure.lyubimtsev.SummaryTask4.db.entities.*;
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

public class InsertPatientCommand extends Command {
    @Override
    public Redirect execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {

        HttpSession session = request.getSession();

        List<State> states = (List<State>) session.getAttribute("states");
        int stateId = Integer.parseInt(request.getParameter("state"));
        State myState = states
                .stream()
                .filter(state -> state.getId() == stateId)
                .collect(Collectors.collectingAndThen(Collectors.toList(), list -> list.get(0)));


        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("date"));
        } catch (ParseException e) {
            e.printStackTrace();
        }


        Object user = session.getAttribute("user");
        int doctorId = user instanceof Admin ? Integer.parseInt(request.getParameter("doctor")) : ((Doctor) user).getId();

        Patient patient = new Patient(
                request.getParameter("name"),
                request.getParameter("address"),
                date,
                myState,
                doctorId
        );

        boolean success = DAOFactory.getMySQLDAOFactory().getPatientDAO().insertPatient(patient) > 0;
        if (success) {




            if (user instanceof Admin) {
                ((Admin) user).getPatients().add(patient);
            }

            if (user instanceof Doctor) {
                ((Doctor) user).getPatients().add(patient);
            }
        }
        return new Redirect(Path.DISPLAY_INSERT_PATIENT_COMMAND + "&success=" + success, ForwardingType.SEND_REDIRECT);
    }
}
