package ua.nure.lyubimtsev.SummaryTask4.web.commands.doctor;

import ua.nure.lyubimtsev.SummaryTask4.ForwardingType;
import ua.nure.lyubimtsev.SummaryTask4.Path;
import ua.nure.lyubimtsev.SummaryTask4.Redirect;
import ua.nure.lyubimtsev.SummaryTask4.db.entities.Admin;
import ua.nure.lyubimtsev.SummaryTask4.exception.AppException;
import ua.nure.lyubimtsev.SummaryTask4.web.commands.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class GetDoctorOnUpdateCommand extends Command {

    @Override
    public Redirect execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {

        HttpSession session = request.getSession();
        int id = Integer.parseInt(request.getParameter("id"));

        Admin admin = (Admin) session.getAttribute("user");

        request.setAttribute("doctorById", admin.getDoctorById(id));

        return new Redirect(Path.DOCTORS_PAGE, ForwardingType.FORWARD);
    }
}
