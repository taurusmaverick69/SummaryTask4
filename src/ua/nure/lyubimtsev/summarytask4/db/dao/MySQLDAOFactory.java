package ua.nure.lyubimtsev.SummaryTask4.db.dao;

import ua.nure.lyubimtsev.SummaryTask4.db.dao.daoimpl.AdminDAOImpl;
import ua.nure.lyubimtsev.SummaryTask4.db.dao.daoimpl.CategoryDAOImpl;
import ua.nure.lyubimtsev.SummaryTask4.db.dao.daoimpl.DoctorDAOImpl;
import ua.nure.lyubimtsev.SummaryTask4.db.dao.daoimpl.PatientDAOImpl;
import ua.nure.lyubimtsev.SummaryTask4.db.dao.entitydao.*;
import ua.nure.lyubimtsev.SummaryTask4.db.entities.Patient;

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
    public CategoryDAO getCategoryDAO() {
        return new CategoryDAOImpl();
    }

    @Override
    public AppointmentDAO getAppointmentDAO() {
        return null;
    }

    @Override
    public DoctorDAO getDoctorDAO() {
        return new DoctorDAOImpl();
    }

    @Override
    public MedicalCardDAO getMedicalCardDAO() {
        return null;
    }

    @Override
    public PatientDAO getPatientDAO() {
        return new PatientDAOImpl();
    }

    @Override
    public StateDAO getStateDAO() {
        return null;
    }

    @Override
    public TypeDAO getTypeDAO() {
        return null;
    }

}
