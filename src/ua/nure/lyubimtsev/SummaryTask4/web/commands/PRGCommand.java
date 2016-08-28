package ua.nure.lyubimtsev.SummaryTask4.web.commands;

import ua.nure.lyubimtsev.SummaryTask4.*;
import ua.nure.lyubimtsev.SummaryTask4.exception.AppException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PRGCommand extends Command {

    @Override
    public Redirect execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {

        boolean success = Boolean.parseBoolean(request.getParameter("success"));

        String entity = request.getParameter("entity");
        String action = request.getParameter("action");

        StringBuilder result = new StringBuilder();
        result.append(entity)
                .append(" ")
                .append(action)
                .append(success ? " successful" : " failed");

        request.setAttribute("result", result);

        Redirect redirect = new Redirect();
        redirect.setForwardingType(ForwardingType.FORWARD);

        switch (entity) {
            case "Doctor":
                redirect.setURL(Path.GET_DOCTORS_COMMAND);
                break;
            case "Patient":
                int doctorId = Integer.parseInt(request.getParameter("doctorId"));
                redirect.setURL(Path.GET_PATIENTS_COMMAND + "&doctorId=" + doctorId);
                break;
            case "Appointment":
                redirect.setURL(Path.GET_APPOINTMENTS_COMMAND);
                break;
        }
        return redirect;
    }
}
