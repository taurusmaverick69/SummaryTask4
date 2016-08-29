package ua.nure.lyubimtsev.SummaryTask4.web.commands;

import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class CommandTest extends Mockito {

    protected HttpServletRequest request = mock(HttpServletRequest.class);
    protected HttpServletResponse response = mock(HttpServletResponse.class);
    protected HttpSession session = mock(HttpSession.class);

}


