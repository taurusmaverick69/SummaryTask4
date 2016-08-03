package ua.nure.lyubimtsev.summarytask4.dao;


import ua.nure.lyubimtsev.summarytask4.dao.entitydao.AdminDAO;

public abstract class DAOFactory {

    public static DAOFactory getMySQLDAOFactory() {
        return new MySQLDAOFactory();
    }

    public abstract AdminDAO getAdminDAO();


}
