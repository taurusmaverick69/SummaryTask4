package ua.nure.lyubimtsev.summarytask4.db.dao.entitydao;

import ua.nure.lyubimtsev.summarytask4.db.entities.Admin;
import ua.nure.lyubimtsev.summarytask4.db.entities.Patient;

import java.util.List;

public interface AdminDAO {

    public List<Admin> selectAdmins();

    Admin getAdminByLoginAndPassword(String login, String password);

}
