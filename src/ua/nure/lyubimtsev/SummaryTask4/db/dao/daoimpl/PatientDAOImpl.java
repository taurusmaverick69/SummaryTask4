package ua.nure.lyubimtsev.SummaryTask4.db.dao.daoimpl;

import ua.nure.lyubimtsev.SummaryTask4.db.dao.MySQLDAOFactory;
import ua.nure.lyubimtsev.SummaryTask4.db.dao.entitydao.PatientDAO;
import ua.nure.lyubimtsev.SummaryTask4.db.entities.Patient;
import ua.nure.lyubimtsev.SummaryTask4.db.entities.State;
import ua.nure.lyubimtsev.SummaryTask4.exception.DBException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PatientDAOImpl implements PatientDAO {

    private static final String SQL_GET_PATIENTS_BY_DOCTOR_ID = "SELECT * FROM patient, patient_doctor, state WHERE patient.state_id = state.id AND patient.id = patient_doctor.patient_id AND doctor_id = ?";
    private static final String SQL_GET_ALL_PATIENTS = "SELECT * FROM patient, state WHERE patient.state_id = state.id";
    private static final String SQL_INSERT_PATIENT = "INSERT INTO patient VALUES (DEFAULT, ?, ?, ?, ?)";
    private static final String SQL_ASSIGN_PATIENT_TO_DOCTOR = "INSERT INTO patient_doctor VALUES (?,?)";
    private static final String UPDATE_PATIENT = "UPDATE patient SET name = ?, address = ?, birthDate = ?, state_id = ? WHERE id = ?";
    private static final String GET_UNASSIGNED_PATIENTS = "SELECT *FROM patient, state WHERE  patient.state_id = state.id AND patient.id NOT IN (SELECT patient_id FROM patient_doctor WHERE doctor_id = ?)";

    @Override
    public List<Patient> getAllPatients() throws DBException {
        List<Patient> patients = new ArrayList<>();

        try (Connection connection = MySQLDAOFactory.createConnection();
             Statement statement = connection.createStatement()) {


            ResultSet resultSet = statement.executeQuery(SQL_GET_ALL_PATIENTS);

            while (resultSet.next()) {
                patients.add(new Patient(
                        resultSet.getInt("patient.id"),
                        resultSet.getString("patient.name"),
                        resultSet.getString("patient.address"),
                        resultSet.getDate("patient.birthDate"),
                        new State(resultSet.getInt("state.id"), resultSet.getString("state.name"))
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return patients;
    }

    @Override
    public List<Patient> getPatientsByDoctorId(int doctorId) throws DBException {

        List<Patient> patients = new ArrayList<>();

        try (Connection connection = MySQLDAOFactory.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_PATIENTS_BY_DOCTOR_ID)) {

            preparedStatement.setInt(1, doctorId);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                patients.add(new Patient(
                        resultSet.getInt("patient.id"),
                        resultSet.getString("patient.name"),
                        resultSet.getString("patient.address"),
                        resultSet.getDate("patient.birthDate"),
                        new State(resultSet.getInt("state.id"), resultSet.getString("state.name"))
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return patients;
    }

    @Override
    public int insertPatient(Patient patient) throws DBException {

        int rows;


        try (Connection connection = MySQLDAOFactory.createConnection();
             PreparedStatement patientPreparedStatement = connection.prepareStatement(SQL_INSERT_PATIENT, Statement.RETURN_GENERATED_KEYS);
             PreparedStatement patient_doctorPreparedStatement = connection.prepareStatement(SQL_ASSIGN_PATIENT_TO_DOCTOR)) {

            patientPreparedStatement.setString(1, patient.getName());
            patientPreparedStatement.setString(2, patient.getAddress());
            patientPreparedStatement.setDate(3, new Date(patient.getBirthDate().getTime()));
            patientPreparedStatement.setInt(4, patient.getState().getId());

            int patientId = 0;
            rows = patientPreparedStatement.executeUpdate();
            if (rows != 0) {
                ResultSet generatedKeys = patientPreparedStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    patientId = generatedKeys.getInt(1);
                    patient.setId(patientId);
                }
            }

            patient_doctorPreparedStatement.setInt(1, patientId);
            patient_doctorPreparedStatement.setInt(2, patient.getDoctorId());

            return patient_doctorPreparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int updatePatient(Patient patient) throws DBException {


        try (Connection connection = MySQLDAOFactory.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PATIENT)) {

            preparedStatement.setString(1, patient.getName());
            preparedStatement.setString(2, patient.getAddress());
            preparedStatement.setDate(3, new Date(patient.getBirthDate().getTime()));
            preparedStatement.setInt(4, patient.getState().getId());
            preparedStatement.setInt(5, patient.getId());

            return preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    @Override
    public List<Patient> getUnassignedPatients(int doctorId) throws DBException {

        List<Patient> patients = new ArrayList<>();

        try (Connection connection = MySQLDAOFactory.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_UNASSIGNED_PATIENTS)) {

            preparedStatement.setInt(1, doctorId);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                patients.add(new Patient(
                        resultSet.getInt("patient.id"),
                        resultSet.getString("patient.name"),
                        resultSet.getString("patient.address"),
                        resultSet.getDate("patient.birthDate"),
                        new State(resultSet.getInt("state.id"), resultSet.getString("state.name"))
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return patients;
    }

    @Override
    public int assignPatient(int patientId, int doctorId) throws DBException {
        try (Connection connection = MySQLDAOFactory.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_ASSIGN_PATIENT_TO_DOCTOR)) {

            preparedStatement.setInt(1, patientId);
            preparedStatement.setInt(2, doctorId);

            return preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }
}
