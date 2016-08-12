package ua.nure.lyubimtsev.SummaryTask4.db.dao.daoimpl;

import ua.nure.lyubimtsev.SummaryTask4.db.dao.MySQLDAOFactory;
import ua.nure.lyubimtsev.SummaryTask4.db.dao.entitydao.StateDAO;
import ua.nure.lyubimtsev.SummaryTask4.db.entities.State;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StateDAOImpl implements StateDAO {
    @Override
    public List<State> getStates() {

        List<State> states = new ArrayList<>();

        try(Connection connection = MySQLDAOFactory.createDataSource().getConnection();
            Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery("SELECT * FROM state");

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
