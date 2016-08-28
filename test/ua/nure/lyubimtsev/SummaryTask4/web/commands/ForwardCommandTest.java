package ua.nure.lyubimtsev.SummaryTask4.web.commands;

import org.junit.Test;

import static org.junit.Assert.*;

public class ForwardCommandTest extends CommandTest {

    @Test
    public void page() throws Exception {

        when(request.getParameter("page")).thenReturn("testPage");
        assertNotNull(new ForwardCommand().execute(request, response));
        verify(request, atLeast(1)).getParameter("page");

    }

}