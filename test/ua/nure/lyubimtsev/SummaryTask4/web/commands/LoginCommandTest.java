package ua.nure.lyubimtsev.SummaryTask4.web.commands;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class LoginCommandTest extends CommandTest {

    @Test
    public void adminExists() throws Exception {

        when(request.getParameter("login")).thenReturn("admin");
        when(request.getParameter("password")).thenReturn("admin");
        when(request.getSession()).thenReturn(session);

        assertNotNull(new LoginCommand().execute(request, response));

        verify(request, atLeast(1)).getParameter("login");
        verify(request, atLeast(1)).getParameter("password");

    }

    @Test
    public void doctorExists() throws Exception {

        when(request.getParameter("login")).thenReturn("login1");
        when(request.getParameter("password")).thenReturn("pass1");
        when(request.getSession()).thenReturn(session);

        assertNotNull(new LoginCommand().execute(request, response));

        verify(request, atLeast(1)).getParameter("login");
        verify(request, atLeast(1)).getParameter("password");

    }

    @Test
    public void noneUser() throws Exception {

        when(request.getParameter("login")).thenReturn("aaa");
        when(request.getParameter("password")).thenReturn("aaa");
        when(request.getSession()).thenReturn(session);

        assertNotNull(new LoginCommand().execute(request, response));

        verify(request, atLeast(1)).getParameter("login");
        verify(request, atLeast(1)).getParameter("password");

    }

}