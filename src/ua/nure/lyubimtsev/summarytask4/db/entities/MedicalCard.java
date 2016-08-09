package ua.nure.lyubimtsev.SummaryTask4.db.entities;

import java.util.Date;

public class MedicalCard {

    private int id;
    private Date registrationDate;
    private Patient patient;

    public MedicalCard() {
    }

    public MedicalCard(int id, Date registrationDate, Patient patient) {
        this.id = id;
        this.registrationDate = registrationDate;
        this.patient = patient;
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

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    @Override
    public String toString() {
        return "MedicalCard{" +
                "id=" + id +
                ", registrationDate=" + registrationDate +
                ", patient=" + patient +
                '}';
    }
}
