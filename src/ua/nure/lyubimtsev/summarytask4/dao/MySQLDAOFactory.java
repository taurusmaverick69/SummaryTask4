package ua.nure.lyubimtsev.summarytask4.dao;

import ua.nure.lyubimtsev.summarytask4.dao.daoimpl.MySQLAdminDAO;
import ua.nure.lyubimtsev.summarytask4.dao.entitydao.AdminDAO;

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
        return new MySQLAdminDAO();
    }
}
