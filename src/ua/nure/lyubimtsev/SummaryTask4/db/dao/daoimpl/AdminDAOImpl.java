package ua.nure.lyubimtsev.SummaryTask4.db.dao.daoimpl;


import org.apache.log4j.Logger;
import ua.nure.lyubimtsev.SummaryTask4.db.Fields;
import ua.nure.lyubimtsev.SummaryTask4.db.dao.MySQLDAOFactory;
import ua.nure.lyubimtsev.SummaryTask4.db.dao.entitydao.AdminDAO;
import ua.nure.lyubimtsev.SummaryTask4.db.entities.Admin;
import ua.nure.lyubimtsev.SummaryTask4.exception.DBException;
import ua.nure.lyubimtsev.SummaryTask4.exception.Messages;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminDAOImpl implements AdminDAO {

    private static final Logger LOG = Logger.getLogger(AdminDAOImpl.class);

    private static final String GET_ADMIN_BY_LOGIN_AND_PASSWORD = "SELECT * FROM admin WHERE login = ? AND password = ?";

    @Override
    public Admin getAdminByLoginAndPassword(String login, String password) throws DBException {

        Admin admin = null;

        try (Connection connection = MySQLDAOFactory.createConnection()) {
            try (PreparedStatement prepareStatement = connection.prepareStatement(GET_ADMIN_BY_LOGIN_AND_PASSWORD)) {

                connection.setAutoCommit(false);
                connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);

                prepareStatement.setString(1, login);
                prepareStatement.setString(2, password);

                ResultSet resultSet = prepareStatement.executeQuery();

                if (resultSet.next()) {

                    admin = new Admin(
                            resultSet.getInt(Fields.ENTITY_ID),
                            resultSet.getString(Fields.USER_LOGIN),
                            resultSet.getString(Fields.USER_PASSWORD),
                            resultSet.getString(Fields.NAME)
                    );
                }

                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                LOG.error(Messages.ERR_CANNOT_OBTAIN_ADMIN, e);
                throw new DBException(Messages.ERR_CANNOT_OBTAIN_ADMIN, e);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return admin;
    }
}
