package ua.nure.lyubimtsev.SummaryTask4.db.dao.daoimpl;

import ua.nure.lyubimtsev.SummaryTask4.db.Fields;
import ua.nure.lyubimtsev.SummaryTask4.db.dao.MySQLDAOFactory;
import ua.nure.lyubimtsev.SummaryTask4.db.dao.entitydao.TypeDAO;
import ua.nure.lyubimtsev.SummaryTask4.db.entities.Type;
import ua.nure.lyubimtsev.SummaryTask4.exception.DBException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TypeDAOImpl implements TypeDAO {

    private static final String SQL_GET_ALL_TYPES = "SELECT * FROM type";

    @Override
    public List<Type> getAllTypes() throws DBException {

        List<Type> types = new ArrayList<>();
        try (Connection connection = MySQLDAOFactory.createConnection();
             Statement statement = connection.createStatement()) {


            ResultSet resultSet = statement.executeQuery(SQL_GET_ALL_TYPES);

            while (resultSet.next()) {
                types.add(new Type(
                        resultSet.getInt(Fields.ENTITY_ID),
                        resultSet.getString(Fields.NAME)
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return types;

    }
}
