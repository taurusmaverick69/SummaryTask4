package ua.nure.lyubimtsev.SummaryTask4.web.commands;

import org.apache.log4j.Logger;
import ua.nure.lyubimtsev.SummaryTask4.ForwardingType;
import ua.nure.lyubimtsev.SummaryTask4.Path;
import ua.nure.lyubimtsev.SummaryTask4.Redirect;
import ua.nure.lyubimtsev.SummaryTask4.exception.AppException;
import ua.nure.lyubimtsev.SummaryTask4.web.commands.patient.UpdatePatientCommand;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Logout command.
 *
 * @author Vladislav
 *
 */
public class LogoutCommand extends Command {

    private static final Logger LOG = Logger.getLogger(LogoutCommand.class);

    @Override
    public Redirect execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException, AppException {

        LOG.debug("Command starts");

        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        LOG.trace("Session invalidated --> " + session);

        LOG.debug("Commands finished");
        return new Redirect(Path.LOGIN_PAGE, ForwardingType.FORWARD);
    }
}
