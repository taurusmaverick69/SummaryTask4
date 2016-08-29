package ua.nure.lyubimtsev.SummaryTask4.web.commands.patient;

import org.junit.Test;
import ua.nure.lyubimtsev.SummaryTask4.db.dao.DAOFactory;
import ua.nure.lyubimtsev.SummaryTask4.db.entities.Patient;
import ua.nure.lyubimtsev.SummaryTask4.web.commands.CommandTest;
import ua.nure.lyubimtsev.SummaryTask4.web.commands.LoginCommand;

import java.util.List;

import static org.junit.Assert.*;

public class GetPatientOnUpdateCommandTest extends CommandTest {

    @Test
    public void test() throws Exception {

        DAOFactory factory = DAOFactory.getMySQLDAOFactory();
        List<Patient> allPatients = factory.getPatientDAO().getAllPatients();

        when(request.getSession()).thenReturn(session);

        when(request.getParameter("patientId")).thenReturn("1");
        when(session.getAttribute("patients")).thenReturn(allPatients);


        assertNotNull(new GetPatientOnUpdateCommand().execute(request, response));

        verify(request, atLeast(1)).getParameter("patientId");
        verify(session, atLeast(1)).getAttribute("patients");

    }


}