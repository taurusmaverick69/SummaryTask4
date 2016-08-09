package ua.nure.lyubimtsev.SummaryTask4.db.dao.entitydao;


import ua.nure.lyubimtsev.SummaryTask4.db.entities.Admin;

public interface AdminDAO {

    Admin getAdminByLoginAndPassword(String login, String password);

}


