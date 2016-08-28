package ua.nure.lyubimtsev.SummaryTask4.web.commands;


import org.junit.Test;

import static org.junit.Assert.assertNotNull;


public class LogoutCommandTest extends CommandTest {

    @Test
    public void name() throws Exception {

        when(request.getSession()).thenReturn(session);

        assertNotNull(new LogoutCommand().execute(request, response));


    }


}