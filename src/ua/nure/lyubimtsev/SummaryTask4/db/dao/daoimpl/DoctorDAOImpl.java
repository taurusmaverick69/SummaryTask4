package ua.nure.lyubimtsev.SummaryTask4.db.dao.daoimpl;

import org.apache.log4j.Logger;
import ua.nure.lyubimtsev.SummaryTask4.Role;
import ua.nure.lyubimtsev.SummaryTask4.db.Fields;
import ua.nure.lyubimtsev.SummaryTask4.db.dao.MySQLDAOFactory;
import ua.nure.lyubimtsev.SummaryTask4.db.dao.entitydao.DoctorDAO;
import ua.nure.lyubimtsev.SummaryTask4.db.entities.Category;
import ua.nure.lyubimtsev.SummaryTask4.db.entities.Doctor;
import ua.nure.lyubimtsev.SummaryTask4.exception.DBException;
import ua.nure.lyubimtsev.SummaryTask4.exception.Messages;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class DoctorDAOImpl implements DoctorDAO {

    private static final Logger LOG = Logger.getLogger(AppointmentDAOImpl.class);

    private static final String SQL_GET_DOCTOR_BY_LOGIN_AND_PASSWORD = "SELECT * FROM doctor, category WHERE doctor.category_id = category.id AND login = ? AND password = ?";
    private static final String SQL_GET_ALL_DOCTORS = "SELECT * FROM doctor, category WHERE doctor.category_id = category.id";
    private static final String SQL_INSERT_DOCTOR = "INSERT INTO doctor VALUES (DEFAULT,?,?,?,?,?)";
    private static final String SQL_ADMIN_UPDATE_DOCTOR = "UPDATE doctor SET name = ?, category_id = ? WHERE id = ?";
    private static final String SQL_DOCTOR_UPDATE_DOCTOR = "UPDATE doctor SET login = ?, password = ?, name = ?, category_id = ? WHERE id = ?";


    @Override
    public List<Doctor> getAllDoctors() throws DBException {

        List<Doctor> doctors = new ArrayList<>();

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {

            connection = MySQLDAOFactory.createConnection();
            statement = connection.createStatement();

            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);

            resultSet = statement.executeQuery(SQL_GET_ALL_DOCTORS);

            while (resultSet.next()) {
                doctors.add(
                        new Doctor(
                                resultSet.getInt(Fields.DOCTOR_ID),
                                resultSet.getString(Fields.DOCTOR_LOGIN),
                                resultSet.getString(Fields.DOCTOR_PASSWORD),
                                resultSet.getString(Fields.DOCTOR_NAME),
                                new Category(resultSet.getInt(Fields.CATEGORY_ID), resultSet.getString(Fields.CATEGORY_NAME))
                        )
                );
            }
            connection.commit();

        } catch (SQLException e) {
            MySQLDAOFactory.rollback(connection);
            LOG.error(Messages.ERR_CANNOT_OBTAIN_DOCTORS, e);
            throw new DBException(Messages.ERR_CANNOT_OBTAIN_DOCTORS, e);
        } finally {
            MySQLDAOFactory.close(connection, statement, resultSet);
        }

        return doctors;
    }

    @Override
    public Doctor getDoctorByLoginAndPassword(String login, String password) throws DBException {

        Doctor doctor = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = MySQLDAOFactory.createConnection();
            preparedStatement = connection.prepareStatement(SQL_GET_DOCTOR_BY_LOGIN_AND_PASSWORD, Statement.RETURN_GENERATED_KEYS);

            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);

            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                doctor = new Doctor(
                        resultSet.getInt(Fields.DOCTOR_ID),
                        resultSet.getString(Fields.DOCTOR_LOGIN),
                        resultSet.getString(Fields.DOCTOR_PASSWORD),
                        resultSet.getString(Fields.DOCTOR_NAME),
                        new Category(resultSet.getInt(Fields.CATEGORY_ID), resultSet.getString(Fields.CATEGORY_NAME))
                );
            }
            connection.commit();

        } catch (SQLException e) {
            MySQLDAOFactory.rollback(connection);
            LOG.error(Messages.ERR_CANNOT_OBTAIN_DOCTOR_BY_LOGIN_AND_PASSWORD, e);
            throw new DBException(Messages.ERR_CANNOT_OBTAIN_DOCTOR_BY_LOGIN_AND_PASSWORD, e);
        } finally {
            MySQLDAOFactory.close(connection, preparedStatement, resultSet);
        }

        return doctor;
    }

    @Override
    public int insertDoctor(Doctor doctor) throws DBException {

        int rows = 0;

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = MySQLDAOFactory.createConnection();
            preparedStatement = connection.prepareStatement(SQL_INSERT_DOCTOR, Statement.RETURN_GENERATED_KEYS);

            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);

            preparedStatement.setString(1, doctor.getLogin());
            preparedStatement.setString(2, doctor.getPassword());
            preparedStatement.setString(3, doctor.getName());
            preparedStatement.setInt(4, doctor.getAdmin_id());
            preparedStatement.setInt(5, doctor.getCategory().getId());

            rows = preparedStatement.executeUpdate();
            if (rows != 0) {
                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    doctor.setId(generatedKeys.getInt(1));
                }
            }
            connection.commit();

        } catch (SQLException e) {
            MySQLDAOFactory.rollback(connection);
            LOG.error(Messages.ERR_CANNOT_INSERT_DOCTOR, e);
            throw new DBException(Messages.ERR_CANNOT_INSERT_DOCTOR, e);
        } finally {
            MySQLDAOFactory.close(connection);
            MySQLDAOFactory.close(preparedStatement);
        }


        return rows;
    }

    @Override
    public int updateDoctor(Doctor doctor, Role role) throws DBException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = MySQLDAOFactory.createConnection();
            switch (role) {
                case ADMIN:
                    preparedStatement = connection.prepareStatement(SQL_ADMIN_UPDATE_DOCTOR);
                    preparedStatement.setString(1, doctor.getName());
                    preparedStatement.setInt(2, doctor.getCategory().getId());
                    preparedStatement.setInt(3, doctor.getId());
                    break;

                case DOCTOR:

                    preparedStatement = connection.prepareStatement(SQL_DOCTOR_UPDATE_DOCTOR);
                    preparedStatement.setString(1, doctor.getLogin());
                    preparedStatement.setString(2, doctor.getPassword());
                    preparedStatement.setString(3, doctor.getName());
                    preparedStatement.setInt(4, doctor.getCategory().getId());
                    preparedStatement.setInt(5, doctor.getId());
                    break;
                default:
                    return 0;
            }

            return preparedStatement.executeUpdate();

        } catch (SQLException e) {
            MySQLDAOFactory.rollback(connection);
            LOG.error(Messages.ERR_CANNOT_UPDATE_DOCTOR, e);
            throw new DBException(Messages.ERR_CANNOT_UPDATE_DOCTOR, e);
        } finally {
            MySQLDAOFactory.close(connection);
            MySQLDAOFactory.close(preparedStatement);
        }
    }

    @Override
    public boolean isLoginExists(String login) throws DBException {
        try (Connection connection = MySQLDAOFactory.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT id FROM doctor WHERE login = ?")) {

            preparedStatement.setString(1, login);

            return preparedStatement.executeQuery().next();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
