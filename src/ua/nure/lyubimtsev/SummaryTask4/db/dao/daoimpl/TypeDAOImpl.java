package ua.nure.lyubimtsev.SummaryTask4.db.dao.daoimpl;

import ua.nure.lyubimtsev.SummaryTask4.db.dao.MySQLDAOFactory;
import ua.nure.lyubimtsev.SummaryTask4.db.dao.entitydao.TypeDAO;
import ua.nure.lyubimtsev.SummaryTask4.db.entities.Type;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TypeDAOImpl implements TypeDAO {
    @Override
    public List<Type> getAllTypes() {

        List<Type> types = new ArrayList<>();
        try (Connection connection = MySQLDAOFactory.createDataSource().getConnection();
             Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery("SELECT * FROM type");

            while (resultSet.next()) {
                types.add(new Type(
                        resultSet.getInt("id"),
                        resultSet.getString("name")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return types;

    }
}
