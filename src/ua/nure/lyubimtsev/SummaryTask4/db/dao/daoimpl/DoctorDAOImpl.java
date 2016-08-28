package ua.nure.lyubimtsev.SummaryTask4.db.dao.daoimpl;

import ua.nure.lyubimtsev.SummaryTask4.Role;
import ua.nure.lyubimtsev.SummaryTask4.db.dao.MySQLDAOFactory;
import ua.nure.lyubimtsev.SummaryTask4.db.dao.entitydao.DoctorDAO;
import ua.nure.lyubimtsev.SummaryTask4.db.entities.Category;
import ua.nure.lyubimtsev.SummaryTask4.db.entities.Doctor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DoctorDAOImpl implements DoctorDAO {


    private static final String GET_DOCTOR_BY_LOGIN_AND_PASSWORD = "SELECT * FROM doctor, category WHERE doctor.category_id = category.id AND login = ? AND password = ?";

    @Override
    public List<Doctor> getAllDoctors() {

        List<Doctor> doctors = new ArrayList<>();

        try (Connection connection = MySQLDAOFactory.createConnection();
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

        try (Connection connection = MySQLDAOFactory.createConnection();
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

    @Override
    public int insertDoctor(Doctor doctor) {

        int rows = 0;
        try (Connection connection = MySQLDAOFactory.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO doctor VALUES (DEFAULT,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS)) {

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

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rows;
    }

    @Override
    public int updateDoctor(Doctor doctor, Role role) {

        PreparedStatement preparedStatement = null;
        try (Connection connection = MySQLDAOFactory.createConnection()) {

            switch (role) {
                case ADMIN:
                    preparedStatement = connection.prepareStatement("UPDATE doctor SET name = ?, category_id = ? WHERE id = ?");
                    preparedStatement.setString(1, doctor.getName());
                    preparedStatement.setInt(2, doctor.getCategory().getId());
                    preparedStatement.setInt(3, doctor.getId());
                    break;

                case DOCTOR:
                    preparedStatement = connection.prepareStatement("UPDATE doctor SET login = ?, password = ?, name = ?, category_id = ? WHERE id = ?");
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
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }


    @Override
    public boolean isLoginExists(String login) {
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
