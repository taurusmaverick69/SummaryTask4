package ua.nure.lyubimtsev.summarytask4.dao;


import ua.nure.lyubimtsev.summarytask4.dao.entitydao.AdminDAO;
import ua.nure.lyubimtsev.summarytask4.dao.entitydao.CategoryDAO;
import ua.nure.lyubimtsev.summarytask4.dao.entitydao.DoctorDAO;

public abstract class DAOFactory {

    private static MySQLDAOFactory factory;

    public static DAOFactory getMySQLDAOFactory() {

        if (factory == null){
            factory = new MySQLDAOFactory();
        }
        return factory;
    }

    public abstract AdminDAO getAdminDAO();

    public abstract DoctorDAO getDoctorDAO();

    public abstract CategoryDAO getCategoryDAO();


}
