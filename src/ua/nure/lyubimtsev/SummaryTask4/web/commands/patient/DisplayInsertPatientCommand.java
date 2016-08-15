package ua.nure.lyubimtsev.SummaryTask4.web.commands.patient;

import ua.nure.lyubimtsev.SummaryTask4.ForwardingType;
import ua.nure.lyubimtsev.SummaryTask4.Path;
import ua.nure.lyubimtsev.SummaryTask4.Redirect;
import ua.nure.lyubimtsev.SummaryTask4.exception.AppException;
import ua.nure.lyubimtsev.SummaryTask4.web.commands.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DisplayInsertPatientCommand extends Command {
    @Override
    public Redirect execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {


        System.out.println("DisplayInsertPatientCommand.execute");
        boolean success = Boolean.parseBoolean(request.getParameter("success"));
        request.setAttribute("result", success ? "Patient Successfully Inserted" : "Patient Not Inserted");
        return new Redirect(Path.PATIENTS_PAGE, ForwardingType.FORWARD);

    }
}
