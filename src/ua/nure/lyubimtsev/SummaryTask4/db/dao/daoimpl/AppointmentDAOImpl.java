package ua.nure.lyubimtsev.SummaryTask4.db.dao.daoimpl;

import ua.nure.lyubimtsev.SummaryTask4.db.dao.MySQLDAOFactory;
import ua.nure.lyubimtsev.SummaryTask4.db.dao.entitydao.AppointmentDAO;
import ua.nure.lyubimtsev.SummaryTask4.db.entities.Appointment;
import ua.nure.lyubimtsev.SummaryTask4.db.entities.Type;
import ua.nure.lyubimtsev.SummaryTask4.db.entities.Category;
import ua.nure.lyubimtsev.SummaryTask4.db.entities.Doctor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AppointmentDAOImpl implements AppointmentDAO {


    private static final String GET_APPOINTMENTS_BY_MEDICAL_CARD = "SELECT a.id, a.diagnose, t.id, t.name, a.info, a.date,  a.medicalCard_id, d.name, c.name\n" +
            " FROM appointment a, type t , doctor d, category c\n" +
            " WHERE a.type_id = t.id \n" +
            " AND a.doctor_id = d.id\n" +
            " and d.category_id = c.id\n" +
            " AND medicalCard_id = ?";

    @Override
    public List<Appointment> getAppointmentsByMedicalCardId(int medicalCardId) {

        List<Appointment> appointments = new ArrayList<>();


        try (Connection connection = MySQLDAOFactory.createDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_APPOINTMENTS_BY_MEDICAL_CARD)) {

            preparedStatement.setInt(1, medicalCardId);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                appointments.add(new Appointment(
                        resultSet.getInt("a.id"),
                        resultSet.getString("a.diagnose"),
                        new Type(resultSet.getInt("t.id"), resultSet.getString("t.name")),
                        resultSet.getString("a.info"),
                        resultSet.getDate("a.date"),
                        Doctor.newBuilder().setName(resultSet.getString("d.name")).setCategory(new Category(resultSet.getString("c.name"))).build(),
                        medicalCardId
                ));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return appointments;
    }
}
