package ua.nure.lyubimtsev.SummaryTask4.web.commands.appointment;

import org.junit.Test;
import ua.nure.lyubimtsev.SummaryTask4.db.dao.DAOFactory;
import ua.nure.lyubimtsev.SummaryTask4.db.entities.MedicalCard;
import ua.nure.lyubimtsev.SummaryTask4.web.commands.CommandTest;
import ua.nure.lyubimtsev.SummaryTask4.web.commands.LoginCommand;

import java.util.Date;

import static org.junit.Assert.*;

public class GetAppointmentOnUpdateCommandTest extends CommandTest {

    @Test
    public void test() throws Exception {

        DAOFactory factory = DAOFactory.getMySQLDAOFactory();
        MedicalCard medicalCardByPatientId = factory.getMedicalCardDAO().getMedicalCardByPatientId(1);
        medicalCardByPatientId.setAppointments(factory.getAppointmentDAO().getAppointmentsByMedicalCardId(medicalCardByPatientId.getId()));

        when(request.getSession()).thenReturn(session);
        when(request.getParameter("id")).thenReturn("17");
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("medicalCard")).thenReturn(medicalCardByPatientId);

        assertNotNull(new GetAppointmentOnUpdateCommand().execute(request, response));

        verify(request, atLeast(1)).getParameter("id");
        verify(session, atLeast(1)).getAttribute("medicalCard");

    }


}