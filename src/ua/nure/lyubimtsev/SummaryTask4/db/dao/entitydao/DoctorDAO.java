package ua.nure.lyubimtsev.SummaryTask4.db.dao.entitydao;

import ua.nure.lyubimtsev.SummaryTask4.Role;
import ua.nure.lyubimtsev.SummaryTask4.db.entities.Doctor;

import java.util.List;

public interface DoctorDAO {

    List<Doctor> getAllDoctors();

    Doctor getDoctorByLoginAndPassword(String login, String password);

    int insertDoctor(Doctor doctor);

    int updateDoctor(Doctor doctor, Role role);

    boolean isLoginExists(String login);

}
