package ua.nure.lyubimtsev.SummaryTask4.db.dao.daoimpl;

import ua.nure.lyubimtsev.SummaryTask4.db.dao.MySQLDAOFactory;
import ua.nure.lyubimtsev.SummaryTask4.db.dao.entitydao.RoleDAO;
import ua.nure.lyubimtsev.SummaryTask4.db.entities.Role;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RoleDAOImpl implements RoleDAO {
    @Override
    public List<Role> getRoles() {

        List<Role> roles = new ArrayList<>();

        try (Connection connection = MySQLDAOFactory.createDataSource().getConnection();
             Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery("SELECT * FROM role");

            while (resultSet.next()) {
                roles.add(new Role(
                        resultSet.getInt("id"),
                        resultSet.getString("name")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return roles;
    }
}
