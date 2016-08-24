package ua.nure.lyubimtsev.SummaryTask4.web.commands;

import ua.nure.lyubimtsev.SummaryTask4.Redirect;
import ua.nure.lyubimtsev.SummaryTask4.db.dao.entitydao.*;
import ua.nure.lyubimtsev.SummaryTask4.exception.AppException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

public abstract class Command implements Serializable {

    /**
     * Execution method for commands.
     *
     * @return Type of Object Redirect which contains address to go once the commands is executed and type of forwarding(sendRedirect for PRG pattern or forward).
     */
    public abstract Redirect execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException;

}