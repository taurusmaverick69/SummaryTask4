package ua.nure.lyubimtsev.SummaryTask4.db.dao.entitydao;

import ua.nure.lyubimtsev.SummaryTask4.db.entities.Doctor;
import ua.nure.lyubimtsev.SummaryTask4.db.entities.Patient;

import java.util.List;

public interface PatientDAO {

    List<Patient> getAllPatients();

    List<Patient> getPatientsByDoctorId(int doctorId);

    int insertPatientAndMedicalCard(Patient patient);

    int updatePatient(Patient patient);

    List<Patient> getUnassignedPatients(int doctorId);

    int assignPatient(int patientId, int doctorId);

}
