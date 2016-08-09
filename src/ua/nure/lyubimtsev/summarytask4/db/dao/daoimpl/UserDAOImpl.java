package ua.nure.lyubimtsev.SummaryTask4.db.dao.daoimpl;


import ua.nure.lyubimtsev.SummaryTask4.db.dao.MySQLDAOFactory;
import ua.nure.lyubimtsev.SummaryTask4.db.dao.entitydao.UserDAO;
import ua.nure.lyubimtsev.SummaryTask4.db.entities.Patient;
import ua.nure.lyubimtsev.SummaryTask4.db.entities.PatientState;
import ua.nure.lyubimtsev.SummaryTask4.db.entities.Role;
import ua.nure.lyubimtsev.SummaryTask4.db.entities.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {

    private static final String GET_USER_BY_LOGIN_AND_PASSWORD = "SELECT * FROM user, role WHERE user.role_id = role.id AND login = ? AND password = ?";
    private static final String GET_PATIENTS_BY_USER_ID = "SELECT * FROM patient, user_patient, state WHERE patient.id = user_patient.patient_id AND state_id = state.id AND user_patient.user_id = ?";

    private static final String GET_DOCTORS = "SELECT * FROM user, role WHERE role_id = role.id AND role.name <> ?";

    @Override
    public User getUserByLoginAndPassword(String login, String password) {


        try (Connection connection = MySQLDAOFactory.createDataSource().getConnection();
             PreparedStatement prepareStatement = connection.prepareStatement(GET_USER_BY_LOGIN_AND_PASSWORD)) {

            prepareStatement.setString(1, login);
            prepareStatement.setString(2, password);

            ResultSet resultSet = prepareStatement.executeQuery();

            if (resultSet.next()) {

                User user = new User(
                        resultSet.getInt("user.id"),
                        resultSet.getString("user.login"),
                        resultSet.getString("user.password"),
                        resultSet.getString("user.name"),
                        new Role(resultSet.getInt("role.id"), resultSet.getString("role.name"))
                );

                String role = resultSet.getString("role.name");
                if (role.equals("admin")) {

                    List<User> doctors = new ArrayList<>();
                    PreparedStatement preparedStatement = connection.prepareStatement(GET_DOCTORS);
                    preparedStatement.setString(1, role);
                    ResultSet doctorResultSet = preparedStatement.executeQuery();

                    while (doctorResultSet.next()) {
                        doctors.add(
                                new User(
                                        resultSet.getInt("user.id"),
                                        resultSet.getString("user.login"),
                                        resultSet.getString("user.password"),
                                        resultSet.getString("user.name"),
                                        new Role(resultSet.getInt("role.id"), resultSet.getString("role.name"))
                                )
                        );

                    }

                    user.setDoctors(doctors);
                }


                PreparedStatement patientPreparedStatement = connection.prepareStatement(GET_PATIENTS_BY_USER_ID);
                patientPreparedStatement.setInt(1, user.getId());
                ResultSet patientResultSet = patientPreparedStatement.executeQuery();

                while (patientResultSet.next()) {
                    user.getPatients().add(
                            new Patient(
                                    patientResultSet.getInt("id"),
                                    patientResultSet.getString("name"),
                                    patientResultSet.getString("address"),
                                    patientResultSet.getDate("birthDate"),
                                    new PatientState(patientResultSet.getInt("state.id"), patientResultSet.getString("state.name"))
                            )
                    );
                }

                return user;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int insertUser(User user) {
        try (Connection connection = MySQLDAOFactory.createDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO user VALUES (DEFAULT, ?, ?, ?, ?)")) {

            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getName());
            preparedStatement.setInt(4, user.getRole().getId());

            return preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    @Override
    public int updateUser(User user) {
        try (Connection connection = MySQLDAOFactory.createDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("UPDATE user SET login = ?, password = ?,  name = ?, role_id = ? WHERE id = ?")) {

            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getName());
            preparedStatement.setInt(4, user.getRole().getId());
            preparedStatement.setInt(5, user.getId());

            return preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }


}
