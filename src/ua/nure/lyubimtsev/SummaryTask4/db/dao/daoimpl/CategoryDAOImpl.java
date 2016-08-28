package ua.nure.lyubimtsev.SummaryTask4.db.dao.daoimpl;

import org.apache.log4j.Logger;
import ua.nure.lyubimtsev.SummaryTask4.db.Fields;
import ua.nure.lyubimtsev.SummaryTask4.db.dao.MySQLDAOFactory;
import ua.nure.lyubimtsev.SummaryTask4.db.dao.entitydao.CategoryDAO;
import ua.nure.lyubimtsev.SummaryTask4.db.entities.Category;
import ua.nure.lyubimtsev.SummaryTask4.exception.DBException;
import ua.nure.lyubimtsev.SummaryTask4.exception.Messages;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAOImpl implements CategoryDAO {

    private static final Logger LOG = Logger.getLogger(AppointmentDAOImpl.class);

    private static final String GET_ALL_CATEGORIES = "SELECT * FROM category";

    @Override
    public List<Category> getAllCategories() throws DBException {

        List<Category> categories = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = MySQLDAOFactory.createConnection();
            statement = connection.createStatement();

            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);

            resultSet = statement.executeQuery(GET_ALL_CATEGORIES);

            while (resultSet.next()) {
                categories.add(new Category(
                        resultSet.getInt(Fields.ENTITY_ID),
                        resultSet.getString(Fields.NAME)
                ));
            }

            connection.commit();

        } catch (SQLException e) {
            MySQLDAOFactory.rollback(connection);
            LOG.error(Messages.ERR_CANNOT_OBTAIN_CATEGORIES, e);
            throw new DBException(Messages.ERR_CANNOT_OBTAIN_CATEGORIES, e);
        } finally {
            MySQLDAOFactory.close(connection, statement, resultSet);
        }


        return categories;

    }
}
