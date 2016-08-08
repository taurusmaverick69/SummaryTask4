package ua.nure.lyubimtsev.summarytask4.db.dao;

import ua.nure.lyubimtsev.summarytask4.db.dao.daoimpl.AdminDAOImpl;
import ua.nure.lyubimtsev.summarytask4.db.dao.daoimpl.CategoryDAOImpl;
import ua.nure.lyubimtsev.summarytask4.db.dao.daoimpl.DoctorDAOImpl;
import ua.nure.lyubimtsev.summarytask4.db.dao.entitydao.AdminDAO;
import ua.nure.lyubimtsev.summarytask4.db.dao.entitydao.CategoryDAO;
import ua.nure.lyubimtsev.summarytask4.db.dao.entitydao.DoctorDAO;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class MySQLDAOFactory extends DAOFactory {

    private static DataSource dataSource;

    public static DataSource createDataSource() {
        if (dataSource == null) {
            try {
                Context ctx = new InitialContext();
                dataSource = (DataSource) ctx.lookup("java:comp/env/jdbc/hospitalDB");
            } catch (NamingException e) {
                e.printStackTrace();
            }
        }
        return dataSource;

    }

    @Override
    public AdminDAO getAdminDAO() {
        return new AdminDAOImpl();
    }

    @Override
    public DoctorDAO getDoctorDAO() {
        return new DoctorDAOImpl();
    }

    @Override
    public CategoryDAO getCategoryDAO() {
        return new CategoryDAOImpl();
    }
}
