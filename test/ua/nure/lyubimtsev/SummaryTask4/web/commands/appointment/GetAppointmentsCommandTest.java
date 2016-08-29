package ua.nure.lyubimtsev.SummaryTask4.web.commands.appointment;

import org.junit.Test;
import ua.nure.lyubimtsev.SummaryTask4.db.dao.DAOFactory;
import ua.nure.lyubimtsev.SummaryTask4.db.entities.Category;
import ua.nure.lyubimtsev.SummaryTask4.db.entities.Doctor;
import ua.nure.lyubimtsev.SummaryTask4.db.entities.MedicalCard;
import ua.nure.lyubimtsev.SummaryTask4.db.entities.Patient;
import ua.nure.lyubimtsev.SummaryTask4.web.commands.CommandTest;

import java.util.List;

import static org.junit.Assert.assertNotNull;

public class GetAppointmentsCommandTest extends CommandTest {

    @Test
    public void test() throws Exception {


        DAOFactory factory = DAOFactory.getMySQLDAOFactory();
        MedicalCard medicalCardByPatientId = factory.getMedicalCardDAO().getMedicalCardByPatientId(1);
        medicalCardByPatientId.setAppointments(factory.getAppointmentDAO().getAppointmentsByMedicalCardId(medicalCardByPatientId.getId()));


        Doctor doctor = new Doctor();
        Category category = new Category();
        category.setName("nurse");
        doctor.setCategory(category);

        List<Patient> allPatients = factory.getPatientDAO().getAllPatients();

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("medicalCard")).thenReturn(medicalCardByPatientId);
        when(session.getAttribute("user")).thenReturn(doctor);
        when(session.getAttribute("patients")).thenReturn(allPatients);

        assertNotNull(new GetAppointmentsCommand().execute(request, response));

        verify(session, atLeast(1)).getAttribute("medicalCard");
        verify(session, atLeast(1)).getAttribute("user");
        verify(session, atLeast(1)).getAttribute("patients");


    }


}