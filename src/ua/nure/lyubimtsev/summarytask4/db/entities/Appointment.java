package ua.nure.lyubimtsev.SummaryTask4.db.entities;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Appointment {

    private int id;
    private String diagnose;
    private Type type;
    private String info;
    private Date date;
    private Doctor doctor;
    private int medicalCardId;

    public Appointment() {
    }

    public Appointment(int id, String diagnose, Type type, String info, Date date, Doctor doctor, int medicalCardId) {
        this.id = id;
        this.diagnose = diagnose;
        this.type = type;
        this.info = info;
        this.date = date;
        this.doctor = doctor;
        this.medicalCardId = medicalCardId;
    }

    public Appointment(String diagnose, Type type, String info, Date date, Doctor doctor, int medicalCardId) {
        this.diagnose = diagnose;
        this.type = type;
        this.info = info;
        this.date = date;
        this.doctor = doctor;
        this.medicalCardId = medicalCardId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDiagnose() {
        return diagnose;
    }

    public void setDiagnose(String diagnose) {
        this.diagnose = diagnose;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Date getDate() {
        return date;
    }

    public String formatDate() {
        return new SimpleDateFormat("dd.MM.yyyy").format(date);
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public int getMedicalCardId() {
        return medicalCardId;
    }

    public void setMedicalCardId(int medicalCardId) {
        this.medicalCardId = medicalCardId;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "id=" + id +
                ", diagnose='" + diagnose + '\'' +
                ", type=" + type +
                ", info='" + info + '\'' +
                ", date=" + date +
                ", doctor=" + doctor +
                ", medicalCardId=" + medicalCardId +
                '}';
    }
}


