package ua.nure.lyubimtsev.summarytask4.web;

import org.apache.log4j.Logger;
import ua.nure.lyubimtsev.summarytask4.ForwardingType;
import ua.nure.lyubimtsev.summarytask4.Path;
import ua.nure.lyubimtsev.summarytask4.Redirect;
import ua.nure.lyubimtsev.summarytask4.exception.AppException;
import ua.nure.lyubimtsev.summarytask4.web.command.Command;
import ua.nure.lyubimtsev.summarytask4.web.command.CommandContainer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 * Main servlet controller.
 *
 * @author V.Lyubimtsev
 */

@WebServlet(name = "Controller", urlPatterns = "/controller")
public class Controller extends HttpServlet {

    private static final long serialVersionUID = 2423353715955164816L;

    private static final Logger LOG = Logger.getLogger(Controller.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    /**
     * Main method of this controller.
     */
    private void process(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        LOG.debug("Controller starts");

        // extract command name from the request
        String commandName = request.getParameter("command");
        LOG.trace("Request parameter: command --> " + commandName);

        // obtain command object by its name
        Command command = CommandContainer.get(commandName);
        LOG.trace("Obtained command --> " + command);

        // execute command and get forward address
        Redirect redirect = new Redirect(Path.PAGE_ERROR_PAGE, ForwardingType.FORWARD);

        try {
            redirect = command.execute(request, response);
        } catch (AppException ex) {
            request.setAttribute("errorMessage", ex.getMessage());
        }
        LOG.trace("Forward address --> " + redirect.getURL());

        LOG.debug("Controller finished, now go to forward address --> " + redirect.getURL());


        // go to forward
        switch (redirect.getForwardingType()) {
            case SEND_REDIRECT:
                response.sendRedirect(redirect.getURL());
                break;
            case FORWARD:
                request.getRequestDispatcher(redirect.getURL()).forward(request, response);
                break;
        }
    }

}