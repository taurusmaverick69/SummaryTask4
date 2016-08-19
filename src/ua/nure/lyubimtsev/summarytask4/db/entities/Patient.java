package ua.nure.lyubimtsev.SummaryTask4.db.entities;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Patient {

    private int id;
    private String name;
    private String address;
    private Date birthDate;
    private State state;
    private int doctor_id;

    public Patient() {
    }

    public Patient(int id, String name, String address, Date birthDate, State state) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.birthDate = birthDate;
        this.state = state;
    }

    public Patient(String name, String address, Date birthDate, State state, int doctor_id) {
        this.name = name;
        this.address = address;
        this.birthDate = birthDate;
        this.state = state;
        this.doctor_id = doctor_id;
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

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public int getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(int doctor_id) {
        this.doctor_id = doctor_id;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", birthDate=" + birthDate +
                ", state=" + state +
                ", doctor_id=" + doctor_id +
                '}';
    }
}
