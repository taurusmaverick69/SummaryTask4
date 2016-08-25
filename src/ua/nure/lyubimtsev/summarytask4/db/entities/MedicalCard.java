package ua.nure.lyubimtsev.SummaryTask4.db.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class MedicalCard {

    private int id;
    private Date registrationDate;
    private int patientId;
    private List<Appointment> appointments;

    public MedicalCard() {
    }

    public MedicalCard(int id, Date registrationDate, int patientId) {
        this.id = id;
        this.registrationDate = registrationDate;
        this.patientId = patientId;
        this.appointments = new ArrayList<>();
    }

    public MedicalCard(Date registrationDate, int patientId) {
        this.registrationDate = registrationDate;
        this.patientId = patientId;
        this.appointments = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }


    public Appointment getAppointmentById(int id) {
        return appointments
                .stream()
                .filter(appointment -> appointment.getId() == id)
                .collect(Collectors.collectingAndThen(Collectors.toList(), list -> list.get(0)));
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }



    @Override
    public String toString() {
        return "MedicalCard{" +
                "id=" + id +
                ", registrationDate=" + registrationDate +
                ", patientId=" + patientId +
                ", appointments=" + appointments +
                '}';
    }
}
