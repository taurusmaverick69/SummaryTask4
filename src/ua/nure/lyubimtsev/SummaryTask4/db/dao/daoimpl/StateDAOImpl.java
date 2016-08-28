package ua.nure.lyubimtsev.SummaryTask4.db.dao.daoimpl;

import org.apache.log4j.Logger;
import ua.nure.lyubimtsev.SummaryTask4.db.dao.MySQLDAOFactory;
import ua.nure.lyubimtsev.SummaryTask4.db.dao.entitydao.StateDAO;
import ua.nure.lyubimtsev.SummaryTask4.db.entities.State;
import ua.nure.lyubimtsev.SummaryTask4.exception.DBException;
import ua.nure.lyubimtsev.SummaryTask4.exception.Messages;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StateDAOImpl implements StateDAO {

    private static final Logger LOG = Logger.getLogger(StateDAOImpl.class);

    private static final String SQL_GEL_ALL_STATES = "SELECT * FROM state";

    @Override
    public List<State> getAllStates() throws DBException {

        List<State> states = new ArrayList<>();

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = MySQLDAOFactory.createConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SQL_GEL_ALL_STATES);

            while (resultSet.next()) {
                states.add(new State(
                        resultSet.getInt("id"),
                        resultSet.getString("name")
                ));
            }
        } catch (SQLException e) {
            MySQLDAOFactory.rollback(connection);
            LOG.error(Messages.ERR_CANNOT_OBTAIN_ADMIN, e);
            throw new DBException(Messages.ERR_CANNOT_OBTAIN_ADMIN, e);
        } finally {
            MySQLDAOFactory.close(connection, statement, resultSet);
        }

        return states;
    }
}
