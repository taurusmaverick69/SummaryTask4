package ua.nure.lyubimtsev.SummaryTask4.web.commands.patient;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;
import ua.nure.lyubimtsev.SummaryTask4.Role;
import ua.nure.lyubimtsev.SummaryTask4.db.dao.DAOFactory;
import ua.nure.lyubimtsev.SummaryTask4.db.entities.Admin;
import ua.nure.lyubimtsev.SummaryTask4.db.entities.Doctor;
import ua.nure.lyubimtsev.SummaryTask4.web.commands.CommandTest;
import ua.nure.lyubimtsev.SummaryTask4.web.commands.LoginCommand;

import java.util.List;

import static org.junit.Assert.*;

public class GetPatientsCommandTest extends CommandTest {

    @Test
    public void adminGetPatients() throws Exception {

        DAOFactory factory = DAOFactory.getMySQLDAOFactory();
        Admin admin = factory.getAdminDAO().getAdminByLoginAndPassword("admin", DigestUtils.md5Hex("admin"));
        List<Doctor> allDoctors = factory.getDoctorDAO().getAllDoctors();
        admin.setDoctors(allDoctors);


        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("role")).thenReturn(Role.ADMIN);
        when(session.getAttribute("user")).thenReturn(admin);
        when(request.getParameter("doctorId")).thenReturn("1");

        assertNotNull(new GetPatientsCommand().execute(request, response));

        verify(session, atLeast(1)).getAttribute("role");
        verify(session, atLeast(1)).getAttribute("user");
        verify(request, atLeast(1)).getParameter("doctorId");

    }


    @Test
    public void doctorGetPatients() throws Exception {

        DAOFactory factory = DAOFactory.getMySQLDAOFactory();
        Doctor doctor = factory.getDoctorDAO().getDoctorByLoginAndPassword("login1", DigestUtils.md5Hex("pass1"));

        when(request.getSession()).thenReturn(session);

        when(session.getAttribute("role")).thenReturn(Role.DOCTOR);
        when(session.getAttribute("user")).thenReturn(doctor);
        when(request.getParameter("doctorId")).thenReturn("1");

        assertNotNull(new GetPatientsCommand().execute(request, response));

        verify(session, atLeast(1)).getAttribute("role");
        verify(session, atLeast(1)).getAttribute("user");

    }

}