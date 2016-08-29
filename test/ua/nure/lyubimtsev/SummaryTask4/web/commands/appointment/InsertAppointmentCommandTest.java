package ua.nure.lyubimtsev.SummaryTask4.web.commands.appointment;

import org.junit.Test;
import ua.nure.lyubimtsev.SummaryTask4.db.dao.DAOFactory;
import ua.nure.lyubimtsev.SummaryTask4.db.entities.*;
import ua.nure.lyubimtsev.SummaryTask4.web.commands.CommandTest;
import ua.nure.lyubimtsev.SummaryTask4.web.commands.LoginCommand;

import java.util.List;

import static org.junit.Assert.*;

public class InsertAppointmentCommandTest extends CommandTest {

    @Test
    public void test() throws Exception {


        DAOFactory factory = DAOFactory.getMySQLDAOFactory();
        MedicalCard medicalCardByPatientId = factory.getMedicalCardDAO().getMedicalCardByPatientId(1);
        medicalCardByPatientId.setAppointments(factory.getAppointmentDAO().getAppointmentsByMedicalCardId(medicalCardByPatientId.getId()));


        List<Type> allTypes = factory.getTypeDAO().getAllTypes();


        when(request.getSession()).thenReturn(session);
        when(request.getParameter("diagnose")).thenReturn("MyDiagnose");
        when(request.getParameter("type")).thenReturn("1");
        when(session.getAttribute("types")).thenReturn(allTypes);
        when(request.getParameter("info")).thenReturn("MyInfo");
        when(session.getAttribute("user")).thenReturn(new Doctor(1));
        when(session.getAttribute("medicalCard")).thenReturn(medicalCardByPatientId);

        assertNotNull(new InsertAppointmentCommand().execute(request, response));

        verify(request, atLeast(1)).getParameter("diagnose");
        verify(request, atLeast(1)).getParameter("type");
        verify(session, atLeast(1)).getAttribute("types");
        verify(request, atLeast(1)).getParameter("info");
        verify(session, atLeast(1)).getAttribute("user");
        verify(session, atLeast(1)).getAttribute("medicalCard");

    }


}