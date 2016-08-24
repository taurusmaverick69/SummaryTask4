package ua.nure.lyubimtsev.SummaryTask4.db.entities;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Patient {

    private int id;
    private String name;
    private String address;
    private Date birthDate;
    private State state;
    private int doctorId;

    public Patient() {
    }

    public Patient(int id, String name, String address, Date birthDate, State state) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.birthDate = birthDate;
        this.state = state;
    }

    public Patient(String name, String address, Date birthDate, State state, int doctorId) {
        this.name = name;
        this.address = address;
        this.birthDate = birthDate;
        this.state = state;
        this.doctorId = doctorId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public String formatBirthDate() {
        return new SimpleDateFormat("dd.MM.yyyy").format(birthDate);
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", birthDate=" + birthDate +
                ", state=" + state +
                '}';
    }
}
