package ua.nure.lyubimtsev.SummaryTask4.db.dao.entitydao;

import ua.nure.lyubimtsev.SummaryTask4.db.entities.Appointment;
import ua.nure.lyubimtsev.SummaryTask4.exception.DBException;

import java.util.List;

public interface AppointmentDAO {

    List<Appointment> getAppointmentsByMedicalCardId(int medicalCardId) throws DBException;

    int insertAppointment(Appointment appointment) throws DBException;

    int updateAppointment(Appointment appointment) throws DBException;
}
