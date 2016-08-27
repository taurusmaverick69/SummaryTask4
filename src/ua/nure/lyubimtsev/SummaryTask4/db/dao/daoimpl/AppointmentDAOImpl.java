package ua.nure.lyubimtsev.SummaryTask4.db.dao.daoimpl;

import ua.nure.lyubimtsev.SummaryTask4.db.dao.MySQLDAOFactory;
import ua.nure.lyubimtsev.SummaryTask4.db.dao.entitydao.AppointmentDAO;
import ua.nure.lyubimtsev.SummaryTask4.db.entities.Appointment;
import ua.nure.lyubimtsev.SummaryTask4.db.entities.Type;
import ua.nure.lyubimtsev.SummaryTask4.db.entities.Category;
import ua.nure.lyubimtsev.SummaryTask4.db.entities.Doctor;

import javax.print.Doc;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AppointmentDAOImpl implements AppointmentDAO {


    private static final String GET_APPOINTMENTS_BY_MEDICAL_CARD = "SELECT *\n" +
            "FROM appointment, type, doctor\n" +
            "WHERE appointment.type_id = type.id\n" +
            "      AND appointment.doctor_id = doctor.id\n" +
            "      AND medicalCard_id = ?";

    @Override
    public List<Appointment> getAppointmentsByMedicalCardId(int medicalCardId) {

        List<Appointment> appointments = new ArrayList<>();


        try (Connection connection = MySQLDAOFactory.createDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_APPOINTMENTS_BY_MEDICAL_CARD)) {

            preparedStatement.setInt(1, medicalCardId);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                appointments.add(new Appointment(
                        resultSet.getInt("appointment.id"),
                        resultSet.getString("appointment.diagnose"),
                        new Type(resultSet.getInt("type.id"), resultSet.getString("type.name")),
                        resultSet.getString("appointment.info"),
                        resultSet.getDate("appointment.date"),
                        Doctor.newBuilder().setId(resultSet.getInt("doctor.id")).setName(resultSet.getString("doctor.name")).build(),
                        medicalCardId
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return appointments;
    }

    @Override
    public int insertAppointment(Appointment appointment) {
        try (Connection connection = MySQLDAOFactory.createDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO appointment VALUES (DEFAULT,?,?,?,?,?,?)")) {

            preparedStatement.setString(1, appointment.getDiagnose());
            preparedStatement.setInt(2, appointment.getType().getId());
            preparedStatement.setString(3, appointment.getInfo());
            preparedStatement.setDate(4, new Date(appointment.getDate().getTime()));
            preparedStatement.setInt(5, appointment.getMedicalCardId());
            preparedStatement.setInt(6, appointment.getDoctor().getId());

            return preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int updateAppointment(Appointment appointment) {
        try (Connection connection = MySQLDAOFactory.createDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("UPDATE  appointment SET diagnose = ?, type_id = ?, info = ? WHERE id = ?")) {

            preparedStatement.setString(1, appointment.getDiagnose());
            preparedStatement.setInt(2, appointment.getType().getId());
            preparedStatement.setString(3, appointment.getInfo());
            preparedStatement.setInt(4, appointment.getId());

            return preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
