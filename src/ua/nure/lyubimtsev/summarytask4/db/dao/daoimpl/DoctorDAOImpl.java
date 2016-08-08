package ua.nure.lyubimtsev.summarytask4.db.dao.daoimpl;


import ua.nure.lyubimtsev.summarytask4.db.dao.DAOFactory;
import ua.nure.lyubimtsev.summarytask4.db.dao.MySQLDAOFactory;
import ua.nure.lyubimtsev.summarytask4.db.dao.entitydao.DoctorDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DoctorDAOImpl implements DoctorDAO {

    @Override
    public Doctor getDoctorByLoginAndPassword(String login, String password) {

        try (Connection connection = MySQLDAOFactory.createDataSource().getConnection();
             PreparedStatement prepareStatement = connection.prepareStatement("SELECT * FROM doctor, category\n" +
                     "WHERE doctor.category_id = category.id AND login = ? AND password = ?")) {

            prepareStatement.setString(1, login);
            prepareStatement.setString(2, password);

            ResultSet resultSet = prepareStatement.executeQuery();

            if (resultSet.next()) {

                Doctor doctor = new Doctor(
                        resultSet.getInt("id"),
                        resultSet.getString("login"),
                        resultSet.getString("password"),
                        resultSet.getString("name"),
                        new Category(resultSet.getInt("category.id"), resultSet.getString("category.name"))
                );


                PreparedStatement patientPreparedStatement = connection.prepareStatement("SELECT *" +
                        "FROM patient, doctor_patient, state\n" +
                        "WHERE patient.id = doctor_patient.patient_id\n" +
                        "AND state_id = state.id " +
                        "AND doctor_patient.doctor_id = ?");

                patientPreparedStatement.setInt(1, doctor.getId());
                ResultSet patientResultSet = patientPreparedStatement.executeQuery();

                while (patientResultSet.next()) {
                    doctor.getPatients().add(
                            new Patient(
                                    patientResultSet.getInt("id"),
                                    patientResultSet.getString("name"),
                                    patientResultSet.getString("address"),
                                    patientResultSet.getDate("birthDate"),
                                    new PatientState(patientResultSet.getInt("state.id"), patientResultSet.getString("state.name"))
                            )
                    );
                }

                return doctor;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

    @Override
    public Doctor getDoctorById(int id) {
//        try (Connection connection = MySQLDAOFactory.createDataSource().getConnection();
//             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM doctor WHERE id = ?")) {
//
//            preparedStatement.setInt(1, id);
//            ResultSet resultSet = preparedStatement.executeQuery();
//
//            if (resultSet.next()) {
//                return new Doctor(
//                        resultSet.getInt("id"),
//                        resultSet.getString("login"),
//                        resultSet.getString("password"),
//                        resultSet.getString("name"),
//                        resultSet.getString("category")
//                );
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

        return null;
    }

    @Override
    public int insertDoctor(Doctor doctor) {

        try (Connection connection = MySQLDAOFactory.createDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO doctor VALUES (DEFAULT, ?, ?, ?, ?, ?)")) {

            preparedStatement.setString(1, doctor.getLogin());
            preparedStatement.setString(2, doctor.getPassword());
            preparedStatement.setString(3, doctor.getName());
            preparedStatement.setInt(4, doctor.getCategory().getId());
            preparedStatement.setInt(5, doctor.getAdmin().getId());

            return preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    @Override
    public int updateDoctor(Doctor doctor) {
        try (Connection connection = MySQLDAOFactory.createDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("UPDATE doctor SET login = ?, password = ?,  name = ?, category_id = ? WHERE id = ?")) {

            preparedStatement.setString(1, doctor.getLogin());
            preparedStatement.setString(2, doctor.getPassword());
            preparedStatement.setString(3, doctor.getName());
            //     preparedStatement.setString(4, doctor.getCategory());
            preparedStatement.setInt(5, doctor.getId());

            return preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }


}
