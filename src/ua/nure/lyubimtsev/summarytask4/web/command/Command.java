package ua.nure.lyubimtsev.summarytask4.web.command;

import ua.nure.lyubimtsev.summarytask4.Redirect;
import ua.nure.lyubimtsev.summarytask4.exception.AppException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

public abstract class Command implements Serializable {

    /**
     * Execution method for command.
     *
     * @return Type of Object Redirect which contains address to go once the command is executed and type of forwarding(sendRedirect for PRG pattern or forward).
     */
    public abstract Redirect execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException;

}