package ua.nure.lyubimtsev.SummaryTask4.web.commands;

import ua.nure.lyubimtsev.SummaryTask4.ForwardingType;
import ua.nure.lyubimtsev.SummaryTask4.Redirect;
import ua.nure.lyubimtsev.SummaryTask4.exception.AppException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Command, that forwarding to specify page.
 *
 * @author Vladislav
 *
 */
public class ForwardCommand extends Command {
    @Override
    public Redirect execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        String page = request.getParameter("page");
        return page != null ?
                new Redirect(page, ForwardingType.FORWARD)
                : null;

    }
}
