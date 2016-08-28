package ua.nure.lyubimtsev.SummaryTask4.db.dao.daoimpl;

import org.apache.log4j.Logger;
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

    @Override
    public List<Appointment> getAppointmentsByMedicalCardId(int medicalCardId) throws DBException {

        List<Appointment> appointments = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = MySQLDAOFactory.createConnection();
            preparedStatement = connection.prepareStatement(GET_APPOINTMENTS_BY_MEDICAL_CARD);

            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);

            preparedStatement.setInt(1, medicalCardId);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                appointments.add(new Appointment(
                        resultSet.getInt("appointment.id"),
                        resultSet.getString("appointment.diagnose"),
                        new Type(resultSet.getInt("type.id"), resultSet.getString("type.name")),
                        resultSet.getString("appointment.info"),
                        resultSet.getDate("appointment.date"),
                        Doctor.newBuilder().setId(resultSet.getInt("doctor.id")).setName(resultSet.getString("doctor.name")).build(),
                        medicalCardId
                ));
            }

            connection.commit();
        } catch (SQLException e) {
            MySQLDAOFactory.rollback(connection);
            LOG.error(Messages.ERR_CANNOT_OBTAIN_APPOINTMENTS, e);
            throw new DBException(Messages.ERR_CANNOT_OBTAIN_APPOINTMENTS, e);
        } finally {
            MySQLDAOFactory.close(connection, preparedStatement, resultSet);
        }
        return appointments;
    }

    @Override
    public int insertAppointment(Appointment appointment) throws DBException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = MySQLDAOFactory.createConnection();
            preparedStatement = connection.prepareStatement(SQL_INSERT_APPOINTMENT);

            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);

            preparedStatement.setString(1, appointment.getDiagnose());
            preparedStatement.setInt(2, appointment.getType().getId());
            preparedStatement.setString(3, appointment.getInfo());
            preparedStatement.setDate(4, new Date(appointment.getDate().getTime()));
            preparedStatement.setInt(5, appointment.getMedicalCardId());
            preparedStatement.setInt(6, appointment.getDoctor().getId());

            connection.commit();
            return preparedStatement.executeUpdate();

        } catch (SQLException e) {

            MySQLDAOFactory.rollback(connection);
            LOG.error(Messages.ERR_CANNOT_INSERT_APPOINTMENT, e);
            throw new DBException(Messages.ERR_CANNOT_INSERT_APPOINTMENT, e);

        } finally {
            MySQLDAOFactory.close(connection);
            MySQLDAOFactory.close(preparedStatement);
        }
    }

    @Override
    public int updateAppointment(Appointment appointment) throws DBException {

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

            connection.commit();
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            MySQLDAOFactory.rollback(connection);
            LOG.error(Messages.ERR_CANNOT_UPDATE_APPOINTMENT, e);
            throw new DBException(Messages.ERR_CANNOT_UPDATE_APPOINTMENT, e);
        } finally {
            MySQLDAOFactory.close(connection);
            MySQLDAOFactory.close(preparedStatement);
        }

    }
}
