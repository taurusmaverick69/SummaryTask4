package ua.nure.lyubimtsev.summarytask4.dao.daoimpl;

import ua.nure.lyubimtsev.summarytask4.dao.MySQLDAOFactory;
import ua.nure.lyubimtsev.summarytask4.dao.entitydao.AdminDAO;
import ua.nure.lyubimtsev.summarytask4.entities.Admin;
import ua.nure.lyubimtsev.summarytask4.entities.Doctor;
import ua.nure.lyubimtsev.summarytask4.entities.Patient;

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


                PreparedStatement doctorPreparedStatement = connection.prepareStatement("SELECT * FROM doctor WHERE admin_id = ?");
                doctorPreparedStatement.setInt(1, admin.getId());
                ResultSet doctorResultSet = doctorPreparedStatement.executeQuery();

                while (doctorResultSet.next()) {
                    admin.getDoctors().add(
                            new Doctor(
                                    doctorResultSet.getInt("id"),
                                    doctorResultSet.getString("login"),
                                    doctorResultSet.getString("password"),
                                    doctorResultSet.getString("name"),
                                    doctorResultSet.getString("category")
                            )
                    );
                }


                PreparedStatement patientPreparedStatement = connection.prepareStatement("SELECT * FROM patient WHERE admin_id = ?");
                patientPreparedStatement.setInt(1, admin.getId());
                ResultSet patientResultSet = patientPreparedStatement.executeQuery();

                while (patientResultSet.next()) {
                    admin.getPatients().add(
                            new Patient(
                                    patientResultSet.getInt("id"),
                                    patientResultSet.getString("name"),
                                    patientResultSet.getString("address"),
                                    patientResultSet.getDate("birthDate"),
                                    patientResultSet.getString("state")
                            )
                    );
                }

                return admin;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }
}
