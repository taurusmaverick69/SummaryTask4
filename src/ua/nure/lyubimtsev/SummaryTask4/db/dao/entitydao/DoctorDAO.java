package ua.nure.lyubimtsev.SummaryTask4.db.dao.entitydao;

import ua.nure.lyubimtsev.SummaryTask4.db.entities.Doctor;

import java.util.List;

public interface DoctorDAO {

    List<Doctor> getDoctors();

    Doctor getDoctorByLoginAndPassword(String login, String password);

    int insertDoctor(Doctor doctor);

    boolean isLoginExists(String login);

}
