package ua.nure.lyubimtsev.SummaryTask4.db.dao.entitydao;

import ua.nure.lyubimtsev.SummaryTask4.db.entities.Appointment;

import java.util.List;

public interface AppointmentDAO {

    List<Appointment> getAppointmentsByMedicalCardId(int medicalCardId);

    int insertAppointment(Appointment appointment);

}
