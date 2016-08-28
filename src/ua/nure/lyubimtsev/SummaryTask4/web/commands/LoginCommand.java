package ua.nure.lyubimtsev.SummaryTask4.web.commands;

import org.apache.commons.codec.digest.DigestUtils;
import ua.nure.lyubimtsev.SummaryTask4.ForwardingType;
import ua.nure.lyubimtsev.SummaryTask4.Path;
import ua.nure.lyubimtsev.SummaryTask4.Redirect;
import ua.nure.lyubimtsev.SummaryTask4.Role;
import ua.nure.lyubimtsev.SummaryTask4.db.dao.DAOFactory;
import ua.nure.lyubimtsev.SummaryTask4.db.entities.Admin;
import ua.nure.lyubimtsev.SummaryTask4.db.entities.Doctor;
import ua.nure.lyubimtsev.SummaryTask4.exception.AppException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Login command.
 *
 * @author Vladislav
 *
 */
public class LoginCommand extends Command {

    @Override
    public Redirect execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException, AppException {

        HttpSession session = request.getSession();

        Redirect redirect = new Redirect();
        redirect.setForwardingType(ForwardingType.SEND_REDIRECT);


        String login = request.getParameter("login");
        String password = request.getParameter("password");

        Admin admin = factory.getAdminDAO().getAdminByLoginAndPassword(login, DigestUtils.md5Hex(password));

        String contextPath = request.getContextPath();
        if (admin == null) {
            Doctor doctor = factory.getDoctorDAO().getDoctorByLoginAndPassword(login, DigestUtils.md5Hex(password));
            if (doctor == null) {
                request.setAttribute("loginFailed", "Invalid username or password");
                return new Redirect(Path.LOGIN_PAGE, ForwardingType.FORWARD);
            } else {
                session.setAttribute("user", doctor);
                session.setAttribute("role", Role.DOCTOR);
                redirect.setURL(contextPath + Path.GET_PATIENTS_COMMAND);
            }

        } else {
            session.setAttribute("user", admin);
            session.setAttribute("role", Role.ADMIN);
            redirect.setURL(contextPath + Path.GET_DOCTORS_COMMAND);
        }

        return redirect;
    }
}
