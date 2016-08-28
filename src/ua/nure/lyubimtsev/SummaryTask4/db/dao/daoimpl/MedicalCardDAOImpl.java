package ua.nure.lyubimtsev.SummaryTask4.db.dao.daoimpl;

import ua.nure.lyubimtsev.SummaryTask4.db.Fields;
import ua.nure.lyubimtsev.SummaryTask4.db.dao.MySQLDAOFactory;
import ua.nure.lyubimtsev.SummaryTask4.db.dao.entitydao.MedicalCardDAO;
import ua.nure.lyubimtsev.SummaryTask4.db.entities.MedicalCard;
import ua.nure.lyubimtsev.SummaryTask4.exception.DBException;

import java.sql.*;

public class MedicalCardDAOImpl implements MedicalCardDAO {

    private static final String SQL_INSERT_MEDICAL_CARD = "INSERT INTO medicalcard VALUES (DEFAULT, ?, ?)";
    private static final String SQL_GET_MEDICAL_CARD_BY_PATIENT_ID = "SELECT * FROM medicalcard WHERE patient_id=?";

    @Override
    public int insertMedicalCard(MedicalCard medicalCard) throws DBException {

        try (Connection connection = MySQLDAOFactory.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_MEDICAL_CARD)) {

            preparedStatement.setDate(1, new Date(medicalCard.getRegistrationDate().getTime()));
            preparedStatement.setInt(2, medicalCard.getPatientId());

            return preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }


    @Override
    public MedicalCard getMedicalCardByPatientId(int patientId) throws DBException {

        MedicalCard medicalCard = null;


        try (Connection connection = MySQLDAOFactory.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_MEDICAL_CARD_BY_PATIENT_ID)) {

            preparedStatement.setInt(1, patientId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                medicalCard = new MedicalCard(
                        resultSet.getInt(Fields.ENTITY_ID),
                        resultSet.getDate(Fields.REGISTRATION_DATE),
                        patientId
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return medicalCard;
    }
}
