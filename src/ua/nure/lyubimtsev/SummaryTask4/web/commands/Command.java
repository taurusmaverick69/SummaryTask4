package ua.nure.lyubimtsev.SummaryTask4.web.commands;

import ua.nure.lyubimtsev.SummaryTask4.Redirect;
import ua.nure.lyubimtsev.SummaryTask4.db.dao.DAOFactory;
import ua.nure.lyubimtsev.SummaryTask4.exception.AppException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

/**
 * Main interface for the Command pattern implementation.
 *
 * @author Vladislav
 *
 */
public abstract class Command implements Serializable {

    protected DAOFactory factory = DAOFactory.getMySQLDAOFactory();

    /**
     * Execution method for commands.
     *
     * @return Type of Object Redirect which contains address to go once the commands is executed and type of forwarding(sendRedirect for PRG pattern or forward).
     */
    public abstract Redirect execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException;
}