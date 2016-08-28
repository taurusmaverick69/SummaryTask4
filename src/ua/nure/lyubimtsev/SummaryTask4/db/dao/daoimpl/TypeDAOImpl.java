package ua.nure.lyubimtsev.SummaryTask4.db.dao.daoimpl;

import org.apache.log4j.Logger;
import ua.nure.lyubimtsev.SummaryTask4.db.Fields;
import ua.nure.lyubimtsev.SummaryTask4.db.dao.MySQLDAOFactory;
import ua.nure.lyubimtsev.SummaryTask4.db.dao.entitydao.TypeDAO;
import ua.nure.lyubimtsev.SummaryTask4.db.entities.Type;
import ua.nure.lyubimtsev.SummaryTask4.exception.DBException;
import ua.nure.lyubimtsev.SummaryTask4.exception.Messages;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TypeDAOImpl implements TypeDAO {

    private static final Logger LOG = Logger.getLogger(TypeDAOImpl.class);


    private static final String SQL_GET_ALL_TYPES = "SELECT * FROM type";

    @Override
    public List<Type> getAllTypes() throws DBException {

        List<Type> types = new ArrayList<>();

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try  {

            connection = MySQLDAOFactory.createConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SQL_GET_ALL_TYPES);

            while (resultSet.next()) {
                types.add(new Type(
                        resultSet.getInt(Fields.ENTITY_ID),
                        resultSet.getString(Fields.NAME)
                ));
            }
        } catch (SQLException e) {
            MySQLDAOFactory.rollback(connection);
            LOG.error(Messages.ERR_CANNOT_OBTAIN_ADMIN, e);
            throw new DBException(Messages.ERR_CANNOT_OBTAIN_ADMIN, e);
        } finally {
            MySQLDAOFactory.close(connection, statement, resultSet);
        }
        return types;

    }
}
