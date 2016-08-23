package ua.nure.lyubimtsev.SummaryTask4.db.dao.entitydao;

import ua.nure.lyubimtsev.SummaryTask4.db.entities.Doctor;
import ua.nure.lyubimtsev.SummaryTask4.db.entities.Patient;

import java.util.List;

public interface PatientDAO {

    List<Patient> getAllPatients();

    List<Patient> getPatientsByDoctorId(int doctorId);

    int insertPatient(Patient patient);

    int updatePatient(Patient patient);
}
