package ua.nure.lyubimtsev.SummaryTask4.db.dao.entitydao;


import ua.nure.lyubimtsev.SummaryTask4.db.entities.Admin;
import ua.nure.lyubimtsev.SummaryTask4.exception.DBException;

public interface AdminDAO {

    Admin getAdminByLoginAndPassword(String login, String password) throws DBException;

}


