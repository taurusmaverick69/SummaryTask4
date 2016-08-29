package ua.nure.lyubimtsev.SummaryTask4.web.commands.patient;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;
import ua.nure.lyubimtsev.SummaryTask4.Role;
import ua.nure.lyubimtsev.SummaryTask4.db.dao.DAOFactory;
import ua.nure.lyubimtsev.SummaryTask4.db.entities.*;
import ua.nure.lyubimtsev.SummaryTask4.web.commands.CommandTest;
import ua.nure.lyubimtsev.SummaryTask4.web.commands.LoginCommand;

import java.util.List;

import static org.junit.Assert.*;

public class AssignPatientCommandTest extends CommandTest {

    @Test
    public void assignAdmin() throws Exception {

        DAOFactory factory = DAOFactory.getMySQLDAOFactory();
        MedicalCard medicalCardByPatientId = factory.getMedicalCardDAO().getMedicalCardByPatientId(1);
        medicalCardByPatientId.setAppointments(factory.getAppointmentDAO().getAppointmentsByMedicalCardId(medicalCardByPatientId.getId()));

        int doctorId = 103;

        List<Patient> unassignedPatients = factory.getPatientDAO().getUnassignedPatients(doctorId);

        Admin admin = factory.getAdminDAO().getAdminByLoginAndPassword("admin", DigestUtils.md5Hex("admin"));
        List<Doctor> allDoctors = factory.getDoctorDAO().getAllDoctors();
        admin.setDoctors(allDoctors);

        when(request.getSession()).thenReturn(session);

        when(request.getParameter("patientId")).thenReturn("65");
        when(session.getAttribute("doctorId")).thenReturn(doctorId);
        when(session.getAttribute("unassignedPatients")).thenReturn(unassignedPatients);
        when(session.getAttribute("user")).thenReturn(admin);
        when(session.getAttribute("role")).thenReturn(Role.ADMIN);

        assertNotNull(new AssignPatientCommand().execute(request, response));

        verify(request, atLeast(1)).getParameter("patientId");
        verify(session, atLeast(1)).getAttribute("doctorId");
        verify(session, atLeast(1)).getAttribute("unassignedPatients");
        verify(session, atLeast(1)).getAttribute("user");
        verify(session, atLeast(1)).getAttribute("role");

    }

    @Test
    public void assignDoctor() throws Exception {

        DAOFactory factory = DAOFactory.getMySQLDAOFactory();
        MedicalCard medicalCardByPatientId = factory.getMedicalCardDAO().getMedicalCardByPatientId(1);
        medicalCardByPatientId.setAppointments(factory.getAppointmentDAO().getAppointmentsByMedicalCardId(medicalCardByPatientId.getId()));

        int doctorId = 104;

        List<Patient> unassignedPatients = factory.getPatientDAO().getUnassignedPatients(doctorId);

        Doctor doctor = factory.getDoctorDAO().getDoctorByLoginAndPassword("login1", DigestUtils.md5Hex("pass1"));

        when(request.getSession()).thenReturn(session);

        when(request.getParameter("patientId")).thenReturn("65");
        when(session.getAttribute("doctorId")).thenReturn(doctorId);
        when(session.getAttribute("unassignedPatients")).thenReturn(unassignedPatients);
        when(session.getAttribute("user")).thenReturn(doctor);
        when(session.getAttribute("role")).thenReturn(Role.DOCTOR);

        assertNotNull(new AssignPatientCommand().execute(request, response));

        verify(request, atLeast(1)).getParameter("patientId");
        verify(session, atLeast(1)).getAttribute("doctorId");
        verify(session, atLeast(1)).getAttribute("unassignedPatients");
        verify(session, atLeast(1)).getAttribute("user");
        verify(session, atLeast(1)).getAttribute("role");

    }


}