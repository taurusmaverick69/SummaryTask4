package ua.nure.lyubimtsev.summarytask4.db.dao.daoimpl;

import ua.nure.lyubimtsev.summarytask4.db.dao.MySQLDAOFactory;
import ua.nure.lyubimtsev.summarytask4.db.dao.entitydao.AdminDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdminDAOImpl implements AdminDAO {

    @Override
    public List<Admin> selectAdmins() {

        List<Admin> admins = new ArrayList<>();

        try (Connection connection = MySQLDAOFactory.createDataSource().getConnection();
             Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery("SELECT * FROM admin");

            while (resultSet.next()) {
                admins.add(
                        new Admin(
                                resultSet.getInt("id"),
                                resultSet.getString("login"),
                                resultSet.getString("password"),
                                resultSet.getString("name")
                        )
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return admins;
    }

    @Override
    public Admin getAdminByLoginAndPassword(String login, String password) {

        try (Connection connection = MySQLDAOFactory.createDataSource().getConnection();
             PreparedStatement prepareStatement = connection.prepareStatement("SELECT * FROM admin WHERE login = ? AND password = ?")) {

            prepareStatement.setString(1, login);
            prepareStatement.setString(2, password);

            ResultSet resultSet = prepareStatement.executeQuery();

            if (resultSet.next()) {

                Admin admin = new Admin(
                        resultSet.getInt("id"),
                        resultSet.getString("login"),
                        resultSet.getString("password"),
                        resultSet.getString("name")
                );


                PreparedStatement doctorPreparedStatement = connection.prepareStatement("SELECT *\n" +
                        "FROM doctor, category\n" +
                        "WHERE category_id = category.id \n" +
                        "AND admin_id = ?");

                doctorPreparedStatement.setInt(1, admin.getId());
                ResultSet doctorResultSet = doctorPreparedStatement.executeQuery();

                while (doctorResultSet.next()) {

                    Doctor doctor = new Doctor(
                            doctorResultSet.getInt("doctor.id"),
                            doctorResultSet.getString("doctor.login"),
                            doctorResultSet.getString("doctor.password"),
                            doctorResultSet.getString("doctor.name"),
                            new Category(doctorResultSet.getInt("category.id"), doctorResultSet.getString("category.name"))
                    );

                    PreparedStatement patientPreparedStatement = connection.prepareStatement("SELECT *\n" +
                            "FROM patient, doctor_patient, state\n" +
                            "WHERE patient.id = doctor_patient.patient_id \n" +
                            "AND patient.state_id = state.id \n" +
                            "AND doctor_patient.doctor_id = ?");

                    patientPreparedStatement.setInt(1, doctorResultSet.getInt("id"));
                    ResultSet patientResultSet = patientPreparedStatement.executeQuery();

                    while (patientResultSet.next()) {
                        doctor.getPatients().add(
                                new Patient(
                                        patientResultSet.getInt("id"),
                                        patientResultSet.getString("name"),
                                        patientResultSet.getString("address"),
                                        patientResultSet.getDate("birthDate"),
                                        new PatientState(patientResultSet.getInt("state.id"), patientResultSet.getString("state.name"))
                                ));
                    }

                    admin.getDoctors().add(doctor);
                }

                return admin;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }
}
