package ua.nure.lyubimtsev.SummaryTask4.db.dao.daoimpl;

import org.apache.log4j.Logger;
import ua.nure.lyubimtsev.SummaryTask4.db.Fields;
import ua.nure.lyubimtsev.SummaryTask4.db.dao.MySQLDAOFactory;
import ua.nure.lyubimtsev.SummaryTask4.db.dao.entitydao.AppointmentDAO;
import ua.nure.lyubimtsev.SummaryTask4.db.entities.Appointment;
import ua.nure.lyubimtsev.SummaryTask4.db.entities.Doctor;
import ua.nure.lyubimtsev.SummaryTask4.db.entities.Type;
import ua.nure.lyubimtsev.SummaryTask4.exception.DBException;
import ua.nure.lyubimtsev.SummaryTask4.exception.Messages;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AppointmentDAOImpl implements AppointmentDAO {

    private static final Logger LOG = Logger.getLogger(AppointmentDAOImpl.class);

    private static final String GET_APPOINTMENTS_BY_MEDICAL_CARD = "SELECT * FROM appointment, type, doctor WHERE appointment.type_id = type.id  AND appointment.doctor_id = doctor.id AND medicalCard_id = ?";
    private static final String SQL_INSERT_APPOINTMENT = "INSERT INTO appointment VALUES (DEFAULT,?,?,?,?,?,?)";
    private static final String SQL_UPDATE_APPOINTMENT = "UPDATE appointment SET diagnose = ?, type_id = ?, info = ? WHERE id = ?";

    /**
     * Returns Appointments by medicalCard id.
     *
     * @param medicalCardId medical card identifier
     * @return List of appointment entities.
     */
    @Override
    public List<Appointment> getAppointmentsByMedicalCardId(int medicalCardId) throws DBException {

        List<Appointment> appointments = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = MySQLDAOFactory.createConnection();
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);

            preparedStatement = connection.prepareStatement(GET_APPOINTMENTS_BY_MEDICAL_CARD);

            preparedStatement.setInt(1, medicalCardId);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                appointments.add(new Appointment(
                        resultSet.getInt(Fields.APPOINTMENT_ID),
                        resultSet.getString(Fields.APPOINTMENT_DIAGNOSE),
                        new Type(resultSet.getInt(Fields.TYPE_ID), resultSet.getString(Fields.TYPE_NAME)),
                        resultSet.getString(Fields.APPOINTMENT_INFO),
                        resultSet.getDate(Fields.APPOINTMENT_DATE),
                        Doctor.newBuilder().setId(resultSet.getInt(Fields.DOCTOR_ID)).setName(resultSet.getString(Fields.DOCTOR_NAME)).build(),
                        medicalCardId
                ));
            }

            connection.commit();
        } catch (SQLException e) {
            MySQLDAOFactory.rollback(connection);
            LOG.error(Messages.ERR_CANNOT_OBTAIN_APPOINTMENTS_BY_MEDICAL_CARD_ID, e);
            throw new DBException(Messages.ERR_CANNOT_OBTAIN_APPOINTMENTS_BY_MEDICAL_CARD_ID, e);
        } finally {
            MySQLDAOFactory.close(connection, preparedStatement, resultSet);
        }
        return appointments;
    }


    /**
     * Insert a new appointment.
     *
     * @param appointment to insert
     * @return affected rows.
     */
    @Override
    public int insertAppointment(Appointment appointment) throws DBException {

        int rows = 0;

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = MySQLDAOFactory.createConnection();
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);

            preparedStatement = connection.prepareStatement(SQL_INSERT_APPOINTMENT);

            preparedStatement.setString(1, appointment.getDiagnose());
            preparedStatement.setInt(2, appointment.getType().getId());
            preparedStatement.setString(3, appointment.getInfo());
            preparedStatement.setDate(4, new Date(appointment.getDate().getTime()));
            preparedStatement.setInt(5, appointment.getMedicalCardId());
            preparedStatement.setInt(6, appointment.getDoctor().getId());


            rows =  preparedStatement.executeUpdate();
            connection.commit();

        } catch (SQLException e) {

            MySQLDAOFactory.rollback(connection);
            LOG.error(Messages.ERR_CANNOT_INSERT_APPOINTMENT, e);
            throw new DBException(Messages.ERR_CANNOT_INSERT_APPOINTMENT, e);

        } finally {
            MySQLDAOFactory.close(connection);
            MySQLDAOFactory.close(preparedStatement);
        }
        return rows;
    }

    /**
     * Update appointment.
     *
     * @param appointment to update
     * @return affected rows.
     */
    @Override
    public int updateAppointment(Appointment appointment) throws DBException {

        int rows = 0;

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = MySQLDAOFactory.createConnection();
            preparedStatement = connection.prepareStatement(SQL_UPDATE_APPOINTMENT);

            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);

            preparedStatement.setString(1, appointment.getDiagnose());
            preparedStatement.setInt(2, appointment.getType().getId());
            preparedStatement.setString(3, appointment.getInfo());
            preparedStatement.setInt(4, appointment.getId());

            rows = preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            MySQLDAOFactory.rollback(connection);
            LOG.error(Messages.ERR_CANNOT_UPDATE_APPOINTMENT, e);
            throw new DBException(Messages.ERR_CANNOT_UPDATE_APPOINTMENT, e);
        } finally {
            MySQLDAOFactory.close(connection);
            MySQLDAOFactory.close(preparedStatement);
        }

        return rows;
    }
}
