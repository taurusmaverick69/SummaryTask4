package ua.nure.lyubimtsev.SummaryTask4.web.commands.doctor;

import ua.nure.lyubimtsev.SummaryTask4.ForwardingType;
import ua.nure.lyubimtsev.SummaryTask4.Path;
import ua.nure.lyubimtsev.SummaryTask4.Redirect;
import ua.nure.lyubimtsev.SummaryTask4.web.commands.Command;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DisplayInsertDoctorCommand", urlPatterns = "/displayInsertDoctorServlet")
public class DisplayInsertDoctorCommand extends Command {

    @Override
    public Redirect execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        boolean success = Boolean.parseBoolean(request.getParameter("success"));
        request.setAttribute("result", success ? "Doctor Successfully Inserted" : "Doctor Not Inserted");
        return new Redirect(Path.DOCTORS_PAGE, ForwardingType.FORWARD);

    }
}
