package ua.nure.lyubimtsev.SummaryTask4.db.dao.daoimpl;

import org.apache.log4j.Logger;
import ua.nure.lyubimtsev.SummaryTask4.db.Fields;
import ua.nure.lyubimtsev.SummaryTask4.db.dao.MySQLDAOFactory;
import ua.nure.lyubimtsev.SummaryTask4.db.dao.entitydao.MedicalCardDAO;
import ua.nure.lyubimtsev.SummaryTask4.db.entities.MedicalCard;
import ua.nure.lyubimtsev.SummaryTask4.exception.DBException;
import ua.nure.lyubimtsev.SummaryTask4.exception.Messages;

import java.sql.*;

public class MedicalCardDAOImpl implements MedicalCardDAO {

    private static final Logger LOG = Logger.getLogger(MedicalCardDAOImpl.class);

    private static final String SQL_INSERT_MEDICAL_CARD = "INSERT INTO medicalcard VALUES (DEFAULT, ?, ?)";
    private static final String SQL_GET_MEDICAL_CARD_BY_PATIENT_ID = "SELECT * FROM medicalcard WHERE patient_id=?";

    @Override
    public int insertMedicalCard(MedicalCard medicalCard) throws DBException {

        int rows = 0;

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = MySQLDAOFactory.createConnection();
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);

            preparedStatement = connection.prepareStatement(SQL_INSERT_MEDICAL_CARD);

            preparedStatement.setDate(1, new Date(medicalCard.getRegistrationDate().getTime()));
            preparedStatement.setInt(2, medicalCard.getPatientId());

            rows = preparedStatement.executeUpdate();
            connection.commit();

        } catch (SQLException e) {
            MySQLDAOFactory.rollback(connection);
            LOG.error(Messages.ERR_CANNOT_INSERT_MEDICAL_CARD, e);
            throw new DBException(Messages.ERR_CANNOT_INSERT_MEDICAL_CARD, e);
        } finally {
            MySQLDAOFactory.close(connection);
            MySQLDAOFactory.close(preparedStatement);
        }
        return rows;
    }


    @Override
    public MedicalCard getMedicalCardByPatientId(int patientId) throws DBException {

        MedicalCard medicalCard = null;

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = MySQLDAOFactory.createConnection();
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);

            preparedStatement = connection.prepareStatement(SQL_GET_MEDICAL_CARD_BY_PATIENT_ID);

            preparedStatement.setInt(1, patientId);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                medicalCard = new MedicalCard(
                        resultSet.getInt(Fields.ENTITY_ID),
                        resultSet.getDate(Fields.REGISTRATION_DATE),
                        patientId
                );
            }

            connection.commit();
        } catch (SQLException e) {
            MySQLDAOFactory.rollback(connection);
            LOG.error(Messages.ERR_CANNOT_OBTAIN_MEDICAL_CARD_BY_PATIENT_ID, e);
            throw new DBException(Messages.ERR_CANNOT_OBTAIN_MEDICAL_CARD_BY_PATIENT_ID, e);
        } finally {
            MySQLDAOFactory.close(connection, preparedStatement, resultSet);
        }
        return medicalCard;
    }
}
