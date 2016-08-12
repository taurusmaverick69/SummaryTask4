package ua.nure.lyubimtsev.SummaryTask4.db.dao.daoimpl;

import ua.nure.lyubimtsev.SummaryTask4.db.dao.MySQLDAOFactory;
import ua.nure.lyubimtsev.SummaryTask4.db.dao.entitydao.PatientDAO;
import ua.nure.lyubimtsev.SummaryTask4.db.entities.Doctor;
import ua.nure.lyubimtsev.SummaryTask4.db.entities.Patient;
import ua.nure.lyubimtsev.SummaryTask4.db.entities.State;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PatientDAOImpl implements PatientDAO {

    private static final String GET_PATIENTS_BY_DOCTOR = "SELECT * FROM patient, patient_doctor, state WHERE patient.state_id = state.id AND patient.id = patient_doctor.patient_id AND doctor_id = ?";

    @Override
    public List<Patient> getPatientsByDoctor(Doctor doctor) {

        List<Patient> patients = new ArrayList<>();

        try (Connection connection = MySQLDAOFactory.createDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_PATIENTS_BY_DOCTOR)) {

            preparedStatement.setInt(1, doctor.getId());

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
        try (Connection connection = MySQLDAOFactory.createDataSource().getConnection();
             PreparedStatement patientPreparedStatement = connection.prepareStatement("INSERT INTO patient VALUES (DEFAULT, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
             PreparedStatement patient_doctorPreparedStatement = connection.prepareStatement("INSERT INTO patient_doctor VALUES (?,?)")) {

            patientPreparedStatement.setString(1, patient.getName());
            patientPreparedStatement.setString(2, patient.getAddress());
            patientPreparedStatement.setDate(3, new Date(patient.getBirthDate().getTime()));
            patientPreparedStatement.setInt(4, patient.getState().getId());


            patientPreparedStatement.executeUpdate();

            int patientId = 0;
            ResultSet generatedKeys = patientPreparedStatement.getGeneratedKeys();
            if (generatedKeys.next()){
                patientId = generatedKeys.getInt(1);
            }

            patient_doctorPreparedStatement.setInt(1, patientId);
            patient_doctorPreparedStatement.setInt(2, patient.getDoctor_id());

            return patient_doctorPreparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
