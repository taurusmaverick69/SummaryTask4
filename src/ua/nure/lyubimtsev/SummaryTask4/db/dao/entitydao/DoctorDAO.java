package ua.nure.lyubimtsev.SummaryTask4.db.dao.entitydao;

import ua.nure.lyubimtsev.SummaryTask4.Role;
import ua.nure.lyubimtsev.SummaryTask4.db.entities.Doctor;
import ua.nure.lyubimtsev.SummaryTask4.exception.DBException;

import java.util.List;

public interface DoctorDAO {

    List<Doctor> getAllDoctors() throws DBException;

    Doctor getDoctorByLoginAndPassword(String login, String password) throws DBException;

    int insertDoctor(Doctor doctor) throws DBException;

    int updateDoctor(Doctor doctor, Role role) throws DBException;

    boolean isLoginExists(String login) throws DBException;

}
