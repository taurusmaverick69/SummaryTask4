package ua.nure.lyubimtsev.SummaryTask4.db.dao.entitydao;

import ua.nure.lyubimtsev.SummaryTask4.db.entities.Patient;
import ua.nure.lyubimtsev.SummaryTask4.exception.DBException;

import java.util.List;

public interface PatientDAO {

    List<Patient> getAllPatients() throws DBException;

    List<Patient> getPatientsByDoctorId(int doctorId) throws DBException;

    int insertPatient(Patient patient) throws DBException;

    int updatePatient(Patient patient) throws DBException;

    List<Patient> getUnassignedPatients(int doctorId) throws DBException;

    int assignPatient(int patientId, int doctorId) throws DBException;

}
