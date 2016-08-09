package ua.nure.lyubimtsev.SummaryTask4.db.dao.daoimpl;

import ua.nure.lyubimtsev.SummaryTask4.db.dao.MySQLDAOFactory;
import ua.nure.lyubimtsev.SummaryTask4.db.dao.entitydao.DoctorDAO;
import ua.nure.lyubimtsev.SummaryTask4.db.entities.Admin;
import ua.nure.lyubimtsev.SummaryTask4.db.entities.Category;
import ua.nure.lyubimtsev.SummaryTask4.db.entities.Doctor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DoctorDAOImpl implements DoctorDAO {


    private static final String GET_DOCTOR_BY_LOGIN_AND_PASSWORD = "SELECT * FROM doctor, category WHERE doctor.category_id = category.id AND login = ? AND password = ?";

    @Override
    public List<Doctor> getDoctors() {

        List<Doctor> doctors = new ArrayList<>();

        try (Connection connection = MySQLDAOFactory.createDataSource().getConnection();
             Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery("SELECT * FROM doctor, category WHERE doctor.category_id = category.id");

            while (resultSet.next()) {
                doctors.add(
                        new Doctor(
                                resultSet.getInt("doctor.id"),
                                resultSet.getString("doctor.login"),
                                resultSet.getString("doctor.password"),
                                resultSet.getString("doctor.name"),
                                new Category(resultSet.getInt("category.id"), resultSet.getString("category.name"))
                        )
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return doctors;
    }

    @Override
    public Doctor getDoctorByLoginAndPassword(String login, String password) {

        Doctor doctor = null;

        try (Connection connection = MySQLDAOFactory.createDataSource().getConnection();
             PreparedStatement prepareStatement = connection.prepareStatement(GET_DOCTOR_BY_LOGIN_AND_PASSWORD)) {

            prepareStatement.setString(1, login);
            prepareStatement.setString(2, password);

            ResultSet resultSet = prepareStatement.executeQuery();

            if (resultSet.next()) {

                doctor = new Doctor(
                        resultSet.getInt("doctor.id"),
                        resultSet.getString("doctor.login"),
                        resultSet.getString("doctor.password"),
                        resultSet.getString("doctor.name"),
                        new Category(resultSet.getInt("category.id"), resultSet.getString("category.name"))
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return doctor;

    }
}
