package ua.nure.lyubimtsev.SummaryTask4.web.commands.patient;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;
import ua.nure.lyubimtsev.SummaryTask4.Role;
import ua.nure.lyubimtsev.SummaryTask4.db.dao.DAOFactory;
import ua.nure.lyubimtsev.SummaryTask4.db.entities.Admin;
import ua.nure.lyubimtsev.SummaryTask4.db.entities.Doctor;
import ua.nure.lyubimtsev.SummaryTask4.db.entities.State;
import ua.nure.lyubimtsev.SummaryTask4.web.commands.CommandTest;
import ua.nure.lyubimtsev.SummaryTask4.web.commands.LoginCommand;

import java.util.List;

import static org.junit.Assert.*;

public class InsertPatientCommandTest extends CommandTest {

    @Test
    public void adminInsertPatient() throws Exception {

        DAOFactory factory = DAOFactory.getMySQLDAOFactory();
        List<State> allStates = factory.getStateDAO().getAllStates();

        Admin admin = factory.getAdminDAO().getAdminByLoginAndPassword("admin", DigestUtils.md5Hex("admin"));
        List<Doctor> allDoctors = factory.getDoctorDAO().getAllDoctors();
        admin.setDoctors(allDoctors);

        when(request.getSession()).thenReturn(session);
        when(request.getParameter("name")).thenReturn("MyName");
        when(request.getParameter("address")).thenReturn("MyName");
        when(request.getParameter("birthDate")).thenReturn("23.05.2016");
        when(session.getAttribute("states")).thenReturn(allStates);
        when(request.getParameter("state")).thenReturn("1");
        when(session.getAttribute("doctorId")).thenReturn(1);
        when(session.getAttribute("user")).thenReturn(admin);
        when(session.getAttribute("role")).thenReturn(Role.ADMIN);

        assertNotNull(new InsertPatientCommand().execute(request, response));

        verify(request, atLeast(1)).getParameter("name");
        verify(request, atLeast(1)).getParameter("address");
        verify(request, atLeast(1)).getParameter("birthDate");
        verify(session, atLeast(1)).getAttribute("states");
        verify(request, atLeast(1)).getParameter("state");
        verify(session, atLeast(1)).getAttribute("doctorId");
        verify(session, atLeast(1)).getAttribute("user");
        verify(session, atLeast(1)).getAttribute("role");

    }

    @Test
    public void doctorInsertPatient() throws Exception {

        DAOFactory factory = DAOFactory.getMySQLDAOFactory();
        List<State> allStates = factory.getStateDAO().getAllStates();

        Doctor doctor = factory.getDoctorDAO().getDoctorByLoginAndPassword("login1", DigestUtils.md5Hex("pass1"));


        when(request.getSession()).thenReturn(session);
        when(request.getParameter("name")).thenReturn("MyName");
        when(request.getParameter("address")).thenReturn("MyName");
        when(request.getParameter("birthDate")).thenReturn("23.05.2016");
        when(session.getAttribute("states")).thenReturn(allStates);
        when(request.getParameter("state")).thenReturn("1");
        when(session.getAttribute("doctorId")).thenReturn(1);
        when(session.getAttribute("user")).thenReturn(doctor);
        when(session.getAttribute("role")).thenReturn(Role.DOCTOR);

        assertNotNull(new InsertPatientCommand().execute(request, response));

        verify(request, atLeast(1)).getParameter("name");
        verify(request, atLeast(1)).getParameter("address");
        verify(request, atLeast(1)).getParameter("birthDate");
        verify(session, atLeast(1)).getAttribute("states");
        verify(request, atLeast(1)).getParameter("state");
        verify(session, atLeast(1)).getAttribute("doctorId");
        verify(session, atLeast(1)).getAttribute("user");
        verify(session, atLeast(1)).getAttribute("role");

    }


}