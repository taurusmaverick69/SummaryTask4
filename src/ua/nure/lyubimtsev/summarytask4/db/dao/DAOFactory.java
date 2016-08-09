package ua.nure.lyubimtsev.SummaryTask4.db.dao;

import ua.nure.lyubimtsev.SummaryTask4.db.dao.entitydao.*;
import ua.nure.lyubimtsev.SummaryTask4.db.entities.Patient;

public abstract class DAOFactory {

    private static MySQLDAOFactory factory;

    public static DAOFactory getMySQLDAOFactory() {

        if (factory == null){
            factory = new MySQLDAOFactory();
        }
        return factory;
    }

    public abstract AdminDAO getAdminDAO();

    public abstract CategoryDAO getCategoryDAO();

    public abstract AppointmentDAO getAppointmentDAO();

    public abstract DoctorDAO getDoctorDAO();

    public abstract MedicalCardDAO getMedicalCardDAO();

    public abstract PatientDAO getPatientDAO();

    public abstract StateDAO getStateDAO();

    public abstract TypeDAO getTypeDAO();

}
