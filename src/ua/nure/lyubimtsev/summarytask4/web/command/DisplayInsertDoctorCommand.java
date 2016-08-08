package ua.nure.lyubimtsev.summarytask4.web.command;

import ua.nure.lyubimtsev.summarytask4.ForwardingType;
import ua.nure.lyubimtsev.summarytask4.Path;
import ua.nure.lyubimtsev.summarytask4.Redirect;
import ua.nure.lyubimtsev.summarytask4.web.command.Command;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DisplayInsertDoctorCommand", urlPatterns = "/displayInsertDoctorServlet")
public class DisplayInsertDoctorCommand extends Command {

    @Override
    public Redirect execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        boolean success = Boolean.parseBoolean("success");
        request.setAttribute("result", success ? "Employee Successfully Inserted" : "Employee Not Inserted");
        return new Redirect(Path.PAGE_DOCTORS_PAGE, ForwardingType.FORWARD);

    }
}
