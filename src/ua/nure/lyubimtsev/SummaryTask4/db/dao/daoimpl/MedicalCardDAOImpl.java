package ua.nure.lyubimtsev.SummaryTask4.db.dao.daoimpl;

import ua.nure.lyubimtsev.SummaryTask4.db.dao.MySQLDAOFactory;
import ua.nure.lyubimtsev.SummaryTask4.db.dao.entitydao.MedicalCardDAO;
import ua.nure.lyubimtsev.SummaryTask4.db.entities.MedicalCard;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MedicalCardDAOImpl implements MedicalCardDAO {
    @Override
    public int insertMedicalCard(MedicalCard medicalCard) {
        try (Connection connection = MySQLDAOFactory.createDataSource().getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO medicalcard VALUES (DEFAULT, ?, ?)");
            preparedStatement.setDate(1, new Date(new java.util.Date().getTime()));
            preparedStatement.setInt(2, medicalCard.getPatient().getId());

            return preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
