package ua.nure.lyubimtsev.SummaryTask4.db.dao.daoimpl;

import ua.nure.lyubimtsev.SummaryTask4.db.dao.MySQLDAOFactory;
import ua.nure.lyubimtsev.SummaryTask4.db.dao.entitydao.PatientDAO;
import ua.nure.lyubimtsev.SummaryTask4.db.entities.MedicalCard;
import ua.nure.lyubimtsev.SummaryTask4.db.entities.Patient;
import ua.nure.lyubimtsev.SummaryTask4.db.entities.State;

import java.sql.*;
import java.sql.Date;
import java.util.*;

public class PatientDAOImpl implements PatientDAO {

    private static final String GET_PATIENTS_BY_DOCTOR_ID = "SELECT * FROM patient, patient_doctor, state WHERE patient.state_id = state.id AND patient.id = patient_doctor.patient_id AND doctor_id = ?";

    @Override
    public List<Patient> getAllPatients() {
        List<Patient> patients = new ArrayList<>();

        try (Connection connection = MySQLDAOFactory.createDataSource().getConnection();
             Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery("SELECT * FROM patient, state WHERE patient.state_id = state.id");

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
    public List<Patient> getPatientsByDoctorId(int doctorId) {

        List<Patient> patients = new ArrayList<>();

        try (Connection connection = MySQLDAOFactory.createDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_PATIENTS_BY_DOCTOR_ID)) {

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
    public int insertPatient(Patient patient) {

        int rows;

        try (Connection connection = MySQLDAOFactory.createDataSource().getConnection();
             PreparedStatement patientPreparedStatement = connection.prepareStatement("INSERT INTO patient VALUES (DEFAULT, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
             PreparedStatement patient_doctorPreparedStatement = connection.prepareStatement("INSERT INTO patient_doctor VALUES (?,?)")) {

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
    public int updatePatient(Patient patient) {


        try (Connection connection = MySQLDAOFactory.createDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("UPDATE patient SET name = ?, address = ?, birthDate = ?, state_id = ? WHERE id = ?")) {

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
    public List<Patient> getUnassignedPatients(int doctorId) {

        List<Patient> patients = new ArrayList<>();

        try (Connection connection = MySQLDAOFactory.createDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT *\n" +
                     "FROM patient, state\n" +
                     "WHERE  patient.state_id = state.id\n" +
                     "      AND patient.id NOT IN (SELECT patient_id FROM patient_doctor WHERE doctor_id = ?)")) {

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
    public int assignPatient(int patientId, int doctorId) {
        try (Connection connection = MySQLDAOFactory.createDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO patient_doctor VALUES (?,?)")) {

            preparedStatement.setInt(1, patientId);
            preparedStatement.setInt(2, doctorId);

            return preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }
}
