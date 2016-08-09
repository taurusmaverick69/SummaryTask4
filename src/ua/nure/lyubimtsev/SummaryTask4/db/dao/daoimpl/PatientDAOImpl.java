package ua.nure.lyubimtsev.SummaryTask4.db.dao.daoimpl;

import ua.nure.lyubimtsev.SummaryTask4.db.dao.MySQLDAOFactory;
import ua.nure.lyubimtsev.SummaryTask4.db.dao.entitydao.PatientDAO;
import ua.nure.lyubimtsev.SummaryTask4.db.entities.Doctor;
import ua.nure.lyubimtsev.SummaryTask4.db.entities.Patient;
import ua.nure.lyubimtsev.SummaryTask4.db.entities.PatientState;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

            while (resultSet.next()){
                patients.add(new Patient(
                        resultSet.getInt("patient.id"),
                        resultSet.getString("patient.name"),
                        resultSet.getString("patient.address"),
                        resultSet.getDate("patient.birthDate"),
                        new PatientState(resultSet.getInt("state.id"), resultSet.getString("state.name"))
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return patients;
    }
}
