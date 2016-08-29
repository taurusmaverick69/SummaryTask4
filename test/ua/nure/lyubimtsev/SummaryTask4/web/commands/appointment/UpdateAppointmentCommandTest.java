package ua.nure.lyubimtsev.SummaryTask4.web.commands.appointment;

import org.junit.Test;
import ua.nure.lyubimtsev.SummaryTask4.db.dao.DAOFactory;
import ua.nure.lyubimtsev.SummaryTask4.db.entities.Doctor;
import ua.nure.lyubimtsev.SummaryTask4.db.entities.MedicalCard;
import ua.nure.lyubimtsev.SummaryTask4.db.entities.Type;
import ua.nure.lyubimtsev.SummaryTask4.web.commands.CommandTest;
import ua.nure.lyubimtsev.SummaryTask4.web.commands.LoginCommand;

import java.util.List;

import static org.junit.Assert.*;

public class UpdateAppointmentCommandTest extends CommandTest {

    @Test
    public void test() throws Exception {


        DAOFactory factory = DAOFactory.getMySQLDAOFactory();
        MedicalCard medicalCardByPatientId = factory.getMedicalCardDAO().getMedicalCardByPatientId(1);
        medicalCardByPatientId.setAppointments(factory.getAppointmentDAO().getAppointmentsByMedicalCardId(medicalCardByPatientId.getId()));

        List<Type> allTypes = factory.getTypeDAO().getAllTypes();

        when(request.getSession()).thenReturn(session);
        when(request.getParameter("appointmentId")).thenReturn("17");
        when(request.getParameter("diagnose")).thenReturn("MyDiagnose");
        when(request.getParameter("type")).thenReturn("1");
        when(session.getAttribute("types")).thenReturn(allTypes);
        when(request.getParameter("info")).thenReturn("MyInfo");
        when(session.getAttribute("medicalCard")).thenReturn(medicalCardByPatientId);

        assertNotNull(new UpdateAppointmentCommand().execute(request, response));

        verify(request, atLeast(1)).getParameter("appointmentId");
        verify(request, atLeast(1)).getParameter("diagnose");
        verify(request, atLeast(1)).getParameter("type");
        verify(session, atLeast(1)).getAttribute("types");
        verify(request, atLeast(1)).getParameter("info");
        verify(session, atLeast(1)).getAttribute("medicalCard");



    }


}