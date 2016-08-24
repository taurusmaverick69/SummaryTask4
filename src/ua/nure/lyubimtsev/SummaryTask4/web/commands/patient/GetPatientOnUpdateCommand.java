package ua.nure.lyubimtsev.SummaryTask4.web.commands.patient;

import ua.nure.lyubimtsev.SummaryTask4.ForwardingType;
import ua.nure.lyubimtsev.SummaryTask4.Path;
import ua.nure.lyubimtsev.SummaryTask4.Redirect;
import ua.nure.lyubimtsev.SummaryTask4.Role;
import ua.nure.lyubimtsev.SummaryTask4.db.dao.DAOFactory;
import ua.nure.lyubimtsev.SummaryTask4.db.entities.*;
import ua.nure.lyubimtsev.SummaryTask4.exception.AppException;
import ua.nure.lyubimtsev.SummaryTask4.web.commands.Command;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GetPatientOnUpdateCommand extends Command {

    @Override
    public Redirect execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {

        HttpSession session = request.getSession();

        int id = Integer.parseInt(request.getParameter("id"));


        Role role = (Role) session.getAttribute("role");
        List<Patient> patients = new ArrayList<>();

//        switch (role) {
//            case ADMIN:
//                patients = ((Admin) session.getAttribute("user")).getPatients();
//                break;
//
//            case DOCTOR:
//                patients = ((Doctor) session.getAttribute("user")).getPatients();
//                break;
//        }

        Patient patientById = patients
                .stream()
                .filter(doctor -> doctor.getId() == id)
                .collect(Collectors.collectingAndThen(Collectors.toList(), list -> list.get(0)));

        List<State> states = DAOFactory.getMySQLDAOFactory().getStateDAO().getStates();

        session.setAttribute("patient", patientById);
        session.setAttribute("states", states);

        return new Redirect(Path.UPDATE_PATIENT_PAGE, ForwardingType.FORWARD);

    }
}
