package ua.nure.lyubimtsev.SummaryTask4.web.commands;

import org.junit.Test;

import static org.junit.Assert.*;

public class PRGCommandTest extends CommandTest {

    @Test
    public void doctorPRG() throws Exception {

        when(request.getParameter("entity")).thenReturn("Doctor");
        when(request.getSession()).thenReturn(session);

        new PRGCommand().execute(request, response);

    }

    @Test
    public void patientPRG() throws Exception {

        when(request.getParameter("entity")).thenReturn("Patient");
        when(request.getParameter("doctorId")).thenReturn("1");
        when(request.getSession()).thenReturn(session);

        new PRGCommand().execute(request, response);

    }

    @Test
    public void appointmentPRG() throws Exception {

        when(request.getParameter("entity")).thenReturn("Appointment");
        when(request.getSession()).thenReturn(session);

        new PRGCommand().execute(request, response);

    }
}