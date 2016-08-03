package ua.nure.lyubimtsev.summarytask4.dao.entitydao;

import ua.nure.lyubimtsev.summarytask4.entities.Admin;
import ua.nure.lyubimtsev.summarytask4.entities.Patient;

import java.util.List;

public interface AdminDAO {

    List<Admin> selectAdmins();

    Admin getAdminByLoginAndPassword(String login, String password);

}
