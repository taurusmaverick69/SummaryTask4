package ua.nure.lyubimtsev.SummaryTask4.db.dao.daoimpl;

import ua.nure.lyubimtsev.SummaryTask4.db.dao.MySQLDAOFactory;
import ua.nure.lyubimtsev.SummaryTask4.db.dao.entitydao.MedicalCardDAO;
import ua.nure.lyubimtsev.SummaryTask4.db.entities.MedicalCard;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MedicalCardDAOImpl implements MedicalCardDAO {
    @Override
    public int insertMedicalCard(MedicalCard medicalCard) {
//        try (Connection connection = MySQLDAOFactory.createDataSource().getConnection()) {
//
//            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO medicalcard VALUES (DEFAULT, ?, ?)");
//            preparedStatement.setDate(1, new Date(new java.util.Date().getTime()));
//            preparedStatement.setInt(2, medicalCard.getPatient().getId());
//
//            return preparedStatement.executeUpdate();
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return 0;

        return 0;
    }

    @Override
    public List<MedicalCard> getAllMedicalCards() {
//
//        List<MedicalCard> medicalCards = new ArrayList<>();
//
//        try (Connection connection = MySQLDAOFactory.createDataSource().getConnection()) {
//
//            Statement statement = connection.createStatement();
//            ResultSet resultSet = statement.executeQuery("SELECT * FROM medicalcard");
//
//            while (resultSet.next()) {
//                medicalCards.add(
//                        new MedicalCard(
//                                resultSet.getInt("id"),
//                                resultSet.getDate(""),
//                                )
//
//                )
//            }
//
//
//            return preparedStatement.executeUpdate();
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
        return null;
    }

    @Override
    public MedicalCard getMedicalCardByPatientId(int patientId) {

        MedicalCard medicalCard = null;

        try (Connection connection = MySQLDAOFactory.createDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM medicalcard WHERE patient_id=?")) {

            preparedStatement.setInt(1, patientId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                medicalCard = new MedicalCard(
                        resultSet.getInt("id"),
                        resultSet.getDate("registrationDate"),
                        patientId
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return medicalCard;
    }
}