package ua.nure.lyubimtsev.SummaryTask4.db.dao;

import ua.nure.lyubimtsev.SummaryTask4.db.dao.daoimpl.RoleDAOImpl;
import ua.nure.lyubimtsev.SummaryTask4.db.dao.daoimpl.UserDAOImpl;
import ua.nure.lyubimtsev.SummaryTask4.db.dao.entitydao.RoleDAO;
import ua.nure.lyubimtsev.SummaryTask4.db.dao.entitydao.UserDAO;

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
    public UserDAO getUserDAO() {return new UserDAOImpl();
    }

    @Override
    public RoleDAO getRoleDAO() {
        return new RoleDAOImpl();
    }
}
