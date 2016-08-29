package ua.nure.lyubimtsev.SummaryTask4.web.commands.patient;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;
import ua.nure.lyubimtsev.SummaryTask4.Role;
import ua.nure.lyubimtsev.SummaryTask4.db.dao.DAOFactory;
import ua.nure.lyubimtsev.SummaryTask4.db.entities.Admin;
import ua.nure.lyubimtsev.SummaryTask4.db.entities.Doctor;
import ua.nure.lyubimtsev.SummaryTask4.db.entities.Patient;
import ua.nure.lyubimtsev.SummaryTask4.web.commands.CommandTest;
import ua.nure.lyubimtsev.SummaryTask4.web.commands.LoginCommand;

import java.util.List;

import static org.junit.Assert.*;

public class GetUnassignedPatientsCommandTest extends CommandTest {

    @Test
    public void test() throws Exception {


        DAOFactory factory = DAOFactory.getMySQLDAOFactory();
        List<Patient> unassignedPatients = factory.getPatientDAO().getUnassignedPatients(1);

        when(request.getSession()).thenReturn(session);
        when(request.getParameter("doctorId")).thenReturn("1");
        when(session.getAttribute("unassignedPatients")).thenReturn(unassignedPatients);

        assertNotNull(new GetUnassignedPatientsCommand().execute(request, response));

        verify(request, atLeast(1)).getParameter("doctorId");


    }


}