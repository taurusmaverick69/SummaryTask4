package ua.nure.lyubimtsev.SummaryTask4.db.dao;

import ua.nure.lyubimtsev.SummaryTask4.db.dao.entitydao.*;

/**
 * Class, that produces DAOs such as AdminDAO, CategoryDAO, AppointmentDAO and so forth.
 *
 * @author V.Lyubimtsev
 */
public abstract class DAOFactory {

    private static MySQLDAOFactory factory;

    /**
     * Class, that produces DAOs such as AdminDAO, CategoryDAO, AppointmentDAO and so forth.
     *
     * @return  implementation of DAOFactory - MySQLDAOFactory
     */
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
