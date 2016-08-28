package ua.nure.lyubimtsev.SummaryTask4.db.dao.daoimpl;

import ua.nure.lyubimtsev.SummaryTask4.db.dao.MySQLDAOFactory;
import ua.nure.lyubimtsev.SummaryTask4.db.dao.entitydao.StateDAO;
import ua.nure.lyubimtsev.SummaryTask4.db.entities.State;
import ua.nure.lyubimtsev.SummaryTask4.exception.DBException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StateDAOImpl implements StateDAO {

    private static final String SQL_GEL_ALL_STATES = "SELECT * FROM state";

    @Override
    public List<State> getAllStates() throws DBException {

        List<State> states = new ArrayList<>();

        try(Connection connection = MySQLDAOFactory.createConnection();
            Statement statement = connection.createStatement()) {


            ResultSet resultSet = statement.executeQuery(SQL_GEL_ALL_STATES);

            while (resultSet.next()){

                states.add(new State(
                        resultSet.getInt("id"),
                        resultSet.getString("name")
                ));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

        return states;
    }
}
