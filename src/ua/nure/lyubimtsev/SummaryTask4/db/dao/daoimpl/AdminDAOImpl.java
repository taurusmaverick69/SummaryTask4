package ua.nure.lyubimtsev.SummaryTask4.db.dao.daoimpl;


import ua.nure.lyubimtsev.SummaryTask4.db.dao.MySQLDAOFactory;
import ua.nure.lyubimtsev.SummaryTask4.db.dao.entitydao.AdminDAO;
import ua.nure.lyubimtsev.SummaryTask4.db.entities.Admin;

import java.sql.*;

public class AdminDAOImpl implements AdminDAO {

    private static final String GET_ADMIN_BY_LOGIN_AND_PASSWORD = "SELECT * FROM admin WHERE login = ? AND password = ?";

    @Override
    public Admin getAdminByLoginAndPassword(String login, String password) {

        Admin admin = null;

        try (Connection connection = MySQLDAOFactory.createConnection();
             PreparedStatement prepareStatement = connection.prepareStatement(GET_ADMIN_BY_LOGIN_AND_PASSWORD)) {

            prepareStatement.setString(1, login);
            prepareStatement.setString(2, password);

            ResultSet resultSet = prepareStatement.executeQuery();

            if (resultSet.next()) {

                admin = new Admin(
                        resultSet.getInt("id"),
                        resultSet.getString("login"),
                        resultSet.getString("password"),
                        resultSet.getString("name")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return admin;

    }
}
