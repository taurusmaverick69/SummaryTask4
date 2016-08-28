package ua.nure.lyubimtsev.SummaryTask4.db.dao.daoimpl;

import ua.nure.lyubimtsev.SummaryTask4.db.dao.MySQLDAOFactory;
import ua.nure.lyubimtsev.SummaryTask4.db.dao.entitydao.CategoryDAO;
import ua.nure.lyubimtsev.SummaryTask4.db.entities.Category;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAOImpl implements CategoryDAO {
    @Override
    public List<Category> getCategories() {

        List<Category> categories = new ArrayList<>();

        try (Connection connection = MySQLDAOFactory.createConnection();
             Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery("SELECT * FROM category");

            while (resultSet.next()) {
                categories.add(new Category(
                        resultSet.getInt("id"),
                        resultSet.getString("name")
                ));
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return categories;
    }
}
