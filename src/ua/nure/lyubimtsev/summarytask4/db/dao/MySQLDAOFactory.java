package ua.nure.lyubimtsev.SummaryTask4.db.dao;

import com.mysql.fabric.jdbc.FabricMySQLDriver;
import ua.nure.lyubimtsev.SummaryTask4.db.dao.daoimpl.*;
import ua.nure.lyubimtsev.SummaryTask4.db.dao.entitydao.*;
import ua.nure.lyubimtsev.SummaryTask4.db.entities.Patient;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Hashtable;

public class MySQLDAOFactory extends DAOFactory {



    private static Connection connection;
    private static final String URL = "jdbc:mysql://localhost:3306/hospitaldb?autoReconnect=true&useSSL=false";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    public static Connection createConnection() {

        try {
            if (connection == null || connection.isClosed()) {
                try {
                    Context ctx = new InitialContext();
                    DataSource dataSource = (DataSource) ctx.lookup("java:comp/env/jdbc/hospitalDB");
                    connection = dataSource.getConnection();
                } catch (NamingException e) {
                    DriverManager.registerDriver(new FabricMySQLDriver());
                    connection = DriverManager.getConnection(URL, USER, PASSWORD);
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;


    }

    @Override
    public AdminDAO getAdminDAO() {
        return new AdminDAOImpl();
    }

    @Override
    public CategoryDAO getCategoryDAO() {
        return new CategoryDAOImpl();
    }

    @Override
    public AppointmentDAO getAppointmentDAO() {
        return new AppointmentDAOImpl();
    }

    @Override
    public DoctorDAO getDoctorDAO() {
        return new DoctorDAOImpl();
    }

    @Override
    public MedicalCardDAO getMedicalCardDAO() {
        return new MedicalCardDAOImpl();
    }

    @Override
    public PatientDAO getPatientDAO() {
        return new PatientDAOImpl();
    }

    @Override
    public StateDAO getStateDAO() {
        return new StateDAOImpl();
    }

    @Override
    public TypeDAO getTypeDAO() {
        return new TypeDAOImpl();
    }

}
