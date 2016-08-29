package ua.nure.lyubimtsev.SummaryTask4.web.commands.medicalCard;

import org.junit.Test;
import ua.nure.lyubimtsev.SummaryTask4.web.commands.CommandTest;
import ua.nure.lyubimtsev.SummaryTask4.web.commands.LoginCommand;

import static org.junit.Assert.*;

public class GetMedicalCardCommandTest extends CommandTest {

    @Test
    public void test() throws Exception {


        when(request.getParameter("login")).thenReturn("admin");
        when(request.getParameter("password")).thenReturn("admin");
        when(request.getSession()).thenReturn(session);

        assertNotNull(new LoginCommand().execute(request, response));

        verify(request, atLeast(1)).getParameter("login");
        verify(request, atLeast(1)).getParameter("password");

    }


}