package ua.nure.lyubimtsev.summarytask4.dao.entitydao;


import ua.nure.lyubimtsev.summarytask4.entities.Doctor;

import java.util.List;

public interface DoctorDAO {

    Doctor getDoctorByLoginAndPassword(String login, String password);

    Doctor getDoctorsById(int id);

    int insertDoctor(Doctor doctor);

    int updateDoctor(Doctor doctor);


}


