package ua.nure.lyubimtsev.SummaryTask4.web.commands;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;
import ua.nure.lyubimtsev.SummaryTask4.ForwardingType;
import ua.nure.lyubimtsev.SummaryTask4.Path;
import ua.nure.lyubimtsev.SummaryTask4.Redirect;
import ua.nure.lyubimtsev.SummaryTask4.Role;
import ua.nure.lyubimtsev.SummaryTask4.db.dao.DAOFactory;
import ua.nure.lyubimtsev.SummaryTask4.db.entities.Admin;
import ua.nure.lyubimtsev.SummaryTask4.db.entities.Doctor;
import ua.nure.lyubimtsev.SummaryTask4.exception.AppException;
import ua.nure.lyubimtsev.SummaryTask4.web.commands.patient.UpdatePatientCommand;

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

    private static final String INVALID = "login.invalid";

    private static final Logger LOG = Logger.getLogger(LoginCommand.class);

    @Override
    public Redirect execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException, AppException {

        LOG.debug("Command starts");

        HttpSession session = request.getSession();

        Redirect redirect = new Redirect();
        redirect.setForwardingType(ForwardingType.SEND_REDIRECT);

        String login = request.getParameter("login");
        String password = request.getParameter("password");

        Admin admin = factory.getAdminDAO().getAdminByLoginAndPassword(login, DigestUtils.md5Hex(password));

        if (admin == null) {
            Doctor doctor = factory.getDoctorDAO().getDoctorByLoginAndPassword(login, DigestUtils.md5Hex(password));
            if (doctor == null) {
                request.setAttribute("loginFailed", INVALID);
                return new Redirect(Path.LOGIN_PAGE, ForwardingType.FORWARD);
            } else {
                LOG.trace("Found in DB: user --> " + doctor);

                session.setAttribute("user", doctor);
                LOG.trace("Set the session attribute: user --> " + doctor);

                Role role = Role.DOCTOR;
                LOG.trace("userRole --> " + role);

                session.setAttribute("role", role);
                LOG.trace("Set the session attribute: role --> " + role);

                LOG.info("User " + doctor + " logged as " + role.toString().toLowerCase());
                redirect.setURL(Path.GET_PATIENTS_COMMAND);
            }

        } else {
            LOG.trace("Found in DB: user --> " + admin);

            session.setAttribute("user", admin);
            LOG.trace("Set the session attribute: user --> " + admin);

            Role role = Role.ADMIN;
            LOG.trace("userRole --> " + role);

            session.setAttribute("role", role);
            LOG.trace("Set the session attribute: role --> " + role);

            LOG.info("User " + admin + " logged as " + role.toString().toLowerCase());
            redirect.setURL(Path.GET_DOCTORS_COMMAND);
        }
        LOG.debug("Commands finished");
        return redirect;
    }
}