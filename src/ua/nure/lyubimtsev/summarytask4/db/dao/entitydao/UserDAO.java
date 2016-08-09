package ua.nure.lyubimtsev.SummaryTask4.db.dao.entitydao;


import ua.nure.lyubimtsev.SummaryTask4.db.entities.User;

public interface UserDAO {

    User getUserByLoginAndPassword(String login, String password);

    int insertUser(User user);

    int updateUser(User user);



}


