package ua.nure.lyubimtsev.SummaryTask4.db.dao;


import ua.nure.lyubimtsev.SummaryTask4.db.dao.entitydao.RoleDAO;
import ua.nure.lyubimtsev.SummaryTask4.db.dao.entitydao.UserDAO;

public abstract class DAOFactory {

    private static MySQLDAOFactory factory;

    public static DAOFactory getMySQLDAOFactory() {

        if (factory == null){
            factory = new MySQLDAOFactory();
        }
        return factory;
    }

    public abstract UserDAO getUserDAO();

    public abstract RoleDAO getRoleDAO();


}
