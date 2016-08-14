package ua.nure.lyubimtsev.SummaryTask4.db.dao.entitydao;

import ua.nure.lyubimtsev.SummaryTask4.db.entities.Doctor;
import ua.nure.lyubimtsev.SummaryTask4.db.entities.Patient;

import java.util.List;

public interface PatientDAO {

    List<Patient> getAllPatients();

    List<Patient> getPatientsByDoctor(Doctor doctor);

    int insertPatient(Patient patient);

}
