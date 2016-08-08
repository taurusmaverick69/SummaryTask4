package ua.nure.lyubimtsev.summarytask4.db.dao.entitydao;


import ua.nure.lyubimtsev.summarytask4.db.entities.Doctor;

public interface DoctorDAO {

    Doctor getDoctorByLoginAndPassword(String login, String password);

    Doctor getDoctorById(int id);

    int insertDoctor(Doctor doctor);

    int updateDoctor(Doctor doctor);


}


