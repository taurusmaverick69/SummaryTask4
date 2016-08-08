package ua.nure.lyubimtsev.summarytask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.lyubimtsev.summarytask4.ForwardingType;
import ua.nure.lyubimtsev.summarytask4.Path;
import ua.nure.lyubimtsev.summarytask4.Redirect;
import ua.nure.lyubimtsev.summarytask4.exception.AppException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class NoCommand extends Command {

    private static final Logger LOG = Logger.getLogger(NoCommand.class);


    @Override
    public Redirect execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        LOG.debug("Command starts");

        String errorMessage = "No such command";
        request.setAttribute("errorMessage", errorMessage);
        LOG.error("Set the request attribute: errorMessage --> " + errorMessage);

        LOG.debug("Command finished");
        return new Redirect(Path.PAGE_ERROR_PAGE, ForwardingType.FORWARD);
    }
}
